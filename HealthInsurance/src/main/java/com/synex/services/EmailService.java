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
            helper.addAttachment("purchase.pdf", file);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    @Async
    public void sendEmailWithPdf(String email, String imageURL, JsonNode node) {
        // Creating a PdfWriter       
        String dest = "C:/SynergisticIT/HealthInsuranceReceipts/purchase.pdf";
        
        String planName = node.get("planName").asText();
        int planId = node.get("planId").asInt();
        String startDate = node.get("startDate").asText();
        String coverageLength = node.get("coverageLength").asText();
        float amount = (float) node.get("amount").asDouble();
        
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
        Paragraph title = new Paragraph("Purchase Confirmation")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);
        
        // Add purchase details
        Paragraph PurchaseDetailsTitle = new Paragraph("Purchase Details")
                .setFontSize(16)
                .setBold()
                .setMarginTop(20);
        document.add(PurchaseDetailsTitle );
        
        Table table = new Table(new float[]{1, 2});
        table.setWidth(UnitValue.createPercentValue(100));
        table.addCell(createCell("Health Plan Name:"));
        table.addCell(createCell(planName));
        table.addCell(createCell("Health Plan Id:"));
        table.addCell(createCell(String.valueOf(planId)));
        table.addCell(createCell("Prospective Start Date:"));
        table.addCell(createCell(startDate));
        table.addCell(createCell("Coverage Length:"));
        table.addCell(createCell(coverageLength + " months"));
        table.addCell(createCell("Amount Paid:"));
        table.addCell(createCell("$" + amount));
        
        document.add(table);

        // Add end note
        Paragraph endNote = new Paragraph("Please submit the relevant user documents on your account!")
                .setFontSize(14)
                .setMarginTop(20)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(endNote);
        
        // Closing the document       
        document.close();
        
        // Send the document via email
        String subject = "Your Purchase Confirmation";
        String body = "Dear valued customer," + "\n\nPlease find attached your purchase confirmation below. \n Please enter your documents in account to complete user registration\n\nThank you!";
        sendEmailWithAttachment(email, subject, body, dest);
    }
    
    private Cell createCell(String content) {
        return new Cell()
                .add(new Paragraph(content))
                .setPadding(5)
                .setBorder(null);
    }
}
