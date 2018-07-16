package LogAnalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCatcher implements DataCatcher {
	private String regex;
	private Pattern pattern;
	private Matcher matcher;

	
	public RegexCatcher(String regex) {
		super();
		this.regex = regex;
	}

	@Override
	public String catchData(String input) {
		pattern = Pattern.compile(input);
		matcher = pattern.matcher(regex);
		matcher.find();
		int debut = matcher.start();
		return input.substring(debut);
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String motif) {
		this.regex = motif;
	}

}
