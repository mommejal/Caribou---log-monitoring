package com.appweb.controllers;

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

import com.agent.AgentManager;
import com.agent.paramagent.ParamAgentToManage;
import com.agent.paramagent.ParamAgentToManageBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.log.LightLog;
import com.mongodb.Mongo;


@RestController
@Component
@EnableMongoRepositories(basePackageClasses = com.dao.LogDAO.class)
@Repository
public class RESTController extends AbstractController {

	@Autowired
	Gson gson;

	@Autowired
	AgentManager agents;

	@Autowired
	Mongo mongo;

	@Autowired
	MongoDbFactory mongoDbFactory;

	@Autowired
	ModelAndView mav;

	@RequestMapping(value = "/newAgent", method = RequestMethod.POST)
	@ResponseBody
	void newAgent(@RequestBody String newAgentInfo) {
		ParamAgentToManage newParamAgent = ParamAgentToManageBuilder.build(newAgentInfo);
		System.out.println("naissance de :" + newParamAgent.toString());
		agents.insert(newParamAgent);
	}
	
	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog,
			@RequestParam(value = "idAgent", required = true) String idAgent) {
		// Fonction qui convertit le json en objet java pour sauvegarder les r�sulats
		// dans la BDD
//		System.out.println("je reçois :");
		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<Queue<Queue<String>>>() {
		}.getType());
		for (Queue<String> log : logs) {
			dao.save(new LightLog("id",log, idAgent));
//			System.out.println(log.toString());
//			System.out.println("-----");
		}
//		System.out.println("--------------");
	}
	

	@RequestMapping(value = "/getParamAgent", method = RequestMethod.GET)
	String paramOutcome(@RequestParam(value = "idAgent", required = true) String idAgent) {
//		System.out.println(idAgent + " maj ses params");
		return agents.get(idAgent).toSendStandard();
	}
	
	
}