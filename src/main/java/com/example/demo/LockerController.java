package com.example.demo;

import org.springframework.web.bind.annotation.*;

import Entity.Locker;
import Repository.LockerRepository;

@RestController
@RequestMapping("/lockers")
public class LockerController {

    private final LockerRepository repository;

    public LockerController(LockerRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Locker save(@RequestBody Locker locker) {
        return repository.save(locker);
    }

    @GetMapping("/{id}")
    public Locker getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}