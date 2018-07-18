package LogAnalyzer;

import java.util.Collection;

import com.bdd.LightLog;

public abstract class LogAnalyzer extends LightLog {
	public LogAnalyzer() {
		super();
	}
	public LogAnalyzer(LightLog content) {
			this.source = content.getSource();
			this._id = content.getId();
			this.content = content.getContent();
	}

	public abstract Collection<String> getAvailableDatas();

	public abstract String getData(String data);

	public int isBefore(LogAnalyzer log, String data) {
		return (log.getData(data).compareTo(this.getData(data)));
	}

}
