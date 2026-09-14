package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.Department;
import Repository.DepartmentRepository;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository repository;

    public DepartmentController(
            DepartmentRepository repository) {
        this.repository = repository;
    }
    
    
    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }
    
    @PostMapping
    public Department saveDepartment(
            @RequestBody Department department){

        return repository.save(department);
    }
}