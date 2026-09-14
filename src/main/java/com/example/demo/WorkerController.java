package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Entity.Worker;
import service.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {

	
	
	public final WorkerService wk;
	
	@Autowired
	public WorkerController(WorkerService wk) {
		this.wk=wk;
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> savewk(@RequestBody Worker worker){
		
		wk.saveWorker(worker);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Worker is created");
		
		
	}
	
	@GetMapping("/get/{id}")
	public Worker getEmp(@RequestParam long id){
		return wk.getById(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Worker>> getAll(){
		List<Worker> wok = wk.list();
		
		//ApiResponse ap = new ApiResponse("Employee Details fetched successfully",wok);
		return ResponseEntity.ok(wok);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteWk(@RequestParam long id){
		wk.deletebyId(id);
		
	}
	
}
