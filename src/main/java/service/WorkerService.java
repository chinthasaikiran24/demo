package service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Worker;
import Repository.WorkerRepository;

@Service
public class WorkerService {
	
	@Autowired
	public WorkerRepository wk;
	
	public WorkerService(WorkerRepository wk) {
		this.wk=wk;
	}
	
	
	public void saveWorker(Worker worker) {
		 wk.save(worker);
	}
	
	public List<Worker> list(){
		return wk.findAll();
	}
	
	public Worker getById(long id) {
		return wk.getById(id);
	}

	public void deletebyId(long id) {
		
		 wk.deleteById(id);
	}
	
}
