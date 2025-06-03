package jtt.tpg.dto;

import java.util.List;

public class Track {
	private int id;
	private String name;
	private List<String> artists;
	private int duration;
	private int popularity;
	private String uri;
	private String albumName;
	private String albumImgURL;
	private String albumReleaseDate;
	
	public Track(String name, List<String> artists, int duration, int popularity, String uri, String albumName, String albumImgURL, String albumReleaseDate) {
		this.name = name;
		this.artists = artists;
		this.duration = duration;
		this.popularity = popularity;
		this.uri = uri;
		this.albumName = albumName;
		this.albumImgURL = albumImgURL;
		this.albumReleaseDate = albumReleaseDate;;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArtists() {
		return artists;
	}

	public void setArtists(List<String> artists) {
		this.artists = artists;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumImgURL() {
		return albumImgURL;
	}

	public void setAlbumImgURL(String albumImgURL) {
		this.albumImgURL = albumImgURL;
	}

	public String getAlbumReleaseDate() {
		return albumReleaseDate;
	}

	public void setAlbumReleaseDate(String albumReleaseDate) {
		this.albumReleaseDate = albumReleaseDate;
	}
	
	
	
}
