package jtt.tpg.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
//This class is needed to create artist object that saves info 

@Entity
@Table(name = "artists")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	private int followers;
	private List<String> genres;
	private int popularity;
	
	public Artist() {}

	public Artist(String name, int followers, List<String> genres, int popularity) {
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
	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	
}
