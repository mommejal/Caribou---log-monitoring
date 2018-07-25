package com.caribou;

import com.agent.Agent;
import com.agent.paramagent.ParamAgentToWork;
import com.loggenerator.MainLogGenerator;

public class TestAgent {

	public static void main(String[] args) {

		ParamAgentToWork param1 = new ParamAgentToWork("number_one", "the_original_test_app", "logs.log",
				"http://localhost:8080");
		ParamAgentToWork param2 = new ParamAgentToWork("number_two", "the_second_app", "logs2.log",
				"http://localhost:8080");

		Thread thread1a = new Thread() {
			public void run() {
				new Agent(param1).run();
			}
		};
		
		Thread thread1b = new Thread() {
			public void run() {
				MainLogGenerator.generate(200, "server.log", "logs.log");
			}
		};

		Thread thread2a = new Thread() {
			public void run() {
				new Agent(param2).run();
			}
		};

		Thread thread2b = new Thread() {
			public void run() {
				MainLogGenerator.generate(200, "server4.log", "logs2.log");
			}
		};
		
		thread1a.start();
		thread1b.start();
		thread2a.start();
		thread2b.start();
		
	}

}
