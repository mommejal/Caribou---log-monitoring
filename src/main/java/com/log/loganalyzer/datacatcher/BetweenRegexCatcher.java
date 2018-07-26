package com.log.loganalyzer.datacatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetweenRegexCatcher extends DataCatcher {
	private String regexDebut;
	private String regexFin;
	private Pattern patternDebut;
	private Pattern patternFin;

	public BetweenRegexCatcher(String name, String regexDebut, String regexFin, String typeReturned) {
		super(name, typeReturned);
		this.regexDebut = regexDebut;
		patternDebut = Pattern.compile(regexDebut);
		this.regexFin = regexFin;
		patternFin = Pattern.compile(regexFin);
	}

	@Override
	public String catchDataString(String input) {
		Matcher mDebut = patternDebut.matcher(input);
		Matcher mFin = patternFin.matcher(input);
		if (mDebut.find() && mFin.find())
			return input.substring(mDebut.start(),mFin.start()-1);
		else
			return null;
	}

	public String getRegexDebut() {
		return regexDebut;
	}

	public void setRegexDebut(String regexDebut) {
		this.regexDebut = regexDebut;
		patternDebut = Pattern.compile(regexDebut);
	}

	public String getRegexFin() {
		return regexFin;
	}

	public void setRegexFin(String regexFin) {
		this.regexFin = regexFin;
		patternFin = Pattern.compile(regexFin);
	}
	
	

}
