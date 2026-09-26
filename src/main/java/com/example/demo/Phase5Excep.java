package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import DTO.EmployeeRequest;
import DTO.EmployeeRequestClass;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Phase5Excep")
public class Phase5Excep {

    private final ObjectMapper objectMapper;

    public Phase5Excep(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(
            @Valid @RequestBody EmployeeRequestClass request) {

        return ResponseEntity.ok(request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException ex) {
    	
    	ObjectNode response = objectMapper.createObjectNode();
    	response.put("status", "FAILED");
    	 ArrayNode errors = objectMapper.createArrayNode();

        BindingResult result = ex.getBindingResult();
        
        for(FieldError error : result.getFieldErrors() ) {
        	
        	String filedName = error.getField();
        	String fieldValue = error.getDefaultMessage();
        	
        	ObjectNode obj = objectMapper.createObjectNode();
        	
        	obj.put("Field:- ", filedName);
        	obj.put("Error ", fieldValue);
        	
        	errors.add(obj);
        	      	
        }
        response.put("Errors ", errors);

        return ResponseEntity.badRequest().body(response);
    }
}