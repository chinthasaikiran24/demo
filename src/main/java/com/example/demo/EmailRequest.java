package com.example.demo;

public class EmailRequest  {

    private String emailAddress;
    private String message;
    private String subject;
    
    public String getSubject() {
    	return subject;
    }
    
    public void setSubject(String subject) {
    	this.subject=subject;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}