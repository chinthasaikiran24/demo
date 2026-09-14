package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Repository.EmployeeRepository;
import Repository.EmployeesRepository;
import service.EmployeeService;
import Entity.Employee;


@RestController
@RequestMapping("/emp")
public class Employees {
	
	public EmployeeService empserv;
	
	@Autowired
	public EmployeesRepository emprep;
	
	public  Employees(EmployeesRepository emprep)
	{
		this.emprep=emprep;
	}
	
	

	
	@GetMapping("/all")
	public List<Employee> getAll(){
		return emprep.getAllEmployees();
	}
	
	@GetMapping("/{dept}")
	public List<Employee> getAll(@PathVariable String dept){
		return emprep.getEmpByDeptName(dept);
	}
	

}
