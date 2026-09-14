package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Entity.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    @Query(value = """
            SELECT e.*
            FROM employee e
            JOIN department d ON e.department_id = d.id
            WHERE d.name = :deptName
            """, nativeQuery = true)
    List<Employee> getEmpByDeptName(@Param("deptName") String deptName);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployees();
}