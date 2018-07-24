package com.appweb.mavoutput.agents;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agent.paramagent.ParamAgentToManage;

@Controller
public class AgentsMonitor {
	
//	private static final String DEFAULT_STRING = "RIP_F-ZERO";
	
	@Autowired
	Map<String, ParamAgentToManage> agents;
	
	@RequestMapping(value = "/agents/selectAgent", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView selectAgent(ModelAndView mav) {
		mav.addObject("(agents",agents);
		mav.setViewName("agents/selectAgent");
		return mav;
	}
	
	
//	@RequestMapping(value = "/agents/modifParam", method = RequestMethod.GET)
//	@ResponseBody
//	ModelAndView modifParam(ModelAndView mav,
//			@RequestParam(value = "regexDebut", required = false, defaultValue = DEFAULT_STRING) String regexDebut,
//			@RequestParam(value = "regexFin", required = false, defaultValue = DEFAULT_STRING) String regexFin,
//			@RequestParam(value = "tpsVieMinStock", required = false, defaultValue = DEFAULT_STRING) String tpsVieMinStock,
//			@RequestParam(value = "nbLignesLog", required = false, defaultValue = DEFAULT_STRING) String nbLignesLog) {
//		
//		int oldHashCode = paramAgent.hashCode();
//		
//		if (!regexDebut.equals(DEFAULT_STRING))
//			paramAgent.setRegexDebutLog(regexDebut);
//		if (!regexFin.equals(DEFAULT_STRING))
//			paramAgent.setRegexFinLog(regexFin);
//		
//		
//		try {
//			paramAgent.setTpsVieMinStock(Integer.parseInt(tpsVieMinStock));
//			paramAgent.setNbLignesDeSuite(Integer.parseInt(nbLignesLog));
//			mav.addObject("error_message","");
//			
//		} catch (Exception e){
//			if (!tpsVieMinStock.equals(DEFAULT_STRING) || !nbLignesLog.equals(DEFAULT_STRING))
//				mav.addObject("error_message","Les valeurs entrées ne sont pas toutes correctes");
//			else
//				mav.addObject("error message","");
//		}
//		
//		mav.addObject("changement",oldHashCode != paramAgent.hashCode());
//		mav.addObject("regexDebut", paramAgent.getRegexDebutLog());
//		mav.addObject("regexFin", paramAgent.getRegexFinLog());
//		mav.addObject("tpsVieMinStock", paramAgent.getTpsVieMinStock());
//		mav.addObject("nbLignesLog", paramAgent.getNbLignesDeSuite());
//		mav.setViewName("agents/modifParam");
//		return mav;
//	}
}
