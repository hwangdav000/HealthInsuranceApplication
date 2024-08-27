package com.synex.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

@Entity
public class Claim {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int userId;
	private int policyId;
	private String recipientName;
	private LocalDate createdDate;
	
	private int claimId;
	
	@Enumerated(EnumType.STRING)
	private Status status=Status.PENDING;
	
	@OneToMany
	private List<ClaimItem> claimItems = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ClaimItem> getClaimItems() {
		return claimItems;
	}

	public void setClaimItems(List<ClaimItem> claimItems) {
		this.claimItems = claimItems;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", userId=" + userId + ", policyId=" + policyId + ", recipientName=" + recipientName
				+ ", createdDate=" + createdDate + ", claimId=" + claimId + ", status=" + status + ", claimItems="
				+ claimItems + "]";
	}
	
	
}
