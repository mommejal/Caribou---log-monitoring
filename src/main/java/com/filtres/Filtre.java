package com.filtres;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filtre {
//	String pattern = "([.\\s]+?-{10,}){2}"
	String pattern = "([(?s).&&[^-]]*(-{1,9}[(?s).&&[^-]]+)*-{10,}){4}";
//	String pattern = "((.*[\r\n]?)*?-{10,40}+){4}";
	public int getIdFromStringLog(String input) {
		String patternbeg = "(?s).*-{10}\r\nID: ";
		Matcher a = Pattern.compile(patternbeg).matcher(input);
		int debut = a.end();
		String patternend = "(?s).*-{10}\r\nID: ";
		Matcher b = Pattern.compile(patternend).matcher(input);
		int fin = b.end();
		String res =  new String();
		res = res.substring(debut,fin);
		return Integer.parseInt(res);
	}
}


