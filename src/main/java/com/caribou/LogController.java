package com.caribou;

import java.util.Arrays;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agent.ParamAgent;
import com.bdd.ParametresRecherche;
import com.bdd.Recherche;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.Mongo;

@RestController
//@Controller
@Component
@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
@Repository
public class LogController {

	private static final String DEFAULT_STRING = "RIP_F-ZERO";

	@Autowired
	ParametresRecherche param;
	@Autowired
	Gson gson;
	@Autowired
	String regexAgent;

	@Autowired
	ParamAgent paramAgent;

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

	// Certains Logs apparaissent en double, il faut checker si c'est un probleme
	// dID ou d'enregistrement

	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog) {
		// Fonction qui convertit le json en objet java pour sauvegarder les résulats
		// dans la BDD
		System.out.println("je reÃ§ois :");
		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<Queue<Queue<String>>>() {
		}.getType());
		int _id = 0; // _id va servir de @Id pour les Logs pour les classer facilement par ordre d'arrivée
//		logsRepository.insert(new Logs(-1,String.valueOf(_id))); // DE cette maniere on le fait que quand il n'y a rien à cet ID
//		_id = Integer.parseInt(logsRepository.findLogsBy_id(-1).get(0).getMsg());
//		if (_id==null)
//			_id=0;
		System.out.println("L 'ID est :"+_id);
		for (Queue<String> log : logs) {
			String res = "";
			for (String tmp : log) {
				res += tmp;
			}
			logsRepository.save(new Logs(_id,res));
//			logsRepository.save(new Logs(-1,String.valueOf(_id)));
			_id++;
//			System.out.println("-----");
		}
//		System.out.println("--------------");
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
	public void setLogsResource(LogsRepository logsRepository) {
		this.logsRepository = logsRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		// Retourne à l'acceuil
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/AffLogs/afficher_listes_logs", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView listeLogs(ModelAndView mav
) {
		// TROUVEZ UN MOYEN DE COMPARER LES DATES
		// Fonction qui affiche tous les logs de la base de données, à terme elle devra
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

//	@GetMapping(value = "/AffLogs/afficher_listes_logs/afficherunLog")
//	ModelAndView afficherUnLog(ModelAndView mav, @RequestParam(value = "idlog") int idlog) {
//		// Je voudrais afficher uniquement les logs qui ont cet ID
//		mav.clear();
//		mav.addObject("logs", logsRepository.findLogsByIdlog(idlog));
//		mav.setViewName("/AffLogs/afficher_listes_logs");
//		return mav;
//	}

	@RequestMapping(value = "/technique/gestionBdd", method = RequestMethod.GET)
	ModelAndView gestionBdd(ModelAndView mav) {
		// Fait accéder à l'écran de gestion de la BDD
		mav.setViewName("technique/gestionBdd");
		return mav;
	}

	@RequestMapping(value = "/technique/gestionBdd/viderBdd", method = RequestMethod.GET)
	ModelAndView viderBdd(ModelAndView mav) {
		// En un clic sur le bouton vide toute la BDD
		mongo.dropDatabase(mongoDbFactory.getDb().getName());
		System.out.println("SUPPPRESSION DE LA BDD");
		mav.setViewName("technique/gestionBdd");
		return mav;
	}

	@RequestMapping(value = "/AffLogs/parametres_recherche", method = RequestMethod.GET)
	ModelAndView parametresRecherche(ModelAndView mav,
			@RequestParam(value = "selectedfilters", required = false,defaultValue="noFilter") String selectedfilters,
			@RequestParam(value = "selectedseveritylvls", required = false,defaultValue="( INFO )|(DEBUG )|( ERROR )") String selectedseveritylvls,
			@RequestParam(value = "selectedregexp", required = false,defaultValue="") String selectedregexp,
			@RequestParam(value = "detectiondate", required = false,defaultValue="[0-9\\[][0-9]:[0-9][0-9]:[0-9][0-9][.,][0-9][0-9][0-9]") String detectiondate,
			@RequestParam(value = "detectionidlog", required = false,defaultValue="ID: [0-9]*") String detectionidlog,
			@RequestParam(value = "agent", required = false,defaultValue="agent") String agent,
			@RequestParam(value = "datebeginning", required = false,defaultValue="defaultbeginning") String datebeginning,
			@RequestParam(value = "dateend", required = false,defaultValue="defaultend") String dateend)
	{
		param.setSelectedfilters(Arrays.asList(selectedfilters.split(",")));
		param.setSelectedseveritylvls(Arrays.asList(selectedseveritylvls.split("\\,")));
		param.setSelectedregexp(selectedregexp);
		param.setAgent(agent);
		param.setDatebeginning(datebeginning);
		param.setDateend(dateend);
		param.setDetectionidlog(detectionidlog);
//		param.setDetectionseveritylvl(detectionseveritylvl);
		mav.setViewName("AffLogs/parametres_recherche");
		return mav;
	}
	

	
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
}