package com.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.log.loganalyzer.LogAnalyzer;

public class LogFinder extends LogTool {

	@Autowired
	LogFinder logfinder;
	Collection<LogAnalyzer> LogCache;

	@Override
	public Collection<LogAnalyzer> filterBy(Collection<LogAnalyzer> logs, String attribut, String filtreregexp) {
		// HashSet<LogAnalyzer> res = new HashSet<>();
		Collection<LogAnalyzer> res = new ArrayList<LogAnalyzer>();
		Pattern pattern;
		Matcher matcher;
		for (LogAnalyzer log : logs) {
			pattern = Pattern.compile(filtreregexp);
			matcher = pattern.matcher((String) log.getData(attribut));
			if (matcher.find())
				res.add(log);
		}
		return res;
	}

	public void filterBy(String attribut, String filtreregexp) {
		HashSet<LogAnalyzer> res = new HashSet<>();
		Pattern pattern = Pattern.compile(filtreregexp);
		;
		Matcher matcher;
		boolean src = attribut.equals("Source");
		for (LogAnalyzer log : getLogCache()) {
			if (log.getAvailableDatas().contains(attribut) || src) {
				matcher = pattern.matcher(src ? log.getSource() : (String) log.getData(attribut));
				if (matcher.find())
					res.add(log);
			}
		}
		setLogCache(res);
	}

	public Collection<LogAnalyzer> getLogCache() {
		return LogCache;
	}

	public void setLogCache(Collection<LogAnalyzer> logCache) {
		LogCache = logCache;
	}

}
