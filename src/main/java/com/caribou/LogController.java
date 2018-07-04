package com.caribou;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.bdd.Recherche;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.Mongo;

@RestController
@Controller
@Component
@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
@Repository
public class LogController{
	@Autowired
	Gson gson;
	@Autowired
	String regexAgent;
	@Autowired
	LogsRepository logsRepository;
	@Autowired
	Mongo mongo;
	@Autowired
	MongoDbFactory mongoDbFactory;
	@Autowired
	ModelAndView mav;
	@Autowired
	Recherche recherche;
//	@Autowired
//	QuerydslPredicateExecutor<Logs> test;
	
	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog) {
		// Fonction qui convertit le json en objet java pour sauvegarder les résulats
		// dans la BDD
		System.out.println("je reÃ§ois :");
		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<Queue<Queue<String>>>() {
		}.getType());
		for (Queue<String> log : logs) {
			String res = "";
			for (String tmp : log) {
				res+=tmp;
			}
			logsRepository.save(new Logs(res));
		}
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

	public void LogsResource(LogsRepository logsRepository) {
		this.logsRepository = logsRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		// Retourne à l'acceuil
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/listeLogs", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView listeLogs(ModelAndView mav,
			@RequestParam(value = "filter", required = true/* , defaultValue="nofilter" */) String filter,
			@RequestParam(value = "datebeginning", required = false) String datebeginning,
			@RequestParam(value = "dateend", required = false) String dateend) {
		// Fonction qui affiche tous les logs de la base de données, à terme elle devra
		// afficher seuelement selon les filtres
		// On veut afficher une liste de logs pour l'instant on affiche uniquement les
		// ID et les messages
		mav=recherche.filter(filter,mav);
		mav.addObject("datebeginning", datebeginning);
		mav.addObject("dateend", dateend);
		mav.setViewName("listeLogs");
		return mav;
	}
	
	@GetMapping(value= "/listeLogs/afficherunLog")
	ModelAndView afficherUnLog(ModelAndView mav, @RequestParam(value="idlog") int idlog) {
		mav.addObject("logs", logsRepository.findOneByIdlog(idlog));
		mav.setViewName("listeLogs");
        return mav;
    }

	@RequestMapping(value = "/gestionBdd", method = RequestMethod.GET)
	ModelAndView gestionBdd(ModelAndView mav) {
		// Fait accéder à l'écran de gestion de la BDD
		mav.setViewName("gestionBdd");
		return mav;
	}

	@RequestMapping(value = "/gestionBdd/viderBdd", method = RequestMethod.GET)
	ModelAndView viderBdd(ModelAndView mav) {
		// EN un clic sur le bouton vide toute la BDD
		mongo.dropDatabase(mongoDbFactory.getDb().getName());
		mav.setViewName("gestionBdd");
		return mav;
	}
	
	@RequestMapping(value= "/parametres_recherche", method = RequestMethod.GET)
	ModelAndView parametresRecherche(ModelAndView mav, @RequestParam(value="motifachercher", required=false) String motifachercher) {
		return mav;
	}
	
}
