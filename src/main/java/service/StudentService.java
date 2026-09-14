package service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Repository.StudentRepository;
import Entity.Student;

@Service
public class StudentService {

	private final StudentRepository repository;

    public StudentService(
    		StudentRepository repository) {

        this.repository = repository;
    }

    public Student getEmployee(Long id) {

        return repository.findById(id)
                .orElse(null);
    }
    
    public Page<Student> getAll(String name,int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
    	return repository.findByName(name,pageable);
    }
    
    public void register(Student student) {
    	try {
    	repository.save(student);
    	}
    	catch(Exception e){
    		throw new RuntimeException("User not created ");
    	}
    }
    
    public void update(Student student) {
    	repository.save(student);
    }
    
    public void delete(Student student) {
    	repository.delete(student);
    }
    
    public Student patchName(Long id, String name) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(name);

        return repository.save(student);
    }
}
