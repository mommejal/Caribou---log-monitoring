package com.appweb.mavoutput.afflogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.bdd.Recherche;
import com.filter.LogFinder;
import com.log.LightLog;
import com.log.loganalyzer.LogAnalyzer;
import com.log.loganalyzer.LogAnalyzerBuilder;

@Controller
public class Afficher_Listes_Logs extends Displayer {
	@Autowired
	LogAnalyzerBuilder builder;
	@Autowired
	Recherche recherche;
	@Autowired
	LogFinder logfinder;

	@RequestMapping(value = "/AffLogs/afficher_listes_logs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView afficherListeLightLog(ModelAndView mav,
			@RequestParam(required = false, defaultValue = "") String selectedregexp){
		// Fonction qui affiche tous les logs de la base de donnï¿½es, ï¿½ terme elle
		String type = "FirstLogAnalyzer";
		Collection<LogAnalyzer> logs = builder.buildLogAnalyzer(find(selectedregexp), type);// Vérifier que la complexité soit pas trop grande
		logfinder.setLogCache(logs);
		mav = formater(mav, type, selectedregexp, "Content", logs);
		mav.setViewName("/AffLogs/afficher_listes_logs");
		return mav;
	}

	public HashSet<LightLog> find(String selectedregexp) {
		// Cette fonction dit quels Logs afficher sur la page html
		HashSet<LightLog> res = new HashSet<LightLog>();
		if (selectedregexp.equals("")) {
			res = (new HashSet<LightLog>(dao.findAll()));
		} else {
			res = (new HashSet<LightLog>(dao.findLightLogByRegexpContent(selectedregexp)));
		}
		
		return res;
	}

	@RequestMapping(value = "/AffLogs/affiner_la_recherche", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView affinerLaRecherche(ModelAndView mav,
			@RequestParam(required = false, defaultValue = "") String selectedregexp,
			@RequestParam(required = false, defaultValue = "Content") String selectedattribut) {
		logfinder.filterBy(selectedattribut, selectedregexp);
		mav = formater(mav, "FirstLogAnalyzer", selectedregexp, selectedattribut,logfinder.getLogCache());
		mav.setViewName("/AffLogs/afficher_listes_logs");
		return mav;
	}
	
	public ModelAndView formater(ModelAndView Mav,String type, String selectedregexp, String selectedattribut,Collection<LogAnalyzer> logs) {
		
		ArrayList<String> availabledata = new ArrayList<String>();
		LogAnalyzerBuilder tmptogetavailabledata = new LogAnalyzerBuilder();
		availabledata = (ArrayList<String>) tmptogetavailabledata.getAvailableDataByType(type);
		
		
		logs = (ArrayList<LogAnalyzer>) logs;
		// Permet de récuperer toutes les datas d'un type 			
		// Collection <String> availabledata = builder.getAvailableDataByType(type);

		ArrayList<ArrayList<String>> contenttodisplay = new ArrayList<ArrayList<String>>();

		for (LogAnalyzer log : logs) {
			ArrayList<String> tmp = new ArrayList<String>();
			for (String data : availabledata) {
				tmp.add((String) log.getData(data));
			}
			contenttodisplay.add(tmp);
		}
		mav.addObject("availabledata", availabledata);
		mav.addObject("logs", contenttodisplay);
		return mav;
	}
}
