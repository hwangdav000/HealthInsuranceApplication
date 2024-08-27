package com.synex.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.MetalTier;
import com.synex.domain.PolicyIssue;
import com.synex.domain.PolicyPlan;
import com.synex.domain.PolicyType;
import com.synex.services.PolicyPlanService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PolicyController {

	@Autowired
	PolicyPlanService pService;
	
	@RequestMapping(value = "/getPolicyPlans", method= RequestMethod.GET)
	public List<PolicyPlan> getPolicyPlans() {
		return pService.getPolicyPlans();
	}
	
	@RequestMapping(value = "/getMetalTiers", method= RequestMethod.GET)
	public List<MetalTier> getMetalTiers() {
		return Arrays.asList(MetalTier.values());
	}
	
	@RequestMapping(value = "/getPlanTypes", method= RequestMethod.GET)
	public List<PolicyType> getPlanTypes() {
		return Arrays.asList(PolicyType.values());
	}
	
	@RequestMapping(value="/savePolicyIssue",method=RequestMethod.POST)
	public PolicyIssue savePolicyIssue(@RequestBody JsonNode node) {
		return pService.savePolicyIssue(node);
	}
	
	@RequestMapping(value="/setStatus",method=RequestMethod.POST)
	public PolicyIssue setStatus(@RequestBody JsonNode node) {
		return pService.setStatus(node);
	}
	
	@RequestMapping(value = "/getPolicyIssues", method= RequestMethod.GET)
	public List<PolicyIssue> getPolicyIssues() {
		return pService.getPolicyIssues();
	}
	
	
	@RequestMapping(value = "/getPolicyIssues/{userId}", method= RequestMethod.GET)
	public List<PolicyIssue> getPolicyIssues2(@PathVariable int userId) {
		return pService.getPolicyIssuesById(userId);
	}
	
	@RequestMapping(value = "/filterPlans", method=RequestMethod.POST)
	public List<PolicyPlan> filterHotel(@RequestBody JsonNode json) {
		List<PolicyPlan> filteredList = new ArrayList<>();
		
		String status = json.get("status").asText();
		
		// is status reset then just sened all policy plans
		if (status.equals("RESET")) {
			return pService.getPolicyPlans();
		}
		
		String searchString = json.get("searchString").asText();
		String rating = json.get("ratings").asText();
		
		// if no ratings then default assume all ratings
		String[] ratingArray;
		if (rating.equals("")) {
			ratingArray = new String[] {"1", "2", "3", "4", "5"};
		} else {
			ratingArray = rating.split(",");
		}
		
		String tiers = json.get("tiers").asText();
		String planTypes = json.get("planTypes").asText();
		List<PolicyPlan> listPlan = new ArrayList<>();
		int amount = json.get("amount").asInt();
		if (searchString.equals("")) {
			listPlan = pService.getPolicyPlans();
		} else {
			listPlan = pService.searchLikePlan(searchString);
		}
		
		Iterator<PolicyPlan> itr = listPlan.iterator();
		
		String[] tiersArray;
		String[] planTypesArray;
		if (!tiers.equals("")) {
			tiersArray = tiers.split(",");	
		} else {
			tiersArray = null;
		}
		
		if (!planTypes.equals("")) {
			planTypesArray = planTypes.split(",");	
		} else {
			planTypesArray = null;
		}
		
		// check each plan and see if it matches filter
		while (itr.hasNext()) {
			PolicyPlan plan = itr.next();
			
			Boolean tiersFilter = true;
			Boolean planTypesFilter = true;
			Set<String> tiersSet = new HashSet<>();
			Set<String> planTypesSet = new HashSet<>();
			
			// check if plan metal tier and plan type is contained within filter
			if (tiersArray != null) {
				tiersSet = new HashSet<>(Arrays.asList(tiersArray)); 
			}
			if (planTypesArray != null) {
				planTypesSet = new HashSet<>(Arrays.asList(planTypesArray));
				
			}

			if (tiersArray != null && !tiersSet.contains(plan.getMetalTier().name())) {
				tiersFilter=false;
			}
			if (planTypesArray != null && !planTypesSet.contains(plan.getType().name())) {
				planTypesFilter=false;
				
			}
			
			// pricing and rating logic
			if (plan.getBasePremium() <= amount) {
				for (String rat : ratingArray) {
					int ratingInt = Integer.parseInt(rat);
					
					// check if the star rating is correct and has required filter checks
					if (ratingInt == plan.getRating() && tiersFilter && planTypesFilter) {
						filteredList.add(plan);
					}
				}
			}
		}
		
		return filteredList;
	}
}
