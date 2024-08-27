package com.synex.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.Claim;
import com.synex.domain.ClaimItem;
import com.synex.domain.Status;
import com.synex.repository.ClaimItemRepository;
import com.synex.repository.ClaimRepository;

@Service
public class ClaimService {
	@Autowired
	EmailService emailService;
	
	@Autowired
	ClaimRepository cRepo;
	
	@Autowired
	ClaimItemRepository iRepo;
	
	public int getUserClaimCount(int userId) {
		return cRepo.getUserClaimCount(userId);
	}
	public List<Claim> getClaims() {
		return cRepo.findAll();
	}
	
	public List<Claim> getClaimsByUserId(int userId) {
		return cRepo.findByUserId(userId);
	}
	
	public Claim getClaimsById(int Id) {
		return cRepo.findById(Id);
	}
	
	public ClaimItem getClaimItemById(int Id) {
		return iRepo.findById(Id);
	}
	
	public Claim saveClaim(JsonNode node) {
	    Claim c = new Claim();
	    
	    String recipientName = node.get("claimantName").asText();
	    LocalDate currentDate = LocalDate.parse(node.get("currentDate").asText());
	    int claimId = node.get("claimId").asInt();
	    int policyId = node.get("policyId").asInt();
	    int userId = node.get("userId").asInt();
	    String userName = node.get("userName").asText();
	    
	    c.setPolicyId(policyId);
	    c.setRecipientName(recipientName);
	    c.setCreatedDate(currentDate);
	    c.setClaimId(claimId);
	    c.setUserId(userId);
	    
	    System.out.println("I am in save CLAIM");

	    // claim items
	    JsonNode serviceDateStartList = node.get("date-of-service-start");
	    JsonNode serviceDateEndList = node.get("date-of-service-end");
	    JsonNode placeOfServiceList = node.get("place-of-service");
	    JsonNode cptCodeList = node.get("cpt-code");
	    JsonNode modifiersList = node.get("modifiers");
	    JsonNode diagnosisList = node.get("diagnosis");
	    JsonNode chargeList = node.get("charges");
	    JsonNode extensionList = node.get("fileExtensions");

	    // go through each claim item and save the document path
	    List<ClaimItem> cList = new ArrayList<>();
	    for (int i = 0; i < chargeList.size(); i++) {
	        ClaimItem cItem = new ClaimItem();

	        cItem.setCharge(chargeList.get(i).asDouble());
	        cItem.setCPTCode(cptCodeList.get(i).asText());
	        cItem.setDiagnosis(diagnosisList.get(i).asText());
	        cItem.setModifiers(modifiersList.get(i).asText());
	        cItem.setPlaceOfService(placeOfServiceList.get(i).asText());
	        cItem.setServiceDateEnd(LocalDate.parse(serviceDateEndList.get(i).asText()));
	        cItem.setServiceDateStart(LocalDate.parse(serviceDateStartList.get(i).asText()));

	        String fileName = userName + "_" + i + "_" + claimId + "." + extensionList.get(i).asText();
	        cItem.setDocumentName(fileName);
	        
	        String documentPath = "C:/SynergisticIT/HealthInsuranceClaims/" + userName + "/" + fileName;
	        cItem.setDocumentPath(documentPath);
	        
	        cList.add(cItem);
	        iRepo.save(cItem);
	    }
	
	    c.setClaimItems(cList);
	    return cRepo.save(c);
	}
	
	public Claim setClaimStatus(JsonNode node) {
		int id = node.get("id").asInt();
		String status = node.get("status").asText();
		Claim c = cRepo.findById(id);
		
		if (status.equals("REVIEWED")) {
			c.setStatus(Status.REVIEWED);
		}
		
		// send alert email
		String email = node.get("userEmail").asText();
		String subject = "Claim " + id + " has been reviewed";
		String body = "Please check in view claims to see more details";
		emailService.sendEmail(email, subject, body);

		return cRepo.save(c);
	}
	
	public Claim updateClaimItems(JsonNode node) {
		
		int claimId = node.get("claimId").asInt();
		JsonNode approvalList = node.get("updatedItems");
		
	    Claim c = getClaimsById(claimId);
	    
	    // update status of claim item respectively through approval list
	    for (int i = 0; i < approvalList.size(); i++) {
	    	
	    	int itemId = approvalList.get(i).get("itemId").asInt();
	    	String approvalStatus = approvalList.get(i).get("approvalStatus").asText();
	        ClaimItem cItem = getClaimItemById(itemId);
	        if (approvalStatus.equals("approve")) {
	        	cItem.setStatus(Status.APPROVED);
	        } else {
	        	cItem.setStatus(Status.REJECTED);
	        }
	      
	        iRepo.save(cItem);
	    }
	
	    return c;
	}

}
