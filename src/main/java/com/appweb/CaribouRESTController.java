package com.appweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agent.AgentManager;
import com.agent.paramagent.ParamAgentToManage;
import com.agent.paramagent.ParamAgentToManageBuilder;
import com.google.gson.Gson;
import com.mongodb.Mongo;


@RestController
@Component
@EnableMongoRepositories(basePackageClasses = com.appweb.LogsRepository.class)
@Repository
public class CaribouRESTController {

	//private static final String DEFAULT_STRING = "RIP_F-ZERO";

	@Autowired
	Gson gson;

	@Autowired
	AgentManager agents;

	@Autowired
	LogsRepository logsRepository;

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
	/*
	@RequestMapping(value = "/logIncome", method = RequestMethod.POST)
	@ResponseBody
	void logIncome(@RequestBody String newlog) {
		System.out.println("reception de :");
		int id = 0;
		//Logs tmp = new Logs(id, "");
		Queue<Queue<String>> logs = gson.fromJson(newlog, new TypeToken<ArrayDeque<ArrayDeque<String>>>() {
		}.getType());

		for (Queue<String> log : logs) {
			for (String line : log) {
				System.out.println(line);
				//tmp = new Logs(id, line);
				// id = tmp.getId();
				// tmp.setId(id);
				id++;
				//logsRepository.save(tmp);
			}
			System.out.println("-----");
		}
		System.out.println("--------------");
	}

	@RequestMapping(value = "/getParamAgent", method = RequestMethod.GET)
	String paramOutcome(@RequestParam(value = "idAgent", required = false, defaultValue = DEFAULT_STRING) String idAgent) {
		return agents.get(idAgent).toSendStandard();
	}
	*/
	

	@RequestMapping(value = "/technique/gestionBdd/viderBdd", method = RequestMethod.GET)
	ModelAndView viderBdd(ModelAndView mav) {
		mongo.dropDatabase(mongoDbFactory.getDb().getName());
		mav.setViewName("technique/gestionBdd");
		return mav;
	}
	
	
}