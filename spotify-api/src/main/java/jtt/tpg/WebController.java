package jtt.tpg;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jtt.tpg.dao.ArtistDAO;
import jtt.tpg.dto.Artist;

@Controller
public class WebController {
	
	@Autowired
	ArtistDAO artistDAO;
	
	
	
	@PostMapping("/redirect")
	public String artistInfo(
	    @RequestParam(name="action") String action,
	    @RequestParam(name="artist-name") String artistName, 
	    Model model
	) throws IOException {
	    SpotifyArtistInfo spotifyInfo = new SpotifyArtistInfo();
	    Artist artist = spotifyInfo.getArtistStats(artistName);
	    String genres = spotifyInfo.getArtistGenres(artistName);
	    String imageURL = spotifyInfo.getArtistImage(artistName).get(0).getUrl();

	    if (artist == null) {
	        model.addAttribute("name", "Failed to fetch artist data. Please try again.");
	        return "index";
	    }

	   
	    model.addAttribute("name", artist.getName());
	    model.addAttribute("followers", artist.getFollowers());
	    model.addAttribute("genres", genres);
	    model.addAttribute("popularity", artist.getPopularity() + "%");
	    
	    model.addAttribute("imageURL", imageURL);

	    return "index";
	}
}
