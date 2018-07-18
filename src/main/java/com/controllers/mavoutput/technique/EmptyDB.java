package com.controllers.mavoutput.technique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controllers.Displayer;
import com.mongodb.Mongo;

@Controller
public class EmptyDB extends Displayer{
	@Autowired
	Mongo mongo;
	@Autowired
	MongoDbFactory mongoDbFactory;
	
	@RequestMapping(value = "/technique/gestionBdd/viderBdd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView emptyDB(ModelAndView mav) {
		// En un clic sur le bouton vide toute la BDD
		mongo.dropDatabase(mongoDbFactory.getDb().getName());
		System.out.println("SUPPPRESSION DE LA BDD");
		mav.setViewName("technique/gestionBdd");
		return mav;
	}
}
