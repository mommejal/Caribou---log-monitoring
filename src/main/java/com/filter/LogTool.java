package com.filter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.LogDAO;
import com.log.loganalyzer.LogAnalyzer;

public abstract class LogTool {
	@Autowired
	LogDAO dao;

	public abstract Collection<LogAnalyzer> filterBy(Collection<LogAnalyzer> logs, String attribut, String filtreregexp);
	public abstract void filterBy(String attribut, String filtreregexp);
}
