package Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Student;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

	Page<Student> findByName(String name, Pageable pagebale);
}