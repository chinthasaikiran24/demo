package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import Entity.Student;
import jakarta.validation.Valid;
import DTO.StudentDto;
import service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	
	
	public StudentService stuService;
	
	public StudentController(StudentService stuService ) {
		this.stuService=stuService;
	}

	
	@GetMapping("/get")
	public ResponseEntity<?> getStudent(@RequestParam long id, @RequestParam String name) {
		try {
		
		System.out.println("ID = " + id);
		    System.out.println("Name = " + name);
		    
		Student student = stuService.getEmployee(id);
		
		if(!student.getName().equalsIgnoreCase(name)) {
			return ResponseEntity.badRequest().body("Name does not Match");
		}
		
		if(student == null) {
			return ResponseEntity.notFound().build();
		}
	return ResponseEntity.ok(student);
	}
	catch(Exception e) {
		return ResponseEntity.internalServerError().body("server issue");
	}
	
	}
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid Student student) {
		stuService.register(student);
	return ResponseEntity.status(201).body("User Created");
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<Student>> getAll(@RequestParam String name ,@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(stuService.getAll(name,page,size));
	}
	
	
	@PutMapping("/update")
	public String update(@RequestBody Student student) {
		stuService.update(student);
		return "update is done";
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody Student student) {
		stuService.delete(student);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/patch")
	public void patch(long id, String name) {
		stuService.patchName(id, name);
	}
	
	@GetMapping(path="/{id}", headers="API-VERSION=1")
	public ResponseEntity<?> getting(@PathVariable long id,@RequestHeader Map<String, String> headers) {
		
		  headers.forEach((k, v) ->
	        System.out.println(k + " = " + v));
		  
		  
		String User = headers.get("User-agent");
		if(User ==null || !User.equals("PostmanRuntime/7.54.0")) {
			return ResponseEntity.badRequest().body("Invalid Header User-Agent");
		}else {
		Student student = stuService.getEmployee(id);
			
		StudentDto stu = new StudentDto(student.getId(),student.getName());
		
		
		return ResponseEntity.ok(stuService.getEmployee(id));
		}
	}
}
