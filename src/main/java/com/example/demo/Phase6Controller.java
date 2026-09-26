package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import Entity.EmployeePhase6;
import service.Phase6Service;

@RestController
public class Phase6Controller {

    @Autowired
    private Phase6Service phase6Service;

    @PostMapping(
        value = "/phase6/employee",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    
    public JsonNode getEmployee(@RequestBody String xml) throws Exception {
        return phase6Service.getResult(xml);
    }
    @PostMapping("/phase6/save")
    public EmployeePhase6 saveEmployee(
            @RequestBody EmployeePhase6 employee) {

        return phase6Service.saveEmployee(employee);
    }
    
    @GetMapping("/phase6/get/{empid}")
    public EmployeePhase6 saveEmployee(@PathVariable
           int empid) {

        return phase6Service.getEmployee(empid);
    }
    
    @PutMapping("/employees/{id}")
    public EmployeePhase6 updateEmployee(
            @PathVariable Integer id,
            @RequestBody EmployeePhase6 employee) {

        return phase6Service.updateEmployee(id, employee);
    }
    
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(
            @PathVariable Integer id) {

        phase6Service.deleteEmployee(id);

        return "Employee deleted successfully";
    }
    
}