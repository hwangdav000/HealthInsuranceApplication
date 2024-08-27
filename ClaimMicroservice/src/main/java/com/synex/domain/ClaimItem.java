package com.synex.domain;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClaimItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate serviceDateStart;
	private LocalDate serviceDateEnd;
	private String placeOfService;
	private String CPTCode;
	private String Modifiers;
	private String Diagnosis;
	private double charge;
	private String documentName;
	private String documentPath;
	
	@Enumerated(EnumType.STRING)
	private Status status=Status.PENDING;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getServiceDateStart() {
		return serviceDateStart;
	}
	public void setServiceDateStart(LocalDate serviceDateStart) {
		this.serviceDateStart = serviceDateStart;
	}
	public LocalDate getServiceDateEnd() {
		return serviceDateEnd;
	}
	public void setServiceDateEnd(LocalDate serviceDateEnd) {
		this.serviceDateEnd = serviceDateEnd;
	}
	public String getPlaceOfService() {
		return placeOfService;
	}
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	public String getCPTCode() {
		return CPTCode;
	}
	public void setCPTCode(String cPTCode) {
		CPTCode = cPTCode;
	}
	public String getModifiers() {
		return Modifiers;
	}
	public void setModifiers(String modifiers) {
		Modifiers = modifiers;
	}
	public String getDiagnosis() {
		return Diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		Diagnosis = diagnosis;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ClaimItem [id=" + id + ", serviceDateStart=" + serviceDateStart + ", serviceDateEnd=" + serviceDateEnd
				+ ", placeOfService=" + placeOfService + ", CPTCode=" + CPTCode + ", Modifiers=" + Modifiers
				+ ", Diagnosis=" + Diagnosis + ", charge=" + charge + ", documentName=" + documentName + "]";
	}
	
	
	
}
