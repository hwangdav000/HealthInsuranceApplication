package com.synex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.synex.services.EmailService;

@SpringBootApplication
public class HealthInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthInsuranceApplication.class, args);

    }

}
