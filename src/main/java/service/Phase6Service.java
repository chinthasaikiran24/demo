package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Entity.EmployeePhase6;
import Repository.EmployeeRepositoryPhase6;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Entity.EmployeePhase6;
import Repository.EmployeeRepositoryPhase6;

@Service
public class Phase6Service {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    @Autowired
    private EmployeeRepositoryPhase6 employeeRepository;

    public Phase6Service(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.xmlMapper = new XmlMapper();
    }

    public JsonNode getResult(String xml) throws Exception {

        // XML → JsonNode
        JsonNode request = xmlMapper.readTree(xml);

        // If Employee is the root object, move inside Employee
        if (request.has("Employee")) {
            request = request.get("Employee");
        }

        // Final response
        ObjectNode response = objectMapper.createObjectNode();
        response.put("status", "FAILED");

        // Stores all errors
        ArrayNode errors = objectMapper.createArrayNode();

        // We keep employeeId here so that we can use it later
        // after all validations pass
        Integer empid = null;

        // --------------------------------------------------
        // 1. EMPLOYEE ID VALIDATION
        // --------------------------------------------------

        JsonNode employeeIdNode = request.get("employeeId");

        if (employeeIdNode == null) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "employeeId");
            error.put("message", "Employee id is missing in request");

            errors.add(error);

        } else if (employeeIdNode.asText().trim().isEmpty()) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "employeeId");
            error.put("message", "Employee id is empty in request");

            errors.add(error);

        } else {

            try {

                empid = Integer.parseInt(employeeIdNode.asText().trim());

                if (empid <= 0) {

                    ObjectNode error = objectMapper.createObjectNode();

                    error.put("field", "employeeId");
                    error.put("message", "Employee id must be greater than 0");

                    errors.add(error);
                }

            } catch (NumberFormatException e) {

                ObjectNode error = objectMapper.createObjectNode();

                error.put("field", "employeeId");
                error.put("message", "Employee id must be an integer");

                errors.add(error);
            }
        }

        // --------------------------------------------------
        // 2. NAME VALIDATION
        // --------------------------------------------------

        JsonNode nameNode = request.get("name");

        if (nameNode == null) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "name");
            error.put("message", "Name is missing in request");

            errors.add(error);

        } else if (nameNode.asText().trim().isEmpty()) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "name");
            error.put("message", "Name is empty in request");

            errors.add(error);
        }

        // --------------------------------------------------
        // 3. AGE VALIDATION
        // --------------------------------------------------

        JsonNode ageNode = request.get("age");

        if (ageNode == null) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "age");
            error.put("message", "Age is missing in request");

            errors.add(error);

        } else if (ageNode.asText().trim().isEmpty()) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "age");
            error.put("message", "Age is empty in request");

            errors.add(error);

        } else {

            try {

                int age = Integer.parseInt(ageNode.asText().trim());

                if (age < 18) {

                    ObjectNode error = objectMapper.createObjectNode();

                    error.put("field", "age");
                    error.put("message", "Age must be 18 or greater");

                    errors.add(error);
                }

            } catch (NumberFormatException e) {

                ObjectNode error = objectMapper.createObjectNode();

                error.put("field", "age");
                error.put("message", "Age must be an integer");

                errors.add(error);
            }
        }

        // --------------------------------------------------
        // 4. STOP IF INPUT VALIDATION FAILED
        // --------------------------------------------------

        if (errors.size() > 0) {

            response.set("errors", errors);

            return response;
        }

        // --------------------------------------------------
        // 5. DATABASE VALIDATION
        // --------------------------------------------------

        Optional<EmployeePhase6> employee =
                employeeRepository.findById(empid);

        if (employee.isEmpty()) {

            ObjectNode error = objectMapper.createObjectNode();

            error.put("field", "employeeId");
            error.put("message", "Employee does not exist");

            errors.add(error);

        } else {

            // Employee found in database
            EmployeePhase6 emp = employee.get();

            response.put("status", "SUCCESS");
            response.put("employeeId", emp.getEmployeeId());
            response.put("name", emp.getName());
            response.put("age", emp.getAge());
        }

        // --------------------------------------------------
        // 6. RETURN FINAL RESPONSE
        // --------------------------------------------------

        if (errors.size() > 0) {

            response.set("errors", errors);
        }

        return response;
    }
    
    public EmployeePhase6  saveEmployee(EmployeePhase6 emp) {
    	EmployeePhase6 savedEmp = employeeRepository.save(emp);
    	return savedEmp;
    	
    }
    
    public EmployeePhase6  getEmployee(int empid) {
    	EmployeePhase6 getEmp = employeeRepository.findById(empid)
    			.orElseThrow(() -> new RuntimeException("Employee not Found"));
    	return getEmp;
    	
    }
    
    public EmployeePhase6 updateEmployee(int empId,EmployeePhase6 employee) {
    	
    	EmployeePhase6 empl = employeeRepository.findById(empId)
    			.orElseThrow();
    	
    	empl.setEmployeeId(employee.getEmployeeId());
    	empl.setAge(employee.getAge());
    	empl.setName(employee.getName());
    	
    	EmployeePhase6 updatedEmployee =
                employeeRepository.save(empl);
    	
    	return empl;
    	
    }
    
    public void deleteEmployee(Integer employeeId) {
    	
    	EmployeePhase6 emplo = employeeRepository.findById(employeeId)
    			 .orElseThrow();
    	employeeRepository.delete(emplo);
    }
    
}