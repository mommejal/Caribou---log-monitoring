package com.appweb.mavoutput.afflogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.bdd.ParametresRecherche;
import com.bdd.Recherche;

@Controller
public class AfficherListeLightLog extends Displayer {
	@Autowired
	ParametresRecherche param;
	@Autowired
	Recherche recherche;
	@RequestMapping(value = "/AffLogs/afficher_listes_logs", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView afficherListeLightLog(ModelAndView mav) {
		// TROUVEZ UN MOYEN DE COMPARER LES DATES
		// Fonction qui affiche tous les logs de la base de donn�es, � terme elle devra
		// afficher seuelement selon les filtres
		// On veut afficher une liste de logs pour l'instant on affiche uniquement les
		// ID et les messages
//		mav.clear();
		mav = recherche.filter(mav,param);
		mav.addObject("datebeginning", param.getDatebeginning());
		mav.addObject("dateend", param.getDateend());
		mav.setViewName("/AffLogs/afficher_listes_logs");
		return mav;
	}
}
