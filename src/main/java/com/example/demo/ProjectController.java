package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import Entity.Project;
import Repository.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository repository;
    

    public ProjectController(
            ProjectRepository repository) {

        this.repository = repository;
    }
    
    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Project save(
            @Valid @RequestBody Project project) {

        return repository.save(project);
    }
}