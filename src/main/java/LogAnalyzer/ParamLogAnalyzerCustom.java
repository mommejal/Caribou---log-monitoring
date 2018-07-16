package LogAnalyzer;

import java.util.Map;

public class ParamLogAnalyzerCustom {
	Map<String, DataCatcher> datacatchers;
	public DataCatcher w;
	
	public ParamLogAnalyzerCustom(Map<String, DataCatcher> datacatchers) {
		super();
		this.datacatchers = datacatchers;
	}
	
	public void addDataCatcher(DataCatcher datacatchertoadd, String nameofdatacatcher) {
		datacatchers.put(nameofdatacatcher, datacatchertoadd);
	}
}
