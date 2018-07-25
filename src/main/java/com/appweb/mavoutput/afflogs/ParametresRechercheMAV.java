package com.appweb.mavoutput.afflogs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bdd.ParametresRecherche;
import com.bdd.Recherche;

@Controller
public class ParametresRechercheMAV {
	@Autowired
	ParametresRecherche param;
	@Autowired
	Recherche recherche;
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
}
