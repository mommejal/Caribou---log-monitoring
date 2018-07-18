package com.controllers;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agent.ParamAgent;
import com.bdd.LightLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@EnableMongoRepositories(basePackageClasses = com.dao.LogDAO.class)
@Repository
@Controller
//@RestController
@Component
public class WebController extends AbstractController {
	@Autowired ModelAndView mav;
//	private Collection<App> apps;
//	private Collection<Alarm> alarms;
	@Autowired
	Gson gson;
	@Autowired
	String regexAgent;
	@Autowired
	ParamAgent paramAgent;
	private static final String DEFAULT_STRING = "RIP_F-ZERO";
	
	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog) {
		// Fonction qui convertit le json en objet java pour sauvegarder les résulats
		// dans la BDD
		System.out.println("je reçois :");
		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<Queue<Queue<String>>>() {
		}.getType());
		String _id = ""; // _id va servir de @Id pour les Logs pour les classer facilement par ordre d'arrivée
//		if (dao.existsBy_id(-1)) {
//			_id = Integer.parseInt(dao.findLightLogBy_id(-1).get(0).getMsg());
//		} // DE cette maniere on le fait que quand il n'y a rien à cet ID
		System.out.println("L 'ID est :"+_id);
		for (Queue<String> log : logs) {
//			String res = "";
//			for (String tmp : log) {
//				res += tmp;
//			}
			dao.save(new LightLog(log));
//			dao.save(new LightLog(-1,String.valueOf(_id)));
//			_id++;
//			System.out.println("-----");
		}
//		System.out.println("--------------");
	}
	
//	@RequestMapping(value = "/technique/gestionBdd", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView gestionBDD(ModelAndView mav) {
//		// Fait accéder à l'écran de gestion de la BDD
//		mav.setViewName("/technique/gestionBdd");
//		return mav;
//	}
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
//	@ResponseBody
	public ModelAndView hello(ModelAndView mav) {
		// Fait accéder à l'écran de gestion de la BDD
		mav.setViewName("hellothymeleaf");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agents/modifParam", method = RequestMethod.GET)
	ModelAndView modifParam(ModelAndView mav,
			@RequestParam(value = "regexDebut", required = false, defaultValue = DEFAULT_STRING) String regexDebut,
			@RequestParam(value = "regexFin", required = false, defaultValue = DEFAULT_STRING) String regexFin,
			@RequestParam(value = "tpsVieMinStock", required = false, defaultValue = DEFAULT_STRING) String tpsVieMinStock,
			@RequestParam(value = "nbLignesLog", required = false, defaultValue = DEFAULT_STRING) String nbLignesLog) {
		
		int oldHashCode = paramAgent.hashCode();
		
		if (!regexDebut.equals(DEFAULT_STRING))
			paramAgent.setRegexDebutLog(regexDebut);
		if (!regexFin.equals(DEFAULT_STRING))
			paramAgent.setRegexFinLog(regexFin);
		
		
		try {
			paramAgent.setTpsVieMinStock(Integer.parseInt(tpsVieMinStock));
			paramAgent.setNbLignesDeSuite(Integer.parseInt(nbLignesLog));
			mav.addObject("error_message","");
			
		} catch (Exception e){
			if (!tpsVieMinStock.equals(DEFAULT_STRING) || !nbLignesLog.equals(DEFAULT_STRING))
				mav.addObject("error_message","Les valeurs entrÃ©es ne sont pas toutes correctes");
			else
				mav.addObject("error message","");
		}
		
		mav.addObject("changement",oldHashCode != paramAgent.hashCode());
		mav.addObject("regexDebut", paramAgent.getRegexDebutLog());
		mav.addObject("regexFin", paramAgent.getRegexFinLog());
		mav.addObject("tpsVieMinStock", paramAgent.getTpsVieMinStock());
		mav.addObject("nbLignesLog", paramAgent.getNbLignesDeSuite());
		mav.setViewName("agents/modifParam");
		return mav;
	}
	
	@RequestMapping(value = "/regexOutput", method = RequestMethod.GET)
	String regexOutcome() {
		return regexAgent;
	}

	@RequestMapping(value = "/getParamAgent", method = RequestMethod.GET)
	String paramOutcome() {
		// TODO
		return "";
	}
}
