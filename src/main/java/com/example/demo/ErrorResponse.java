package com.example.demo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	

	
	public long id;
	public String errorMessage;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
private LocalDateTime timestamp;
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp=timestamp;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
	  public ErrorResponse(long id, String errorMessage,LocalDateTime timestamp) {
	        this.id = id;
	        this.errorMessage = errorMessage;
	        this.timestamp=timestamp;
	    }
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;		
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage=errorMessage;
	}
}
