package jtt.tpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jtt.tpg.dao.ArtistDAO;


@Controller
public class WebController {
	

	
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("message", "asd");
        return "index"; // Maps to index.html in templates folder
    }
}
