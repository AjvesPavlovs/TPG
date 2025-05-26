package jtt.tpg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	
    @PostMapping("/redirect")
    public String artistInfo(@RequestParam(name="action") String action,
    		@RequestParam(name="artist-name") String username, int followers, String genres, int popularity, Model model) {

        model.addAttribute("artist-name", username);
        model.addAttribute("followers", followers);
        model.addAttribute("genres", genres);
        model.addAttribute("popularity", popularity);
        return "index"; // Maps to index.html in templates folder
    }
}
