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
    public String artistInfo(@RequestParam(name="action") String action,
    		@RequestParam(name="artist-name") String username, Model model) throws IOException {
    	SpotifyArtistInfo spotifyInfo = new SpotifyArtistInfo();
    	
    	Artist artist = spotifyInfo.getArtistStats(username);
    	System.out.println(artist.getName());
    	System.out.println(artist.getFollowers());
    	int followers = artist.getFollowers();
    	int popularity = artist.getPopularity();
    	
        model.addAttribute("artist-name2", username);
        model.addAttribute("followers", followers);
      //  model.addAttribute("genres", genres);
        model.addAttribute("popularity", popularity);
        return "index"; // Maps to index.html in templates folder
    }
}
