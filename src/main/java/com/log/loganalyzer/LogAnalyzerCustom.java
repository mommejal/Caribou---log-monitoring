package com.log.loganalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.log.loganalyzer.datacatcher.DataCatcher;

public class LogAnalyzerCustom extends LogAnalyzer {
	private ParamLogAnalyzerCustom paramLog; //On pourra rajouter des datacatchers depuis cette variable avec addDataCatcher

	public LogAnalyzerCustom(String type, String id, Collection<String> content, String source,ParamLogAnalyzerCustom parametres) {
		super(type, id, content, source);
		this.paramLog = parametres;
	}

	@Override
	public Collection<String> getAvailableDatas() {
		ArrayList<String> res = new ArrayList<String>();
		Map<String, DataCatcher> map = paramLog.getDatacatchers();
		for (Map.Entry<String, DataCatcher> entry : map.entrySet()) {
			res.add(entry.getKey());
		}
		return res;
	}

	@Override
	public Object getData(String data) {
//		Map<String, DataCatcher> map = paramLog.getDatacatchers();
//		String res = null ;//= "no Data for" + data;
//		for (Map.Entry<String, DataCatcher> entry : map.entrySet()) {
//			if (entry.getKey().equals(data)) {
//				res = entry.getValue().catchData("" + content);
//				break;
//			} 
//		}
		
		DataCatcher dc = paramLog.getDatacatchers().get(data);
		if (dc==null) 
			throw new IllegalArgumentException("La donnée : " + data + " n'a pas encore été défini, n'exsite pas ou est male définie");
		return dc.catchData(String.join(System.lineSeparator(),content));
		}

}
