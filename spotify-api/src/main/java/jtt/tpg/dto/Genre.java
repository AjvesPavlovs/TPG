package jtt.tpg.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
//This class is needed to create artist object that saves info 

@Entity
@Table(name = "genres")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	public Genre() {}
	
	public Genre(String name) {this.name = name;}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}
}