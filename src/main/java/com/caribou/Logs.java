package com.caribou;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document
public class Logs {

	@Id
	private Integer id;
	private String msg;
	private static Pattern pattern;
	private static Matcher matcher;

	public Logs(Integer id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}

//	public Logs(String msg) {
////		super();
//		this.msg = msg;
//	}

	// public Integer getId() {
	// return id;
	// }

	public Integer getId() {

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
			return id;
		}
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSeverityLvl() {
		pattern = Pattern.compile(".{12} ");
		matcher = pattern.matcher(msg);
		if (matcher.find()) {
			int debut = matcher.end();
			pattern = Pattern.compile(".{12} [a-zA-Z]*");
			matcher = pattern.matcher(msg);
			matcher.find();
			int fin = matcher.end();
			String res = new String();
			res = msg.substring(debut, fin);
			return res;
		} else {
			return "UNKNOWN";
		}
	}

	public String getDate() {
		return msg.substring(0, 12);
	}
}
