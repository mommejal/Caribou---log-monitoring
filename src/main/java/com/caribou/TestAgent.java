package com.caribou;

import com.agent.Agent;
import com.agent.ParamAgent;

public class TestAgent {

	public static void main(String[] args) {
		
		ParamAgent param1 = new ParamAgent("number one", "logs.log","http://localhost:8080");
		new Agent(param1).run();
		
	}

}
