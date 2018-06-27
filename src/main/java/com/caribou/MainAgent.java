package com.caribou;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.agent.logreader.WatcherFactory;

public class MainAgent {
	private static final String OUTPUT_PATH = "http://localhost:8080";
	// private static final String LOG_PATH = Paths.get("logs.log").toString();
	private Path logPath = Paths.get("logs.log");

	public static void main(String[] args) {
		try {
			// EnhancedLogScanner scanner = new
			// EnhancedLogScanner(Paths.get(LOG_PATH),Paths.get(OUTPUT_PATH));
			// scanner.run();
			// new Streamer(LOG_PATH).execute();
			new MainAgent().scan();
		} catch (Exception e) {
			Logger.getLogger(MainAgent.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}

	}

	public void scan() throws Exception {
		new WatcherFactory(logPath).addMyWatcher(".*", OUTPUT_PATH);
	}

}
