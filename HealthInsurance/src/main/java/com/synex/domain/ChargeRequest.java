package com.synex.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        EUR, USD;
    }
    private String description;
    private String providerName;
    private LocalDate coverageStart;
    private LocalDate coverageEnd;
    private int premium;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public String getStripeEmail() {
		return stripeEmail;
	}
	public void setStripeEmail(String stripeEmail) {
		this.stripeEmail = stripeEmail;
	}
	public String getStripeToken() {
		return stripeToken;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
	@Override
	public String toString() {
		return "ChargeRequest [description=" + description + ", amount=" + amount + ", currency=" + currency
				+ ", stripeEmail=" + stripeEmail + ", stripeToken=" + stripeToken + "]";
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public LocalDate getCoverageStart() {
		return coverageStart;
	}
	public void setCoverageStart(LocalDate coverageStart) {
		this.coverageStart = coverageStart;
	}
	public LocalDate getCoverageEnd() {
		return coverageEnd;
	}
	public void setCoverageEnd(LocalDate coverageEnd) {
		this.coverageEnd = coverageEnd;
	}
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	
    
    
}