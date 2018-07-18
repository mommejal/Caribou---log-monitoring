package com.appweb.mavoutput.technique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;

@Controller
@Component
public class GestionBDD extends Displayer {
	@Autowired ModelAndView mav;
	@RequestMapping(value = "/technique/gestionBdd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView gestionBDD(ModelAndView mav) {
		// Fait acc�der � l'�cran de gestion de la BDD
		mav.setViewName("/technique/gestionBdd");
		return mav;
	}
}
