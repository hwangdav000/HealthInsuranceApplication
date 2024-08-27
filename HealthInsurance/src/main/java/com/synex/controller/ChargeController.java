package com.synex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.synex.client.HealthClient;
import com.synex.domain.ChargeRequest;
import com.synex.domain.ChargeRequest.Currency;
import com.synex.repository.UserRepository;
import com.synex.services.EmailService;
import com.synex.services.StripeService;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;
    
    @Autowired
	HealthClient hclient;
    
    @Autowired
    private EmailService eService;
    
    @Autowired
    private UserRepository uRepo;

    
    @RequestMapping(value="/charge", method=RequestMethod.POST)
    public String charge(@RequestBody JsonNode node, Model model, Principal principal)
      throws StripeException, Exception {
    	System.out.println(node);
    	ChargeRequest c = new ChargeRequest();
    	c.setAmount(node.get("amount").asInt());
    	c.setCurrency(Currency.USD);
    	c.setStripeEmail(node.get("stripeEmail").asText());
    	c.setStripeToken(node.get("stripeToken").asText());
    	c.setDescription("plan payment purchase");
        
        Charge charge = paymentsService.charge(c);
        
        // check if charge is success
        if (charge != null) {
        	//save to policy db on success
        	int userId = (int) uRepo.findByUserName(principal.getName()).getUserId();
        	((ObjectNode) node).put("userId", userId);
        	hclient.savePolicyIssue(node);
        	
        	//send email of charge to user
        	eService.sendEmailWithPdf(node.get("stripeEmail").asText(), "", node);
        	
        	return "redirect:/result";
        } else {
        	throw new Exception("Exception occurred with Stripe charge");
        }
    }
    
    @GetMapping("/result")
    public String showResult(Model model) {
        return "result";
    }
    
    @GetMapping("/failure")
    public String showResultFailure(Model model) {
        
        return "resultFailure";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}