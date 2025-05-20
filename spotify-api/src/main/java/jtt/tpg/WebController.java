package jtt.tpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jtt.tpg.dao.ArtistDAO;


@Controller
public class WebController {
	
	@Autowired
	ArtistDAO artistDAO;
	
    @GetMapping("/")
    public String home(Model model) {
    	String autorName = artistDAO.getByID(1).getName();
        model.addAttribute("message", autorName);
        return "index"; // Maps to index.html in templates folder
    }
}
