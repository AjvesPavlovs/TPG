package jtt.tpg.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
/**
 * This class is needed to create Artist object that saves info 
 */
@Entity
@Table(name = "artists")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	private int followers;
	private int popularity;
	
	public Artist() {}

	/**
	 * 
	 * Constructor
	 * @param name - The name of the artist.
	 * @param followers - The total number of followers.
	 * @param popularity - The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular.
	 */
	public Artist(String name, int followers, int popularity) {
		this.name = name;
		this.followers = followers;
		this.popularity = popularity;
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
	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	} 
	
	
}
