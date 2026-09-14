package service;

import Entity.*;
import Repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import DTO.EmployeeRequest;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final LockerRepository lockerRepository;

    @Autowired
    private WebClient webClient;
    
    public EmployeeService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            ProjectRepository projectRepository,
            LockerRepository lockerRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.lockerRepository = lockerRepository;
    }
    
    public Department getEmpDetails(long id) {
    	return webClient.get().uri("http://localhost:8082/departments/{id}",id).retrieve().bodyToMono(Department.class).block();
    }

    public Employee saveEmployee(EmployeeRequest request) {

        Department department =
                departmentRepository
                        .findById(request.getDepartmentId())
                        .orElseThrow();

        List<Project> projects =
                projectRepository
                        .findAllById(request.getProjectIds());

        Locker locker =
                lockerRepository
                        .findById(request.getLockerId())
                        .orElseThrow();

        Employee employee = new Employee();

        employee.setName(request.getEmployeeName());
        employee.setSalary(request.getSalary());

        employee.setDepartment(department);
        employee.setProjects(projects);

        employee.setLocker(locker);

        return employeeRepository.save(employee);
    }
}