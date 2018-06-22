package com.LogReader;

import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;

public class WatcherFactory {
	private Path logPath;

	public WatcherFactory(Path logPath) {
		this.logPath = logPath;
	}

	public WatcherFactory addMyWatcher(String regexDefault, String outputPath) throws Exception {
		MyWatcher watcher = new MyWatcher(logPath, regexDefault, outputPath);
		logPath.toAbsolutePath().getParent().register(watcher.getService(), StandardWatchEventKinds.ENTRY_MODIFY);
		watcher.run();
		return this;
	}

}
