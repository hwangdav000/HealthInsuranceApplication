package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.Claim;
import com.synex.services.ClaimService;

@RestController
public class ClaimController {
	@Autowired
	ClaimService cServ;
	
	@RequestMapping(value="/getClaimCount/{userId}", method=RequestMethod.GET)
	public int getClaimCount(@PathVariable int userId) {
		return cServ.getUserClaimCount(userId);
	}
	
	@RequestMapping(value="/saveClaim", method=RequestMethod.POST)
	public Claim getClaimCount(@RequestBody JsonNode node) {
		return cServ.saveClaim(node);
	}
	
	@RequestMapping(value="/getClaims", method=RequestMethod.GET)
	public List<Claim> getClaims() {
		return cServ.getClaims();
	}
	
	@RequestMapping(value="/getClaims/{userId}", method=RequestMethod.GET)
	public List<Claim> getClaimsByUserId(@PathVariable int userId) {
		return cServ.getClaimsByUserId(userId);
	}
	
	@RequestMapping(value="/getClaim/{id}", method=RequestMethod.GET)
	public Claim getClaim(@PathVariable int id) {
		return cServ.getClaimsById(id);
	}
	
	@RequestMapping(value="/setClaimStatus", method=RequestMethod.POST)
	public Claim setClaimStatus(@RequestBody JsonNode node) {
		return cServ.setClaimStatus(node);
	}
	
	@RequestMapping(value="/updateClaimItems", method=RequestMethod.POST)
	public Claim updateClaimItems(@RequestBody JsonNode node) {
		return cServ.updateClaimItems(node);
	}
}
