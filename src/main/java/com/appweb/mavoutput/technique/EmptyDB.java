package com.appweb.mavoutput.technique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.bdd.DBMonitor;

@Controller
public class EmptyDB extends Displayer{

	@Autowired DBMonitor dbmonitor;
	@RequestMapping(value = "/technique/gestionBdd/viderBdd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView emptyDB(ModelAndView mav) {
		// En un clic sur le bouton vide toute la BDD
		dbmonitor.emptyDB();
		mav.setViewName("technique/gestionBdd");
		return mav;
	}
	
}
