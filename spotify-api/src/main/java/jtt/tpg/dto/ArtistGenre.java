package jtt.tpg.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//This class is needed to create ArtistGenre object that saves info about what genres artist have

@Entity
@Table(name = "artist_genres")
public class ArtistGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private int artist_id;
	@NotNull
	private int genre_id;
	
	public ArtistGenre() {}
	
	public ArtistGenre(int artistID, int genreID) {
		this.artist_id = artistID;
		this.genre_id = genreID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArtistID() {
		return artist_id;
	}

	public void setArtistID(int artist_id) {
		this.artist_id = artist_id;
	}

	public int getGenreID() {
		return genre_id;
	}

	public void setGenreID(int genre_id) {
		this.genre_id = genre_id;
	}
}