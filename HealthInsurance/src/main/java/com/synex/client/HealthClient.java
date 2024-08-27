package com.synex.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HealthClient {
	private static final String policyMicroURL= "http://localhost:8484/";
	private static final String claimMicroURL= "http://localhost:8383/";
	
	public JsonNode updateClaimItems(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(claimMicroURL + "updateClaimItems", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode setClaimStatus(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(claimMicroURL + "setClaimStatus", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getClaimsById(int userId) {
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity(claimMicroURL + "getClaims/" + userId, Object.class);
			
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
	}
	
	public JsonNode getClaims() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(claimMicroURL + "getClaims", Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	
	public JsonNode getClaimCount(int userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(claimMicroURL + "getClaimCount/" + userId, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode saveClaim(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(claimMicroURL + "saveClaim", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	
	public JsonNode savePolicyIssue(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(policyMicroURL + "savePolicyIssue", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode setPolicyStatus(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(policyMicroURL + "setStatus", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode filterPlans(JsonNode node) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(node.toString(), headers);	
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(policyMicroURL + "filterPlans", request, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	

	public JsonNode getPolicyPlans() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(policyMicroURL + "getPolicyPlans", Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getPolicyIssues() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(policyMicroURL + "getPolicyIssues", Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getPolicyIssues2(int userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(policyMicroURL + "getPolicyIssues/" + userId, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getMetalTiers() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(policyMicroURL + "getMetalTiers", Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getPlanTypes() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(policyMicroURL + "getPlanTypes", Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	
}