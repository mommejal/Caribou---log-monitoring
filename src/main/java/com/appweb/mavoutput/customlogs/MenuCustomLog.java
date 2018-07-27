package com.appweb.mavoutput.customlogs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;

@Controller
public class MenuCustomLog extends Displayer{
	// Menu pour la gestion de logs custom ou ajout de parametres de recherche aux logs
	@RequestMapping(value ="/customlogs/menuCustomLog", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView  menuCustomLog(ModelAndView mav) {
		mav.setViewName("/customlogs/menuCustomLog");
		return mav;
	}
}