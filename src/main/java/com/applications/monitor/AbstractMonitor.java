package com.applications.monitor;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractMonitor {
	
	protected ModelAndView problem(ModelAndView mav, String message, String retour) {
		mav.addObject("message", message);
		mav.addObject("sortie", retour);
		mav.setViewName("errorEncountered");
		return mav;
	}
}
