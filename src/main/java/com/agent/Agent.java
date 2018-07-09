package com.agent;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.agent.logreader.WatcherFactory;

public class Agent {
	
	protected ParamAgent param;
	
	public Agent(ParamAgent param) {
		super();
		this.param = param;
	}

	public void run() {
		try {
			// EnhancedLogScanner scanner = new
			// EnhancedLogScanner(Paths.get(LOG_PATH),Paths.get(OUTPUT_PATH));
			// scanner.run();
			// new Streamer(LOG_PATH).execute();
			scan();
		} catch (Exception e) {
			Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void scan() throws Exception {
		WatcherFactory.addMyWatcher(param);
	}

	public ParamAgent getParam() {
		return param;
	}

	public void setParam(ParamAgent param) {
		this.param = param;
	}
	
	
}
