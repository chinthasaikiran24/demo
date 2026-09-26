package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import DTO.EmployeeRequestClass;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Phase5Entity")
public class Phase5Entity {

    private final ObjectMapper objectMapper;

    public Phase5Entity(ObjectMapper objectMapper) {
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

    	
    	BindingResult result = ex.getBindingResult();
    	
    	ObjectNode response = objectMapper.createObjectNode();
    	response.put("Status", "Failed");
    	
    	ArrayNode error = objectMapper.createArrayNode();
    	
    	for(FieldError  errors : result.getFieldErrors()) {
    		
    		String errorField = errors.getField();
    		String errorMessage = errors.getDefaultMessage();
    		
    		 ObjectNode errorObject = objectMapper.createObjectNode();
    		
    		 errorObject.put("Field", errorField);
    		 errorObject.put("Message", errorMessage);
    		 
    		 error.add(errorObject);
    		
    	}
    	response.set("errors", error);

        return ResponseEntity.badRequest().body(response);
    }
}