package Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String projectName;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }
    
    public void setId(Long id) {
    	this.id=id;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setProjectName(String projectName) {
    	this.projectName=projectName;
    }
    
    public String getProjectName() {
    	return projectName;
    }

    // Getters and Setters
}