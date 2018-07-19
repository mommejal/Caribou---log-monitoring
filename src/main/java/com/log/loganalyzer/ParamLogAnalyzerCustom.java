package com.log.loganalyzer;

import java.util.Map;

import com.log.loganalyzer.datacatcher.BetweenRegexCatcher;
import com.log.loganalyzer.datacatcher.DataCatcher;
import com.log.loganalyzer.datacatcher.RegexCatcher;

public class ParamLogAnalyzerCustom {
	Map<String, DataCatcher> datacatchers;
	
	public ParamLogAnalyzerCustom(Map<String, DataCatcher> datacatchers) {
		super();
		this.datacatchers = datacatchers;
	}
	
	
	
	public Map<String, DataCatcher> getDatacatchers() {
		return datacatchers;
	}

	public void addDataCatcher(String nameofdatacatcher, String regexdebut) {
		DataCatcher datacatchertoadd = new RegexCatcher(regexdebut);
		datacatchers.put(nameofdatacatcher, datacatchertoadd);
	}
	
	public void addDataCatcher(String nameofdatacatcher, String regexdebut, String regexfin) {
		DataCatcher datacatchertoadd = new BetweenRegexCatcher(regexdebut, regexfin);
		datacatchers.put(nameofdatacatcher, datacatchertoadd);
	}
}
