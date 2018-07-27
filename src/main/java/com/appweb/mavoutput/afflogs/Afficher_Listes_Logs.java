package com.appweb.mavoutput.afflogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.dao.LogDAO;
import com.filter.LogFinder;
import com.log.LightLog;
import com.log.loganalyzer.LogAnalyzer;
import com.log.loganalyzer.LogAnalyzerBuilder;

@Controller
public class Afficher_Listes_Logs extends Displayer {
	// Classe responsable de l'affichage de tableaux de logs et de la recherche à
	// l'intérieur de celui ci

	@Autowired
	LogAnalyzerBuilder builder;

	@Autowired
	LogFinder logfinder;

	@Autowired
	LogDAO dao;

	@RequestMapping(value = "/AffLogs/afficher_listes_logs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView afficherListeLightLog(ModelAndView mav,
			@RequestParam(required = false, defaultValue = "") String selectedregexp) {
		// Fonction qui affiche tous les logs de la base de donnï¿½es, ï¿½ terme elle
		Collection<LogAnalyzer> logs = builder.buildLogAnalyzer(find(selectedregexp));// Vï¿½rifier que la complexitï¿½
																						// soit pas trop grande

		logfinder.setLogCache(logs);
		mav = formater(mav, selectedregexp, "Content", logs);
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
		try {
			mav = formater(mav, selectedregexp, selectedattribut, logfinder.getLogCache());
		} catch (Exception e) {
			System.out.println("Erreur du formatage de log, l'attribut sélectionné est il correct ?");
		}

		mav.setViewName("/AffLogs/afficher_listes_logs");
		return mav;
	}

	public ModelAndView formater(ModelAndView Mav, String selectedregexp, String selectedattribut,
			Collection<LogAnalyzer> logs) {

		Set<String> availabledata = new HashSet<String>();
		for (LogAnalyzer log : logs) {
			for (String data : log.getAvailableDatas())
				availabledata.add(data);
		}

		// logs = (ArrayList<LogAnalyzer>) logs;
		// Permet de rï¿½cuperer toutes les datas d'un type
		// Collection <String> availabledata = builder.getAvailableDataByType(type);

		HashSet<ArrayList<String>> contenttodisplay = new HashSet<ArrayList<String>>();

		for (LogAnalyzer log : logs) {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(log.getSource());
			for (String data : availabledata) {
				if (log.getAvailableDatas().contains(data))
					tmp.add((String) log.getData(data));
				else
					tmp.add("");
			}
			contenttodisplay.add(tmp);
		}
		List<String> listdonnes = new ArrayList<String>();
		listdonnes.add("Source");
		listdonnes.addAll(availabledata);
		
		mav.addObject("availabledata", listdonnes);
		mav.addObject("logs", contenttodisplay);
		return mav;
	}
}
