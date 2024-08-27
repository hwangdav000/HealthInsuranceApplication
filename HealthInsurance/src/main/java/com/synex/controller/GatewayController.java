package com.synex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synex.domain.ChargeRequest;

@Controller
public class GatewayController {

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/introPlans", method = RequestMethod.GET)
	public String introPlans() {
		return "introPlans";
	}
	
	@RequestMapping(value="/approvePolicy", method = RequestMethod.GET)
	public String approvePolicy() {
		return "approvePolicy";
	}
	
	@RequestMapping(value="/claim", method = RequestMethod.GET)
	public String claim() {
		return "claim";
	}
	
	@RequestMapping(value="/approveClaim", method = RequestMethod.GET)
	public String approveClaim() {
		return "approveClaim";
	}
	
	@RequestMapping(value="/viewClaims", method = RequestMethod.GET)
	public String viewClaims() {
		return "viewClaims";
	}
	
	@RequestMapping("/account")
    public String account(Model model) {
        return "account";
    }
	
	private String stripePublicKey = "pk_test_51POOFDHmrVfPtsK1QNR3ZrPVjf9dXcFQgYdMHdpYIImPdbzUf8wHLwwkdF31pNGMDNHAkIA1qyds81WicSAexkpc00o1pGblpz";
	
	@RequestMapping(value="/viewPlans", method = RequestMethod.GET)
	public String viewPlans(Model model) {
		
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
		return "viewPlans";
	}
}
