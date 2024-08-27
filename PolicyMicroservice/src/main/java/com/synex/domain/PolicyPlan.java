package com.synex.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class PolicyPlan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int policyId;
	private String name;
	private String description;
	private int rating;
	private int coverageLength;
	private double coInsurance;
	private double coPay;
	private double deductible;
	private double outOfPocket;
	private double basePremium;
	private double planLimit;
	private String imageURL;
	
	@Enumerated(EnumType.STRING)
	private MetalTier metalTier;
	
	@Enumerated(EnumType.STRING)
	private PolicyType type;
	
	public double getCoInsurance() {
		return coInsurance;
	}
	public void setCoInsurance(double coInsurance) {
		this.coInsurance = coInsurance;
	}
	public double getCoPay() {
		return coPay;
	}
	public void setCoPay(double coPay) {
		this.coPay = coPay;
	}
	public double getDeductible() {
		return deductible;
	}
	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}
	public double getOutOfPocket() {
		return outOfPocket;
	}
	public void setOutOfPocket(double outOfPocket) {
		this.outOfPocket = outOfPocket;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public MetalTier getMetalTier() {
		return metalTier;
	}
	public void setMetalTier(MetalTier metalTier) {
		this.metalTier = metalTier;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MetalTier getCategory() {
		return metalTier;
	}
	public void setCategory(MetalTier metalTier) {
		this.metalTier = metalTier;
	}
	public PolicyType getType() {
		return type;
	}
	public void setType(PolicyType type) {
		this.type = type;
	}
	public double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}

	public int getCoverageLength() {
		return coverageLength;
	}
	public void setCoverageLength(int coverageLength) {
		this.coverageLength = coverageLength;
	}
	public double getPlanLimit() {
		return planLimit;
	}
	public void setPlanLimit(double planLimit) {
		this.planLimit = planLimit;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
