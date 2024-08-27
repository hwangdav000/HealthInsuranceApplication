package com.synex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synex.domain.ChargeRequest;

@Controller
public class CheckoutController {

    private String stripePublicKey = "pk_test_51POOFDHmrVfPtsK1QNR3ZrPVjf9dXcFQgYdMHdpYIImPdbzUf8wHLwwkdF31pNGMDNHAkIA1qyds81WicSAexkpc00o1pGblpz";

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 10); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
    
    @RequestMapping("/result")
    public String result(Model model) {
        return "result";
    }
}