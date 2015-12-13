package rbprojects.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	
	
	@RequestMapping(value = "/listaFavoritos", method = RequestMethod.GET)
	public String  addFavorito() {
		return  "favoritos";
	}
	
	@RequestMapping(value = "/celularCompare", method = RequestMethod.GET)
	public String welcome() {
	    return "celularCompare";
	}
	
}
