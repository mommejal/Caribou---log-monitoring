package com.log.loganalyzer.datacatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCatcher extends DataCatcher {
	private String regex;
	private Pattern pattern;

	
	public RegexCatcher(String name, String regex, String typeReturned) {
		super(name, typeReturned);
		this.regex = regex;
		pattern = Pattern.compile(regex);
	}

	@Override
	public String catchDataString(String input) {
		Matcher matcher = pattern.matcher(input);
		if (matcher.find())
			return input.substring(matcher.start(),matcher.end());
		else
			return null;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String motif) {
		this.regex = motif;
		pattern = Pattern.compile(regex);
		
	}

}
