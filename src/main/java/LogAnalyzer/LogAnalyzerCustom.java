package LogAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class LogAnalyzerCustom extends LogAnalyzer{
	private ParamLogAnalyzerCustom paramLog;
	
//	Map<String, String> map = ...
//			for (Map.Entry<String, String> entry : map.entrySet())
//			{
//			    System.out.println(entry.getKey() + "/" + entry.getValue());
//			}
	public void LogAnalyzerCustom() {
		//TODO faire le constructeur
	}

	@Override
	public Collection<String> getAvailableDatas() {
		ArrayList<String> res = new ArrayList<String>();
		Map<String, DataCatcher> map = paramLog.getDatacatchers();
		for (Map.Entry<String, DataCatcher> entry : map.entrySet())
		{
		    res.add(entry.getValue().catchData(""+content));
		}
		res.add(this.getSource());
		res.add(""+this.getContent());
		return res;
	}

	@Override
	public String getData(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
