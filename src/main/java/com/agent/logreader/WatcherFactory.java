package com.agent.logreader;

import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;

import com.agent.ParamAgent;

public class WatcherFactory {
	private Path logPath;
	private static final String REGEX_DEBUT_LOG_DEFAULT = "^\\d?\\d:\\d\\d:\\d\\d";
	private static final String REGEX_FIN_LOG_DEFAULT = "";

	public WatcherFactory(Path logPath) {
		this.logPath = logPath;
	}

	public WatcherFactory addMyWatcher(String regexDefault, String outputPath) throws Exception {
		ParamAgent paramDefault = new ParamAgent(REGEX_DEBUT_LOG_DEFAULT, REGEX_FIN_LOG_DEFAULT, 5000, 2000);
		MyWatcher watcher = new MyWatcher(logPath, paramDefault, outputPath);
		logPath.toAbsolutePath().getParent().register(watcher.getService(), StandardWatchEventKinds.ENTRY_MODIFY);
		watcher.run();
		return this;
	}

}
