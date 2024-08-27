package com.synex.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.synex.domain.PolicyIssue;
import com.synex.domain.PolicyPlan;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;

import com.itextpdf.layout.property.TextAlignment;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
    	this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    @Async
    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            
            // Add attachment
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment("policyConfirmation.pdf", file);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    @Async
    public void sendEmailWithPdf(String email, String imageURL, PolicyPlan plan, String status) {
        // Creating a PdfWriter       
        String dest = "C:/SynergisticIT/HealthInsuranceReceipts/policyConfirmation.pdf";
        
    	int policyId = plan.getPolicyId();
    	String name = plan.getName();
    	String description = plan.getDescription();
    	
    	int coverageLength = plan.getCoverageLength();
    	double coInsurance = plan.getCoInsurance();
    	double coPay = plan.getCoPay();
    	double deductible = plan.getDeductible();
    	double outOfPocket = plan.getOutOfPocket();
    	double basePremium = plan.getBasePremium();
    	double planLimit = plan.getPlanLimit();
        
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
        // Creating a PdfDocument       
        PdfDocument pdf = new PdfDocument(writer);              
        
        // Creating a Document        
        Document document = new Document(pdf);  
        
        // Add title
        Paragraph title = new Paragraph("Health Plan Details")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);
        
        // Add booking details
        Paragraph PurchaseDetailsTitle = new Paragraph("Information")
                .setFontSize(16)
                .setBold()
                .setMarginTop(20);
        document.add(PurchaseDetailsTitle );
        
        Table table = new Table(new float[]{1, 2});
        table.setWidth(UnitValue.createPercentValue(100));
        table.addCell(createCell("Health Plan Name:"));
        table.addCell(createCell(name));
        table.addCell(createCell("Health Plan Id:"));
        table.addCell(createCell(String.valueOf(policyId)));
        table.addCell(createCell("Description:"));
        table.addCell(createCell(description));
        table.addCell(createCell("Coverage Length:"));
        table.addCell(createCell(coverageLength + " months"));
        table.addCell(createCell("Base Premium:"));
        table.addCell(createCell("$" + basePremium));
        table.addCell(createCell("coInsurance:"));
        table.addCell(createCell(coInsurance + "%"));
        table.addCell(createCell("coPay:"));
        table.addCell(createCell(coPay + "%"));
        table.addCell(createCell("deductible:"));
        table.addCell(createCell(deductible+ "%"));
        table.addCell(createCell("Out of Pocket:"));
        table.addCell(createCell(outOfPocket + "%"));
        table.addCell(createCell("Plan Limit:"));
        table.addCell(createCell("$" + planLimit));
        
        document.add(table);

        // Add thank you note
        Paragraph endNote = new Paragraph("Your plan has been " + status)
                .setFontSize(14)
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(endNote);
        
        // Closing the document       
        document.close();
        
        // Send the document via email
        String subject = "Health Insurance Plan Status";
        String body = "Dear valued customer," + "\n\nPlease find your plan status details in document attached.";
        sendEmailWithAttachment(email, subject, body, dest);
    }
    
    private Cell createCell(String content) {
        return new Cell()
                .add(new Paragraph(content))
                .setPadding(5)
                .setBorder(null);
    }
}
