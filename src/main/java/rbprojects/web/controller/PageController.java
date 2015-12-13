package rbprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rbprojects.service.CelularCompareService;

@Controller
public class PageController {

	@Autowired
	private CelularCompareService serviceCtrl;
	
	@RequestMapping(value = "/addFavorito/{idCelular1}", method = RequestMethod.GET)
	public String  addFavorito(@PathVariable long idCelular1) {
		serviceCtrl.addFavorito(idCelular1);
		return  "favoritos";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
	    return "celularCompare";
	}
	
}
