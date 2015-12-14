package rbprojects.celscompare.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rbprojects.celscompare.dto.CelularInfoDTO;
import rbprojects.celscompare.dto.ComparativoCelularDTO;
import rbprojects.celscompare.service.CelsCompareService;

@RestController
public class CelsCompareRestController {

	@Autowired
	private CelsCompareService serviceCtrl;

	@RequestMapping(value = "/findAll", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CelularInfoDTO[]  getInfos() {
		return serviceCtrl.findAllInfos();
	}
	
	@RequestMapping(value = "/compare/{idCelular1}/{idCelular2}", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ComparativoCelularDTO  compare(@PathVariable long idCelular1,@PathVariable long idCelular2) {
		return serviceCtrl.compareCels(idCelular1, idCelular2);
	}
	
	@RequestMapping(value = "/findAllOrderByFavorito", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CelularInfoDTO[]  findAllOrderByFavorito() {
		return serviceCtrl.findAllInfosOrderByFavorito();
	}
	
	@RequestMapping(value = "/addFavorito/{idCelular1}", method = RequestMethod.POST)
	public @ResponseBody String  addFavorito(@PathVariable long idCelular1) {
		serviceCtrl.addFavorito(idCelular1);
		return "{}";
	}
	

}
