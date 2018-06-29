package com.bdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.caribou.LogsRepository;
import com.mongodb.Mongo;

@Component
@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
public class Recherche {
	// Une classe destinée à traiter les requetes de filtrage des Logs
	// Va probablement utiliser JQUERY
	public LogsRepository logsRepository;
	@Autowired Mongo mongo;
	@Autowired MongoDbFactory mongoDbFactory;
	public String Filtre;
	
	public ModelAndView noFilter(String filter, ModelAndView mav) {
		mav.addObject("logs", logsRepository.findAll());
		mav.addObject("filter", filter);
		return mav;
	}
	
	public int conversionDate(String date) {
		// Pour des dates de type heure:minute:sec.millisec
		return 0;
	}
	
	public ModelAndView filterbySeverityLvl(String severitylvl, ModelAndView mav) {
//		mongo.dropDatabase(mongoDbFactory.getDb().getName());
		return mav;
	}
	
	public ModelAndView filterbyDate(String datebeginning,String dateend, ModelAndView mav) {
		return mav;
	}
	
	public ModelAndView filterbyId(int idbeginning, int idend, ModelAndView mav) {
		return mav;
	}
}
