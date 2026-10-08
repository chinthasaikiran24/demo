package com.example.demo;

import Entity.Employee;
import Repository.EmployeeRepository;
import reactor.core.publisher.Mono;

import com.example.demo.DepartmentClient;
import com.example.demo.DepartmentWebClient;
import service.EmployeeService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    
    @Autowired
    private DepartmentWebClient departmentWebClient;
    
    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @GetMapping("/rabbit-test")
    public String rabbitTest() {

        rabbitMQProducer.sendEmployeeCreatedMessage(
                "Employee 101 created"
        );

        return "Message sent to RabbitMQ";
    }

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

//    @GetMapping("/department/{id}")
//    public CompletableFuture<String> getDept(@PathVariable long id) {
//
//        return departmentClient.getDepartment(id);
//    }
//    
    @GetMapping("/department/{id}")
    public String getDept(@PathVariable long id) {
        return departmentClient.getDepartment(id);
    }
    
    @GetMapping("/department-webclient/{id}")
    public Mono<String> getDepartmentWebClient(
            @PathVariable long id) {

        return departmentWebClient.getDepartment(id);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Employee> getAll() {

        return repository.findAll();
    }

    @PostMapping
    public Employee saveEmployee(
            @RequestBody EmployeeRequest request) {

        return service.saveEmployee(request);
    }
    
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request) {

        return service.updateEmployee(id, request);
    }
    
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        service.deleteEmployee(id);

        return "Employee " + id + " deleted successfully";
    }
}