package com.caribou;

import java.util.ArrayList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.dao.LogDao;
import com.logs.GeneLog;
import com.logs.ListeDeLogs;
import com.logs.logXMLTest;

@RestController
@Controller
public class LogController {

	@Autowired
	Gson gson;
	
	@Autowired
	ListeDeLogs logs;
	// logs.add(new logXMLTest(new String("<![LOG[Exiting
	// Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\"
	// date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\"
	// type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">")));
	// logs.add(new logXMLTest(new String("<![LOG[Exiting
	// Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::GetVolumeProtectionInfos...]LOG]!><time=\"13:03:11.840-120\"
	// date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\"
	// type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:258\">")));
	// logs.add(new logXMLTest(new String("<![LOG[Exiting
	// Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\"
	// date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\"
	// type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));

	@Autowired
	String regexAgent;

	@Autowired
	ModelAndView mav;
	// ModelAndView mav = new ModelAndView("listeLogs");

	public LogDao logdao;

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		// ModelAndView mav = new ModelAndView("index");
		// mav.addObject("hoa_version", "HOA v0.1 parce que je viens de LogController");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	ModelAndView hello() {
		// ModelAndView mav = new ModelAndView("hellothymeleaf");
		// mav.addAttribute("serverTime", "Il est 10h");
		mav.addObject("hello", "hello de la part du Log Controller");
		logXMLTest log = new logXMLTest(new String(
				"<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">"));
		mav.addObject("logMSG", log.getMSG());
		mav.setViewName("hellothymeleaf");
		return mav;
	}

	@GetMapping("/greet")
	ModelAndView greet(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			ModelAndView model) {
		model.addObject("name", name);
		model.setViewName("greet");
		return model;
	}

	@GetMapping("/greeting")
	ModelAndView greeting(@RequestParam(name = "name", required = true, defaultValue = "World") String name,
			ModelAndView model) {
		model.addObject("name", name);
		model.setViewName("greeting");
		return model;
	}

	//
	@RequestMapping(value = "/listeLogs", method = RequestMethod.POST)
	@ResponseBody
	ModelAndView listeLogs(ArrayList<GeneLog> logs, ModelAndView mav,
			@RequestParam(value = "filter", required = false, defaultValue = "nofilter") String filter) {
		// On veut afficher une liste de logs pour l'instant on affiche uniquement les
		// messages
		// ModelAndView mav = new ModelAndView("listeLogs");
		// ArrayList <logXMLTest> logs = new ArrayList<logXMLTest>();
		// Quelques logs de test
		logs.add(new logXMLTest(new String(
				"<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::CheckWriteFilterProtection...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:501\">")));
		logs.add(new logXMLTest(new String(
				"<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::GetVolumeProtectionInfos...]LOG]!><time=\"13:03:11.840-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:258\">")));
		logs.add(new logXMLTest(new String(
				"<![LOG[Exiting Microsoft::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\" date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\" type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
		// On constate qu'une m�thode get sur chaque log suffit � afficher le bon truc
		System.out.println("On passe par le liste Logs");
		mav.addObject("logs", logs);
		mav.addObject("filter", filter);
		mav.setViewName("listeLogs");
		return mav;
		// return logdao.findAll();
	}

	// @RequestMapping(path="/listeLogs",
	// params={"addRow"},method=RequestMethod.GET)
	// @ResponseBody
	// ModelAndView
	// /*LinkedList <GeneLog> logs, GeneLog logaajouter,*/
	// addRow(/*@RequestParam("addRow") boolean addRow,*/ ArrayList <GeneLog> logs,
	// ModelAndView mav) {
	//// List <GeneLog> logs = new LinkedList<GeneLog>();
	//// if (addRow) {
	// logs.add(new logXMLTest(new String("<![LOG[JE SUIS LE LOG RAJOUTE PAR
	// addRow::EmbeddedDeviceManager::Deployment::UwfManager::SuspendProtection...]LOG]!><time=\"13:03:11.855-120\"
	// date=\"06-02-2018\" component=\"MaintenanceCoordinator\" context=\"\"
	// type=\"0\" thread=\"2188\" file=\"uwfmanager.cpp:124\">")));
	//// }
	// System.out.println("On passe par le addROw");
	// mav.addObject("logs",logs);
	// mav.setViewName("listeLogs");
	// return mav;
	// }

	// @RequestMapping(value="/listeLogs", params={"removeRow"})
	// ModelAndView
	// public String removeRow(LinkedList <GeneLog> logs, int id) {
	// //On enleve le log de tel id
	// final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	// seedStarter.getRows().remove(rowId.intValue());
	// return "listeLogs";
	// }

	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog) {
		System.out.println("je reçois :");

		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<Queue<Queue<String>>>() {
		}.getType());
		
		for(Queue<String> log : logs ) {
			for(String line : log)
				System.out.println(line);
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

	@GetMapping(value = "/SeverityLvl/{lvl}")
	public String afficherUnLogSeverityLvl(@PathVariable int lvl) {
		return (logdao.findBySeverityLvl(lvl)).toString();
	}

	@GetMapping(value = "/AfficherUnLogEtId")
	public String afficherUnLogEtId() {
		// Devra prendre en argument un log et l'afficher
		logXMLTest log = new logXMLTest(new String("Contenu du log d'Id="));
		return (log.getData() + log.getID());
	}

}
