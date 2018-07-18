package com.logs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bdd.ParametresRecherche;

@Document//(collection = "logsRepository")
public class Logs {

	@Autowired ParametresRecherche param;
	@Id
	private int _id;
	private String agent;
	// @Order
	private String date;
	@SuppressWarnings("unused")
	private Integer idlog;

	private String msg;
	@SuppressWarnings("unused")
	private String severitylvl;
	private static Pattern pattern;
	private static Matcher matcher;

	public Logs(int _id,String msg) {
		// super();
		this._id= _id;
		this.msg = msg;
//		setIdlog();
		setSeverityLvl();
		setDate();
	}

	// public Integer getId() {
	// return id;
	// }
	public int get_id() {
		return _id;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public void setIdlog() {

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
			this.idlog = Integer.parseInt(res);
		} else {
			this.idlog = 0;
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
		return severitylvl;
	}

	public void setSeverityLvl() {
		pattern = Pattern.compile("( INFO )|( DEBUG )|( ERROR )|( WARN )");
		matcher = pattern.matcher(msg);
		int debut = 0;
		int fin = 0;
		if (matcher.find()) {
			debut = matcher.start();
			fin = matcher.end();
			String res = new String();
			res = msg.substring(debut, fin);
			this.severitylvl=res;
		} else {
			this.severitylvl="UNKNOWN";
		}
	}

	public void setDate() {
		// Detecte la date de type heure:minute:seconde.millisecondes
			pattern = Pattern.compile("[0-9\\[][0-9]:[0-9][0-9]:[0-9][0-9][.,][0-9][0-9][0-9]");
			matcher = pattern.matcher(msg);
			int debut = 0;
			int fin = 0;
			if (matcher.find()) {
				debut = matcher.start();
				fin = matcher.end();
				if (msg.charAt(debut) == '[') {
					debut = debut + 1;
				}
				this.date = msg.substring(debut, fin);
			} else {
				this.date = "UNKNOWN";
		}
	}

	public String getDate() {
		return date;
	}

	public boolean rechercheMotif(String motifachercher) {
		pattern = Pattern.compile(motifachercher);
		matcher = pattern.matcher(msg);
		return matcher.find();
	}
}
