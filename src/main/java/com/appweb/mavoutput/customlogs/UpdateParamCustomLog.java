package com.appweb.mavoutput.customlogs;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;
import com.log.loganalyzer.LogAnalyzerBuilder;
import com.log.loganalyzer.ParamLogAnalyzerCustom;
import com.log.loganalyzer.datacatcher.DataCatcher;

@Controller
public class UpdateParamCustomLog extends Displayer {
	
	@Autowired LogAnalyzerBuilder builder;
	
	@ResponseBody            
	@GetMapping("/customlogs/processingUpdateParamCustomLog")
	ModelAndView processingUpdateParamCustomLog(ModelAndView mav,
			@RequestParam(required=true) String type,
			@RequestParam(required=true) String nameofdatacatcher,
			@RequestParam(required=true) String regexdebut,
			@RequestParam(required=false,defaultValue="") String regexfin
			
			) {
		builder.customedParamLogs = new HashMap <String, ParamLogAnalyzerCustom>();
		builder.customedParamLogs.put("LogCustomTest", new ParamLogAnalyzerCustom(new HashMap<String, DataCatcher>()));
		System.out.println("le builder contient"+builder.customedParamLogs.keySet());
		ParamLogAnalyzerCustom paramtoupdate = builder.customedParamLogs.get(type);
		if (regexfin.isEmpty()) {
			paramtoupdate.addDataCatcher(nameofdatacatcher, regexdebut);
		}
		else {
			paramtoupdate.addDataCatcher(nameofdatacatcher, regexdebut,regexfin);
		}
		System.out.println("Sortie de processing update");
		mav.setViewName("/customlogs/menuCustomLog");
		return mav;
	}
	
	@ResponseBody
	@GetMapping("/customlogs/updateParamCustomLog")
	ModelAndView updateParamCustomLog(ModelAndView mav) {
		mav.setViewName("/customlogs/updateParamCustomLog");
		return mav;
	}

}
