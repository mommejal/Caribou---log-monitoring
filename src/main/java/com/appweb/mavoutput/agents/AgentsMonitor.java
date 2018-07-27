package com.appweb.mavoutput.agents;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agent.paramagent.ParamAgentToManage;

@Controller
public class AgentsMonitor {

	// private static final String DEFAULT_STRING = "RIP_F-ZERO";

	@Autowired
	Map<String, ParamAgentToManage> agents;

	@RequestMapping(value = "/agents/selectAgent", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView selectAgent(ModelAndView mav) {
		mav.addObject("source", agents.values());
		mav.addObject("target", "/agents/modifParam");
		mav.addObject("getValue", "getId()");
		mav.addObject("getText", "getAppName()");
		mav.addObject("TextButton", "selectionner cet agent");
		mav.setViewName("agents/selectAgent");
		return mav;
	}

	private ModelAndView problem(ModelAndView mav, String message, String retour) {
		mav.addObject("message", message);
		mav.addObject("sortie", retour);
		mav.setViewName("agents/errorEncountered");
		return mav;
	}

	@RequestMapping(value = "/agents/modifParam", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView modifParam(ModelAndView mav, @RequestParam(value = "selection", required = true) String idAgent) {

		ParamAgentToManage paramAgent = agents.get(idAgent);
		System.out.println("AAAAAAAAAAAAAAAAAAA");
		System.out.println(paramAgent.getId());
		System.out.println("AAAAAAAAAAAAAAAAAAA");
		if (paramAgent == null)
			return problem(mav, "cet id n'est pas reconnu", "/agents/selectAgent");

		mav.addObject("changement", false);
		mav.addObject("error_message", "");
		mav.addObject("agent", paramAgent);
		mav.setViewName("agents/modifParam");
		return mav;
	}

	@RequestMapping(value = "/agents/modifParamAct", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView modifParamPlus(ModelAndView mav, @RequestParam(value = "idAgent", required = false) String idAgent,
			@RequestParam(value = "appName", required = false) String appName,
			@RequestParam(value = "logPath", required = false) String logPath,
			@RequestParam(value = "outputPath", required = false) String outputPath,
			@RequestParam(value = "regexDebut", required = false) String regexDebut,
			@RequestParam(value = "regexFin", required = false) String regexFin,
			@RequestParam(value = "tpsVieMinStock", required = false) Integer tpsVieMinStock,
			@RequestParam(value = "nbLignesDeSuite", required = false) Integer nbLignesDeSuite,
			@RequestParam(value = "associatedAnalyzer", required = false) String associatedAnalyzer) {
		
		ParamAgentToManage paramAgent = agents.get(idAgent);
		if (paramAgent == null)
			return problem(mav, "cet id n'est pas reconnu", "/agents/selectAgent");
		int pahc = paramAgent.hashCode();
		paramAgent.setAppName(appName);
		paramAgent.setLogPath(logPath);
		paramAgent.setOutputPath(outputPath);
		paramAgent.setRegexDebutLog(regexDebut);
		paramAgent.setRegexFinLog(regexFin);
		paramAgent.setTpsVieMinStock(tpsVieMinStock);
		paramAgent.setNbLignesDeSuite(nbLignesDeSuite);
		paramAgent.setAssociatedAnalyzer(associatedAnalyzer);
		mav.addObject("changement", pahc != paramAgent.hashCode());
		mav.addObject("error_message", "");
		mav.addObject("agent", paramAgent);
		mav.setViewName("agents/modifParam");
		System.out.println("BBBBBBBBBBBBBBB");
		System.out.println(paramAgent.getId());
		return mav;
	}
	
//	@RequestMapping(value = "/agents/modifParam", method = RequestMethod.GET)
//	@ResponseBody
//	ModelAndView modifParamErr(ModelAndView mav, @RequestParam(value = "selection", required = true) String idAgent,
//			@RequestParam(value = "appName", required = false) String appName,
//			@RequestParam(value = "logPath", required = false) String logPath,
//			@RequestParam(value = "outputPath", required = false) String outputPath,
//			@RequestParam(value = "regexDebut", required = false) String regexDebut,
//			@RequestParam(value = "regexFin", required = false) String regexFin,
//			@RequestParam(value = "tpsVieMinStock", required = false) Integer tpsVieMinStock,
//			@RequestParam(value = "nbLignesDeSuite", required = false) Integer nbLignesDeSuite,
//			@RequestParam(value = "associatedAnalyzer", required = false) String associatedAnalyzer) {
//
//		ParamAgentToManage paramAgent = agents.get(idAgent);
//		if (paramAgent == null)
//			return problem(mav, "cet id n'est pas reconnu", "/agents/selectAgent");
//		mav.addObject("changement", false);
//		mav.addObject("error_message", "Un probleme est survenu. Les données reçues semblent corrompues");
//		mav.addObject("agent", paramAgent);
//		mav.setViewName("agents/modifParam");
//		return mav;
//	}

	
	
	// @RequestMapping(value = "/agents/modifParam", method = RequestMethod.GET)
	// @ResponseBody
	// ModelAndView modifParam(ModelAndView mav,
	// @RequestParam(value = "regexDebut", required = false, defaultValue =
	// DEFAULT_STRING) String regexDebut,
	// @RequestParam(value = "regexFin", required = false, defaultValue =
	// DEFAULT_STRING) String regexFin,
	// @RequestParam(value = "tpsVieMinStock", required = false, defaultValue =
	// DEFAULT_STRING) String tpsVieMinStock,
	// @RequestParam(value = "nbLignesLog", required = false, defaultValue =
	// DEFAULT_STRING) String nbLignesLog) {
	//
	// int oldHashCode = paramAgent.hashCode();
	//
	// if (!regexDebut.equals(DEFAULT_STRING))
	// paramAgent.setRegexDebutLog(regexDebut);
	// if (!regexFin.equals(DEFAULT_STRING))
	// paramAgent.setRegexFinLog(regexFin);
	//
	//
	// try {
	// paramAgent.setTpsVieMinStock(Integer.parseInt(tpsVieMinStock));
	// paramAgent.setNbLignesDeSuite(Integer.parseInt(nbLignesLog));
	// mav.addObject("error_message","");
	//
	// } catch (Exception e){
	// if (!tpsVieMinStock.equals(DEFAULT_STRING) ||
	// !nbLignesLog.equals(DEFAULT_STRING))
	// mav.addObject("error_message","Les valeurs entrées ne sont pas toutes
	// correctes");
	// else
	// mav.addObject("error message","");
	// }
	//
	// mav.addObject("changement",oldHashCode != paramAgent.hashCode());
	// mav.addObject("regexDebut", paramAgent.getRegexDebutLog());
	// mav.addObject("regexFin", paramAgent.getRegexFinLog());
	// mav.addObject("tpsVieMinStock", paramAgent.getTpsVieMinStock());
	// mav.addObject("nbLignesLog", paramAgent.getNbLignesDeSuite());
	// mav.setViewName("agents/modifParam");
	// return mav;
	// }
}
