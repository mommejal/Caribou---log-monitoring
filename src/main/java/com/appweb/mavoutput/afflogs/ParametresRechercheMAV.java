package com.appweb.mavoutput.afflogs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParametresRechercheMAV {

	@RequestMapping(value = "/AffLogs/parametres_recherche", method = RequestMethod.GET)
	ModelAndView parametresRecherche(ModelAndView mav,
			@RequestParam(value = "selectedregexp", required = false,defaultValue="") String selectedregexp)
	{
		mav.setViewName("AffLogs/parametres_recherche");
		return mav;
	}
}
