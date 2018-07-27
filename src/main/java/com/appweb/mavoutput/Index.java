package com.appweb.mavoutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.mongodb.Mongo;

@Controller
public class Index extends Displayer{
	// Le menu principal de l'appweb
	@Autowired Mongo mongo;
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView  index(ModelAndView mav) {
		// Retourne � l'acceuil
		mav.setViewName("index");
		return mav;
	}
}
