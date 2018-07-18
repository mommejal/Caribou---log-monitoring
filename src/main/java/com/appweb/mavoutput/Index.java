package com.appweb.mavoutput;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;

@Controller
@Component
public class Index extends Displayer{
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView  index(ModelAndView mav) {
		// Retourne � l'acceuil
		mav.setViewName("index");
		return mav;
	}
}
