package other;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Entity.Department;

@FeignClient(
    name = "department-service",
    url = "http://localhost:8082"
)
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    Department getDepartment(@PathVariable Long id);

}