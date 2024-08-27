package com.synex.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Documents {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int documentId;
	
	private String documentName;
	private String documentPath;
	private String extensionType;

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
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

	public String getExtensionType() {
		return extensionType;
	}

	public void setExtensionType(String extensionType) {
		this.extensionType = extensionType;
	}
	
}
