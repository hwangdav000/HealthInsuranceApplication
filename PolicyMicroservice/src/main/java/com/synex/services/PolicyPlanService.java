package com.synex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.synex.repository.PolicyIssueRepository;
import com.synex.repository.PolicyPlanRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.PolicyIssue;
import com.synex.domain.PolicyPlan;
import com.synex.domain.PolicyStatus;

@Service
public class PolicyPlanService {
	@Autowired
	EmailService emailService;

	@Autowired
	PolicyIssueRepository iRepo;
	@Autowired
	PolicyPlanRepository pRepo;
	
	
	public List<PolicyPlan> getPolicyPlans () {
		return pRepo.findAll();
	}
	
	// filter for plan name
	public List<PolicyPlan> searchLikePlan (String searchString) {
		return pRepo.findByNameLike("%" + searchString + "%");
	}
	
	public List<PolicyIssue> getPolicyIssuesById (int userId) {
		return iRepo.findByUserId(userId);
	}
	
	public PolicyIssue savePolicyIssue(JsonNode node) {
	    int planId = node.get("planId").asInt();
	    PolicyIssue pIssue = new PolicyIssue();

	    // set policy plan by id
	    PolicyPlan plan = pRepo.findByPolicyId(planId);
	    
	    pIssue.setPlan(plan);
	    pIssue.setUserId(node.get("userId").asInt());
	    pIssue.setCreatedDate(LocalDate.now());
	    
	    // Parse startDate from the JSON node and set it, adding 12 months for end date
	    if (node.has("startDate")) {
	        String startDateStr = node.get("startDate").asText();
	        
	        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
	        pIssue.setStartDate(startDate);

	        // Add 12 months to the startDate and set it as the endDate
	        LocalDate endDate = startDate.plusMonths(node.get("coverageLength").asInt());
	        pIssue.setEndDate(endDate);
	    } else {
	        pIssue.setStartDate(null);
	        pIssue.setEndDate(null);
	    }
	    
	    // set status to pending
	    pIssue.setStatus(PolicyStatus.PENDING);
	    
	    return iRepo.save(pIssue);
	}
	
	public List<PolicyIssue> getPolicyIssues() {
		return iRepo.findAll();
	}
	
	public PolicyIssue setStatus(JsonNode node) {
		int id = node.get("id").asInt();
		String status = node.get("status").asText();
		PolicyIssue user_policy = iRepo.findById(id);
		System.out.println(status);
		
		if (status.equals("APPROVED")) {
			user_policy.setStatus(PolicyStatus.APPROVED);
		} else {
			user_policy.setStatus(PolicyStatus.REJECTED);
		}
		// send email regarding policy status here
		String email = node.get("userEmail").asText();
		// send policy plan details
		PolicyPlan p = pRepo.findByPolicyId(node.get("policyId").asInt());
		
		emailService.sendEmailWithPdf(email, "", p, status);
		
		
		return iRepo.save(user_policy);
	}
}
