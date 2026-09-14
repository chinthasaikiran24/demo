package com.example.demo;

import Entity.Department;
import Entity.Employee;
import Repository.EmployeeRepository;
import other.DepartmentClient;
import service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import DTO.EmployeeRequest;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartmentClient departmentClient;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    @GetMapping("/department/{id}")
    public Department getDept(@PathVariable long id) {   	
   	return departmentClient.getDepartment(id);
    }
    
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }
    
    @GetMapping("/all")
    	public List<Employee> getAll(){
    		return repository.findAll();
    	}
  

    @PostMapping
    public Employee saveEmployee(
            @RequestBody EmployeeRequest request) {

        return service.saveEmployee(request);
    }
}