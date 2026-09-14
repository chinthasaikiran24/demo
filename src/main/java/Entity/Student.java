package Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank(message="Name cannot be blank")
	public String name;
	
	private LocalDateTime timestamp;
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp=timestamp;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	
	public long getId() {
		return id;
	}
	

}
