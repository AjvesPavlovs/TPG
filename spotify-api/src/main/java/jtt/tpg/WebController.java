package jtt.tpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jtt.tpg.dao.ArtistDAO;
import jtt.tpg.dao.ArtistGenreDAO;
import jtt.tpg.dao.GenreDAO;
import jtt.tpg.dto.Artist;
import jtt.tpg.dto.ArtistGenre;
import jtt.tpg.dto.Genre;
import jtt.tpg.dto.Track;

@Controller
public class WebController {
	
	@Autowired
	ArtistDAO artistDAO;
	@Autowired
	GenreDAO genreDAO;
	@Autowired
	ArtistGenreDAO agDAO;
	

	@PostMapping("/redirect")
	public String artistInfo(
		@RequestParam(name="action", required=false, defaultValue="search") String action,
		@RequestParam(name="artist-name") String artistName, 
		Model model
	) throws IOException {
		
	    SpotifyArtistInfo spotifyInfo = new SpotifyArtistInfo();
	    Artist artist = spotifyInfo.getArtistStats(artistName);
	    String genres = spotifyInfo.getArtistGenres(artistName);
	    String imageURL = spotifyInfo.getArtistImages(artistName).get(0).getUrl();
	    
	    if(artistDAO.insert(artist) != null) {
	    if(!genres.equals("No genres available")) {
	    for (String genre : genres.split(", ")) {
	    	genreDAO.insert(new Genre(genre));
	    	agDAO.insert(new ArtistGenre(artist.getId(), genreDAO.getID(new Genre(genre))));
	    	

	    	
		}}}

	    if (artist == null) {
	        model.addAttribute("name", "Failed to fetch artist data. Please try again.");
	        return "index";
	    }

	    model.addAttribute("name", artist.getName());
	    model.addAttribute("followers", artist.getFollowers());
	    model.addAttribute("genres", genres);
	    model.addAttribute("popularity", artist.getPopularity() + "%");
	    model.addAttribute("imageURL", imageURL);
	    
	    
		 List<Artist> artists = artistDAO.getAllData();
		    
		    List<String> artistNames = new ArrayList<>();
		    for (Artist i : artists) {
				artistNames.add(i.getName());
			}
		    model.addAttribute("dropdownItems", artistNames);
		    
		    
		   List<Track> tracks = spotifyInfo.getArtistsTopTracks(artistName);
		    model.addAttribute("tracks", tracks);

	    return "index";
	}
}
