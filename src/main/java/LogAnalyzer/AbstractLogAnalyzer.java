package LogAnalyzer;

import java.util.Collection;

public abstract class AbstractLogAnalyzer {
	
	public AbstractLogAnalyzer() {
	}
	public String source;

	public abstract String getSource();

	public abstract Collection<String> getAvailableDatas();

	public abstract Object getData();

	public abstract boolean isBefore(AbstractLogAnalyzer log, String data);

	public abstract boolean hasSame(AbstractLogAnalyzer log, String data);

	public abstract int hashCode();
}
