package com.appweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@EnableMongoRepositories(basePackageClasses = com.dao.LogDAO.class)
@Repository
@Controller
// @RestController
@Component
public class WebController extends AbstractController {
	
	@Autowired
	ModelAndView mav;
	// private Collection<App> apps;
	// private Collection<Alarm> alarms;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView hello(ModelAndView mav) {
		// Fait acc�der � l'�cran de gestion de la BDD
		mav.setViewName("hellothymeleaf");
		return mav;
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	String error(ModelAndView mav) {
		return "une erreur est survenue";
	}
	
	
}
