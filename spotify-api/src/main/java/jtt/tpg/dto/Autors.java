package jtt.tpg.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autors")
public class Autors {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public Autors() {}

	public Autors(String name) {
		this.name = name;
	}

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
