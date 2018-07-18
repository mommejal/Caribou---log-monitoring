package com.loganalyzer.datacatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetweenRegexCatcher implements DataCatcher {
	private String regexDebut;
	private String regexFin;
	private Pattern pattern;
	private Matcher matcher;

	public BetweenRegexCatcher(String regexDebut, String regexFin) {
		super();
		this.regexDebut = regexDebut;
		this.regexFin = regexFin;
	}

	@Override
	public String catchData(String input) {
		pattern = Pattern.compile(input);
		matcher = pattern.matcher(regexDebut);
		matcher.find();
		int debut = matcher.start();
		pattern = Pattern.compile(input);
		matcher = pattern.matcher(regexFin);
		int fin = matcher.end();
		return input.substring(debut, fin);
	}

	public String getRegexDebut() {
		return regexDebut;
	}

	public void setRegexDebut(String regexDebut) {
		this.regexDebut = regexDebut;
	}

	public String getRegexFin() {
		return regexFin;
	}

	public void setRegexFin(String regexFin) {
		this.regexFin = regexFin;
	}
	
	

}
