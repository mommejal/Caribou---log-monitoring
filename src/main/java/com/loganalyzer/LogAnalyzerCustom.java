package com.loganalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import com.bdd.LightLog;
import com.loganalyzer.datacatcher.DataCatcher;

public class LogAnalyzerCustom extends LogAnalyzer {
	private ParamLogAnalyzerCustom paramLog; //On pourra rajouter des datacatchers depuis cette variable avec addDataCatcher
	
	public LogAnalyzerCustom(LightLog content, ParamLogAnalyzerCustom parametres) {
		// Finalement cce serait le role de LogAnalyzerBuilder plutot
		super();
		this.paramLog = parametres;
	}

	@Override
	public Collection<String> getAvailableDatas() {
		ArrayList<String> res = new ArrayList<String>();
		Map<String, DataCatcher> map = paramLog.getDatacatchers();
		for (Map.Entry<String, DataCatcher> entry : map.entrySet()) {
			res.add(entry.getValue().catchData("" + content));
		}
		res.add(this.getSource());
		res.add("" + this.getContent());
		return res;
	}

	@Override
	public String getData(String data) {
		Map<String, DataCatcher> map = paramLog.getDatacatchers();
		String res = "no Data for" + data;
		for (Map.Entry<String, DataCatcher> entry : map.entrySet()) {
			if (entry.getKey().equals(data)) {
				res = entry.getValue().catchData("" + content);
				break;
			} 
//				throw new IllegalArgumentException("La donn�e : " + data + " n'a pas encore �t� d�fini ou n'exsite pas");
			}
		return res;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((paramLog == null) ? 0 : paramLog.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogAnalyzerCustom other = (LogAnalyzerCustom) obj;
		if (paramLog == null) {
			if (other.paramLog != null)
				return false;
		} else if (!paramLog.equals(other.paramLog))
			return false;
		return true;
	}
	


}
