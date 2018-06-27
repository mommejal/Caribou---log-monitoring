package com.LogReader;

import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.util.regex.Pattern;

public class WatcherFactory {
	private Path logPath;

	public WatcherFactory(Path logPath) {
		this.logPath = logPath;
	}

	public WatcherFactory addMyWatcher(String regex, Path outputPath) throws Exception {
		MyWatcher watcher = new MyWatcher(logPath, Pattern.compile(regex), outputPath);
		logPath.toAbsolutePath().getParent().register(watcher.getService(), StandardWatchEventKinds.ENTRY_MODIFY);
		watcher.run();
		return this;
	}

}
