package com.appweb.mavoutput.monitors;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.applications.monitor.AbstractMonitor;
import com.log.loganalyzer.LogAnalyzerBuilder;
import com.log.loganalyzer.ParamLogAnalyzerCustom;
import com.log.loganalyzer.datacatcher.BetweenRegexCatcher;
import com.log.loganalyzer.datacatcher.DataCatcher;
import com.log.loganalyzer.datacatcher.RegexCatcher;

@Controller
public class LogAnalyzerMonitor extends AbstractMonitor{

	// private static final String DEFAULT_STRING = "RIP_F-ZERO";

	@Autowired
	LogAnalyzerBuilder logAnalyzerBuilder;

	@RequestMapping(value = "/analyzers/selection", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView selectAgent(ModelAndView mav) {
		mav.addObject("nonEmpty",!logAnalyzerBuilder.getCustomedParamLogs().isEmpty());
		mav.addObject("source", logAnalyzerBuilder.getCustomedParamLogs().values());
		mav.addObject("target", "/agents/modif");
		mav.addObject("getValue", "getType()");
		mav.addObject("getText", "getType()");
		mav.addObject("TextButton", "selectionner cet analyseur");
		mav.setViewName("analyzers/selection");
		return mav;
	}


	@RequestMapping(value = "/analyzers/modif", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView modifParam(ModelAndView mav, @RequestParam(value = "selection", required = true) String type,
			@RequestParam(value = "isNew", required = false, defaultValue = "false" ) boolean isNew) {

		if (isNew)
			logAnalyzerBuilder.getCustomedParamLogs().put(type, new ParamLogAnalyzerCustom(new HashMap<String, DataCatcher>()));
		
		ParamLogAnalyzerCustom logAn = logAnalyzerBuilder.getCustomedParamLogs().get(type);
		if (logAn == null)
			return problem(mav, "cet id n'est pas reconnu", "/analyzers/selection");

		mav.addObject("changement", false);
		mav.addObject("error_message", "");
		mav.addObject("logAn", logAn);
		
		mav.addObject("source", logAn.getDatacatchers());
		mav.addObject("target", "/agents/modif");
		mav.addObject("getValue", "getName()");
		mav.addObject("getText", "getName()");
		mav.addObject("TextButton", "selectionner cet analyseur");
		
		mav.setViewName("analyzers/modif");
		return mav;
	}

	@RequestMapping(value = "/analyzers/modif/add", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView modifParamPlus(ModelAndView mav, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "regexDebut", required = true) String regexDebut,
			@RequestParam(value = "regexFin", required = false) String regexFin,
			@RequestParam(value = "type", required = true) String type) {

		ParamLogAnalyzerCustom logAn = logAnalyzerBuilder.getCustomedParamLogs().get(id);
		if (logAn == null)
			return problem(mav, "cet id n'est pas reconnu", "/analyzers/selection");
		DataCatcher dc;
		if (regexFin == null || regexFin.isEmpty())
			dc = new RegexCatcher(name, regexDebut, type);
		else
			dc = new BetweenRegexCatcher(name, regexDebut, regexFin, type);
		logAn.getDatacatchers().put(dc.getName(), dc);
		
		mav.addObject("changement", true);
		mav.addObject("error_message", "");
		mav.addObject("logAn", logAn);
		mav.setViewName("analyzers/modifParam");
		return mav;
	}

	@RequestMapping(value = "/analyzers/modif/dell", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView modifParamMoins(ModelAndView mav, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name) {

		ParamLogAnalyzerCustom logAn = logAnalyzerBuilder.getCustomedParamLogs().get(id);
		if (logAn == null)
			return problem(mav, "cet id n'est pas reconnu", "/analyzers/selection");
		boolean del = logAn.getDatacatchers().remove(name)!=null;
		
		mav.addObject("changement", del);
		mav.addObject("error_message", del ? (name + " a bien été retiré") : "DataCatcher inconnu");
		mav.addObject("logAn", logAn);
		mav.setViewName("analyzers/modifParam");
		return mav;
	}
}
