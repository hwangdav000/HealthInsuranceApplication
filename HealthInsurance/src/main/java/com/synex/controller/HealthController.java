package com.synex.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.synex.client.HealthClient;
import com.synex.domain.User;
import com.synex.domain.Documents;
import com.synex.repository.DocumentsRepository;
import com.synex.repository.UserRepository;
import com.synex.services.UserService;
import com.synex.services.UserServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HealthController {
	public static final String UPLOAD_DIR = "C:/SynergisticIT/HealthInsuranceDocuments";
	public static final String UPLOAD_CLAIM_DIR = "C:/SynergisticIT/HealthInsuranceClaims";
	
	@Autowired
	HealthClient hclient;
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	DocumentsRepository dRepo;
	
	@RequestMapping(value="/api/updateClaimItems", method=RequestMethod.POST)
	public JsonNode updateClaimItems(@RequestBody JsonNode node) {
		return hclient.updateClaimItems(node);
	}
	
	@RequestMapping(value="/api/getClaims/{userId}", method=RequestMethod.GET)
	public JsonNode getClaimsById(@PathVariable Integer userId, Principal principal) {
	  
	    return hclient.getClaimsById(userId);
	}
	
	@RequestMapping(value="/api/getClaims/", method=RequestMethod.GET)
	public JsonNode getClaimsByPrincipal( Principal principal) {
	   
        String username = principal.getName();
        User u = uRepo.findByUserName(username);
        
        return hclient.getClaimsById(u.getUserId());
	}
	
	@RequestMapping(value="/api/getClaims", method=RequestMethod.GET)
	public JsonNode getClaims() {
		return hclient.getClaims();
	}
	
	@RequestMapping(value="/api/getPolicyPlans", method=RequestMethod.GET)
	public JsonNode getPolicyPlans() {
		return hclient.getPolicyPlans();
	}
	
	// get number of claims created by user
	@RequestMapping(value="/api/getClaimCount", method=RequestMethod.GET)
	public JsonNode getClaimCount(Principal principal) {
		String username = principal.getName();
	    User u = uRepo.findByUserName(username);
		
		return hclient.getClaimCount(u.getUserId());
	}
	
	@RequestMapping(value="/api/getPlanTypes", method=RequestMethod.GET)
	public JsonNode getPlanTypes() {
		return hclient.getPlanTypes();
	}
	
	@RequestMapping(value="/api/getPolicyIssues", method=RequestMethod.GET)
	public JsonNode getPlanIssues() {
		return hclient.getPolicyIssues();
	}
	
	@RequestMapping(value="/api/getMetalTiers", method=RequestMethod.GET)
	public JsonNode getMetalTiers() {
		return hclient.getMetalTiers();
	}

	@RequestMapping(value="/api/setClaimStatus", method=RequestMethod.POST)
	public JsonNode setClaimStatus(@RequestBody JsonNode node) {
		
		int userId = node.get("userId").asInt();
		
		User u = uRepo.findByUserId(userId);
		((ObjectNode) node).put("userEmail", u.getEmail());
		
		return hclient.setClaimStatus(node);
	}
	
	@RequestMapping(value="/api/setPolicyStatus", method=RequestMethod.POST)
	public JsonNode setPolicyStatus(@RequestBody JsonNode node) {
		
		int userId = node.get("userId").asInt();
		
		User u = uRepo.findByUserId(userId);
		((ObjectNode) node).put("userEmail", u.getEmail());
		
		return hclient.setPolicyStatus(node);
	}
	
	@RequestMapping(value="/api/filterPlans", method=RequestMethod.POST)
	public JsonNode filterPolicy(@RequestBody JsonNode node) {
		
		return hclient.filterPlans(node);
	}
	
	@RequestMapping(value="/api/getDocuments/{userId}", method=RequestMethod.GET)
	public List<Documents> getDocuments(@PathVariable int userId) {
		return uRepo.findByUserId(userId).getDocuments();
	}
	
	// get policy issues of the user
	@RequestMapping(value="/api/getPolicyIssues2", method=RequestMethod.GET)
	public JsonNode getPolicyIssues2(Principal principal) {
		String username = principal.getName();
		User u = uRepo.findByUserName(username);
		return hclient.getPolicyIssues2(u.getUserId());
	}
	
	@RequestMapping(value="/api/saveClaim", method=RequestMethod.POST)
	public JsonNode saveClaim(@RequestBody JsonNode node, Principal principal) {
		String username = principal.getName();
		User u = uRepo.findByUserName(username);
		((ObjectNode) node).put("userName", username);
		((ObjectNode) node).put("userId", u.getUserId());
		return hclient.saveClaim(node);
	}
	
	// handles uploading of documents of user
	@PostMapping("/uploader")
	public ResponseEntity<?> uploader(@RequestParam("fileNames") List<String> fileNames,
	                                  @RequestParam("files") List<MultipartFile> files,
	                                  @RequestParam("fileExtensions") List<String> fileExtensions,
	                                  Principal principal) {
	    String username = principal.getName();
	    User u = uRepo.findByUserName(username);
	    List<Documents> dList = new ArrayList<>();

	    String UPLOAD_DIR_USER = UPLOAD_DIR + "/" + username;
	    try {
	        // Create user folder if it doesn't exist
	        Path userDirPath = Paths.get(UPLOAD_DIR_USER);
	        if (!Files.exists(userDirPath)) {
	            Files.createDirectories(userDirPath);
	        }

	        for (int i = 0; i < files.size(); i++) {
	            MultipartFile file = files.get(i);
	            Documents d = new Documents();

	            // Check file size
	            if (file.getSize() <= 5 * 1024 * 1024) { // 5MB limit in bytes
	                byte[] bytes = file.getBytes();
	                String fullFileName= fileNames.get(i) + "." + fileExtensions.get(i);
	                
	                Path path = Paths.get(UPLOAD_DIR_USER + "/" + fullFileName);
	                Files.write(path, bytes);

	                d.setDocumentName(fileNames.get(i));
	                d.setDocumentPath(UPLOAD_DIR_USER + "/" + fullFileName);
	                d.setExtensionType(fileExtensions.get(i));
	                dList.add(d);
	                dRepo.save(d);
	            } else {
	                return new ResponseEntity<>("File size exceeded limit (5MB)", HttpStatus.BAD_REQUEST);
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Error uploading files", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    u.setDocuments(dList);
	    uRepo.save(u);

	    return new ResponseEntity<>(dList, HttpStatus.CREATED);
	}
	
	// handles uploading the claim documents submitted by user
	@PostMapping("/uploaderClaim")
	public ResponseEntity<?> uploaderClaim(
	                                  @RequestParam("files") List<MultipartFile> files,
	                                  @RequestParam("fileExtensions") List<String> fileExtensions,
	                                  @RequestParam("claimId") int claimId,
	                                  Principal principal) {
	    String username = principal.getName();
	    User u = uRepo.findByUserName(username);
	    List<Documents> dList = new ArrayList<>();
	    
	    String UPLOAD_DIR_USER = UPLOAD_CLAIM_DIR + "/" + username;
	    try {
	        // Create user folder if it doesn't exist
	        Path userDirPath = Paths.get(UPLOAD_DIR_USER);
	        if (!Files.exists(userDirPath)) {
	            Files.createDirectories(userDirPath);
	        }

	        for (int i = 0; i < files.size(); i++) {
	            MultipartFile file = files.get(i);
	            Documents d = new Documents();

	            // Check file size
	            if (file.getSize() <= 5 * 1024 * 1024) { // 5MB limit in bytes
	                byte[] bytes = file.getBytes();

	                // set file name 
	                String fileName = username + "_" + i + "_" + claimId + "." + fileExtensions.get(i);
	                
	                Path path = Paths.get(UPLOAD_DIR_USER + "/" + fileName);
	                Files.write(path, bytes);
	                
	                d.setDocumentName(fileName);
	                d.setDocumentPath(UPLOAD_DIR_USER + "/" + fileName);
	                d.setExtensionType(fileExtensions.get(i));
	                dList.add(d);
	                dRepo.save(d);
	            } else {
	                return new ResponseEntity<>("File size exceeded limit (5MB)", HttpStatus.BAD_REQUEST);
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Error uploading files", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    return new ResponseEntity<>(dList, HttpStatus.CREATED);
	}

	// get the information of the document from local storage
    @GetMapping("/downloader")
    public ResponseEntity<?> downloader(@RequestParam String filePath) throws IOException { 
    	Path path = Paths.get(filePath);
    	byte[] bytes = Files.readAllBytes(path);
    	return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
    }

}
