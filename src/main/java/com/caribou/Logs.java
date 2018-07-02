package com.caribou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Logs {

	@Id
	private String _id;
	private Integer idlog;
	private String msg;
	private static Pattern pattern;
	private static Matcher matcher;

//	public Logs(Integer idlog, String msg) {
//		super();
//		this.idlog = idlog;
//		this.msg = msg;
//	}

	 public Logs(String msg) {
//	 super();
	 this.msg = msg;
	 }

	// public Integer getId() {
	// return id;
	// }

	public Integer getIdlog() {

		pattern = Pattern.compile("(?:(?s).*)-{10,}\r\nID: ");
		matcher = pattern.matcher(msg);
		if (matcher.find()) {

			int debut = matcher.end();
			pattern = Pattern.compile("(?s).*------\r\nID: [0-9]*\r\n");
			matcher = pattern.matcher(msg);
			matcher.find();
			int fin = matcher.end();
			String res = new String();
			res = msg.substring(debut, fin);
			return Integer.parseInt(res);
		} else {
			return idlog;
		}
	}

	public void setIdlog(Integer idlog) {
		this.idlog = idlog;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSeverityLvl() {
		pattern = Pattern.compile("(INFO)|(DEBUG)|(ERROR)|(Exception)|(EXCEPTION)|(WARN )|(WARNING)");
		matcher = pattern.matcher(msg);
		
		int debut = 0;
		int fin = 0;
		if (matcher.find()) {
			debut = matcher.start();
			fin = matcher.end();
			String res = new String();
			res = msg.substring(debut, fin);
			return res;
		} else {
			return "UNKNOWN";
		}
	}

	public String getDate() {
		// Detecte la date de type heure:minute:seconde.millisecondes
		pattern = Pattern.compile("[0-9][0-9]:[0-9][0-9]:[0-9][0-9][.,][0-9][0-9][0-9]");
		matcher = pattern.matcher(msg);
		int debut = 0;
		int fin = 0;
		if (matcher.find()) {
			debut = matcher.start();
			fin = matcher.end();
			return msg.substring(debut, fin);
		} else {
			return "UNKNOWN";
		}
	}
}
