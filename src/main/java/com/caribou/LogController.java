package com.caribou;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logs.GeneLog;

@RestController
@Controller
public class LogController {

	@Autowired
	ModelAndView mav;
	@Autowired
	LogsRepository logsRepository;

	public void LogsResource(LogsRepository logsRepository) {
		this.logsRepository = logsRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView

			index() {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/listeLogs", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView listeLogs(ArrayList<GeneLog> logs, ModelAndView mav,
			@RequestParam(value = "filter", required = true/* , defaultValue="nofilter" */) String filter,
			@RequestParam(value = "datebeginning", required = false) String datebeginning,
			@RequestParam(value = "dateend", required = false) String dateend) {
		// On veut afficher une liste de logs pour l'instant on affiche uniquement les
		// messages
		// Quelques logs de test
		// On constate qu'une méthode get sur chaque log suffit à afficher le bon truc
		mav.addObject("logs", logsRepository.findAll());
		mav.addObject("filter", filter);
		mav.addObject("datebeginning", datebeginning);
		mav.addObject("dateend", dateend);
		mav.setViewName("listeLogs");
		return mav;
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

}
