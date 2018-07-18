package com.bdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.LogsRepository;
import com.mongodb.Mongo;

@Component
@EnableMongoRepositories(basePackageClasses = com.appweb.LogsRepository.class)
public class Recherche {
	// Une classe destin�e � traiter les requetes de filtrage des Logs
	// Va probablement utiliser JQUERY
	public LogsRepository logsRepository;
	@Autowired
	Mongo mongo;
	@Autowired
	MongoDbFactory mongoDbFactory;
	public String Filtre;

	public Recherche(LogsRepository input) {
		logsRepository = input;
	}

	public ModelAndView noFilter(ModelAndView mav) {

		mav.addObject("logs", logsRepository.findAll());
		return mav;
	}

	public int conversionDate(String date) {
		// Pour des dates de type heure:minute:sec.millisec
		return 0;
	}

	public ModelAndView filterBySeverityLvl(String severitylvl, ModelAndView mav) {
		mav.addObject("logs", logsRepository.findLogsBySeveritylvl(severitylvl));
		return mav;
	}

	public ModelAndView filterByDate(String datebeginning, String dateend, ModelAndView mav) {
		return mav;
	}

	public ModelAndView filterByIdlog(int idbeginning, int idend, ModelAndView mav) {
		return mav;
	}

	public ModelAndView filterByRegex(String regexp, ModelAndView mav) {
		mav.addObject("logs", logsRepository.findLogsByRegexpMsg(regexp));
		return mav;
	}

	public ModelAndView filter(ModelAndView mav, ParametresRecherche param) {
		mav.clear();
		mav.addObject("selectedfilters", param.getSelectedfilters());
		System.out.println(param.getSelectedfilters());
		for (String filt : param.getSelectedfilters()) {
			System.out.println("je suis une it�ration de filt");
			System.out.println(filt);
			if (filt.equals("regexpFilter")) {
				System.out.println("Je fais filterbyregexp");
				System.out.println("Je cherche :"+param.getSelectedregexp());
				mav = filterByRegex(param.getSelectedregexp(), mav);				
			} 
			else if (filt.equals("severityLvlFilter")) {
				for (String severitylvl : param.getSelectedseveritylvls()) {
					System.out.println("Je fais filterbySeveritylvl");
					mav=filterBySeverityLvl(severitylvl,mav);
				}
			}		
			else {
				System.out.println("Je fais no filter");

				mav= noFilter(mav);
			}
		}
		return mav;
	}

}
