package Entity;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Value;

@Entity
@Table(name="Worker")
public class Worker {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Email
	String email;
	
	@Min(value=18)
	int age;
	
	public Worker(int id,String email,int age ) {
		this.id=id;
		this.email=email;
		this.age=age;

	}
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public int getAge() {
		return age;
	}


}
