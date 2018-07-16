package LogAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstLogAnalyzer extends AbstractLogAnalyzer {
	public String msg;
	private static Pattern pattern;
	private static Matcher matcher;

	public FirstLogAnalyzer(String source, String msg) {
		this.source = source;
		this.msg = msg;
	}

	@Override
	public Collection<String> getAvailableDatas() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(this.getMsg());
		res.add(this.getSeveritylvl());
		res.add(this.getSource());
		return res;
	}

	@Override
	public Object getData() {
		return this;
	}

	@Override
	public boolean isBefore(AbstractLogAnalyzer log, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSame(AbstractLogAnalyzer log, String data) {
//		try
//		{
//			return true;
//		}
//		catch (Exception e)‏
//		{
//			System.out.println("Les deux logs n'ont pas les memes types d'argument");
//		}

		return false;
	}

	public String getSource() {
		return source;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	public String getMsg() {
		return msg;
	}

	public String getSeveritylvl() {
		pattern = Pattern.compile("( INFO )|( DEBUG )|( ERROR )|( WARN )");
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
			return msg.substring(debut, fin);
		} else {
			return "UNKNOWN";
		}
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
