package com.appweb.mavoutput.afflogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.bdd.Recherche;
import com.log.LightLog;
import com.log.loganalyzer.LogAnalyzer;
import com.log.loganalyzer.LogAnalyzerBuilder;

@Controller
public class AfficherListeLightLog extends Displayer {
	@Autowired
	LogAnalyzerBuilder builder;
	@Autowired
	Recherche recherche;
	@RequestMapping(value = "/AffLogs/afficher_listes_logs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView afficherListeLightLog(ModelAndView mav) {
		// Fonction qui affiche tous les logs de la base de donn�es, � terme elle devra
		// afficher seuelement selon les filtres
//		availabledata.add("source");
		String type = "FirstLogAnalyzer";
//		ArrayList tests= {""
		dao.save(new LightLog("id1", Arrays.asList("Conenu du log  1, suite du log 1 ,333333".split(",")), "source 1"));
		dao.save(new LightLog("id2", Arrays.asList("Conenu du log  1, WARN ,dddddd".split(",")), "source 1"));
		dao.save(new LightLog("id3", Arrays.asList("Conenu du log  1,gegeg ag ,3gageagea33".split(",")), "source 1"));

		Collection <LogAnalyzer> logs = builder.buildLogAnalyzer(dao.findAll(), type);
//		Collection <String> availabledata = builder.getAvailableDataByType(type);
		ArrayList<String>availabledata = new ArrayList<String>();
		availabledata.add("SeverityLvl");
		availabledata.add("Source");

		availabledata.add("Content");

		mav.addObject("availabledata", availabledata);
		mav.addObject("logs", logs);
		mav.setViewName("/AffLogs/afficher_listes_logs");
		System.out.println("Je sors de la fonction afficher liste light log");
		
		return mav;
	}
}
