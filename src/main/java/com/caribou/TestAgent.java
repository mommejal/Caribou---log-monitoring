package com.caribou;

import com.agent.Agent;
import com.agent.paramagent.ParamAgentToWork;

public class TestAgent {

	public static void main(String[] args) {
		
		ParamAgentToWork param1 = new ParamAgentToWork("number one","the original test app", "logs.log","http://localhost:8080");
		new Agent(param1).run();
		
	}

}