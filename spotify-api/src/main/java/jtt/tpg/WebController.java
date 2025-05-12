package jtt.tpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jtt.tpg.dao.AutorDAO;

@Controller
public class WebController {
	
	@Autowired
	AutorDAO autorDAO;
	
    @GetMapping("/")
    public String home(Model model) {
    	String autorName = autorDAO.getByID(1).getName();
        model.addAttribute("message", autorName);
        return "index"; // Maps to index.html in templates folder
    }
}
