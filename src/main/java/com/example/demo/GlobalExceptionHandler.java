package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.*;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleError(MethodArgumentNotValidException ex){
		
		String error = ex.getBindingResult()
				.getFieldError()
				.getDefaultMessage();
		
		ErrorResponse er = new ErrorResponse(400,error,LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(er);
		
	}
}