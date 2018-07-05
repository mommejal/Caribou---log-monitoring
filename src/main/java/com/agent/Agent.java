package com.agent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.agent.logreader.WatcherFactory;

public class Agent {
	protected String OUTPUT_PATH = "http://localhost:8080";
	// private static final String LOG_PATH = Paths.get("logs.log").toString();
	protected Path logPath = Paths.get("logs.log");
	private final String ID;
	
	public Agent(final String ID){
		this.ID = ID;
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
		new WatcherFactory(logPath).addMyWatcher(".*", OUTPUT_PATH);
	}
	
	public String getID() {
		return ID;
	}

}
