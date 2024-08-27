package com.synex.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.synex.domain.ChargeRequest;

import jakarta.annotation.PostConstruct;

@Service
public class StripeService {


    private String secretKey = "sk_test_51POOFDHmrVfPtsK1Yg655mekCn8SARLY0gdBWiFhU2850Gc6abNY01lGRZYYVE9UHd12Yh6WxVq2yury8NTqvLDq00hYL4lv1A";
    
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    public Charge charge(ChargeRequest chargeRequest) 
      throws AuthenticationException, InvalidRequestException,
        APIConnectionException, CardException, APIException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}