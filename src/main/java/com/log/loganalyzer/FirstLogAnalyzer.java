package com.log.loganalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstLogAnalyzer extends LogAnalyzer {
	
	private static Pattern pattern;
	private static Matcher matcher;
	
	private final static String name = "FirstLogAnalyzer";

	public FirstLogAnalyzer(String id, Collection<String> content, String source) {
		super(name, id, content, source);
	}

//	@Override
//	public Collection<String> getAvailableDatas() {
//		ArrayList<String> res = new ArrayList<String>();
//		res.add(this.getSeverityLvl());
//		res.add(this.getSource());
//		res.add(""+this.getContent());
//		return res;
//	}
	
	@Override
	public Collection<String> getAvailableDatas() {
		ArrayList<String> res = new ArrayList<String>();
		res.add("SeverityLvl");
		res.add("Source");
		res.add("Date");
		res.add("Content");
		return res;
	}

	@Override
	public String getData(String data) {
		switch (data) {
		case "Content":
			return getContent().toString();
		case "Date": 
			return getDate();
		case "SeverityLvl":
			return getSeverityLvl();
		case "Source":
			return getSource();
		default: //error handling
			throw new IllegalArgumentException("Il n'y pas l'argument : "+data+" dans ce log");
		}

	}

	public String getSource() {
		return source;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (((content) == null) ? 0 : content.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	public String getSeverityLvl() {
		pattern = Pattern.compile("( INFO )|( DEBUG )|( ERROR )|( WARN )");
		matcher = pattern.matcher(""+content);
		int debut = 0;
		int fin = 0;
		if (matcher.find()) {
			debut = matcher.start();
			fin = matcher.end();
			String res = new String();
			res = (""+content).substring(debut, fin);
			return res;
		} else {
			return "UNKNOWN";
		}
	}

	public String getDate() {
		// Detecte la date de type heure:minute:seconde.millisecondes
		pattern = Pattern.compile("[0-9\\[][0-9]:[0-9][0-9]:[0-9][0-9][.,][0-9][0-9][0-9]");
		matcher = pattern.matcher(""+content);
		int debut = 0;
		int fin = 0;
		if (matcher.find()) {
			debut = matcher.start();
			fin = matcher.end();
			if ((""+content).charAt(debut) == '[') {
				debut = debut + 1;
			}
			return (""+content).substring(debut, fin);
		} else {
			return "UNKNOWN";
		}
	}


	public void setSource(String source) {
		this.source = source;
	}

}
