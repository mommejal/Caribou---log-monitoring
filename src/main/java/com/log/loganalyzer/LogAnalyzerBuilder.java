package com.log.loganalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.log.LightLog;

public class LogAnalyzerBuilder {
	// Construit des nouveaux types de Log, il suffit d'ajouter à buildLog un case pour créer la bonne classe
	public Map <String, ParamLogAnalyzerCustom> customedParamLogs;
	
	public LogAnalyzer buildLog(LightLog log, String type) {
		// Si on a un nouveau type d eLogs ï¿½ ajouter, il suffit d'ajouter un case qui renvoie le new <Object>()
		switch (type) {
		case "FirstLogAnalyzer": 
			return new FirstLogAnalyzer(log.getId(), log.getContent(), log.getSource());
		case "LogAnalyzerCustom":
			return new LogAnalyzerCustom(type, log.getId(), log.getContent(), log.getSource(), customedParamLogs.get(type));
		default: //error handling
			throw new IllegalArgumentException("Le type : "+type+" n'a pas encore Ã©tÃ© dÃ©fini");
		}
		
		// Eventuellement cette version est plus automatique mais aussi plus dangereuse au cas ou l'argument est mauvais ou du meme nom qu'une classe dï¿½jï¿½ utilisï¿½e
//		 try {
//// returns the Class object for the class with the specified name
//	         Class cls = Class.forName(type);
//	         Object res = cls.newInstance();
//	         return res;
//	      } catch(ClassNotFoundException ex) {
//	         System.out.println(ex.toString());
//	      }
	   }
		
	public Collection<LogAnalyzer> buildLogAnalyzer(Collection<LightLog> logs, String type){
		ArrayList<LogAnalyzer> res = new ArrayList<LogAnalyzer>();
		for (LightLog log : logs)
			res.add(buildLog(log, type));
		return res;
	}
	
	public Collection<String> getAvailableDataByType(String type){
		//Renvoie les availabledata pour le log de type "type"
		LightLog tmplightlog = new LightLog();
		LogAnalyzer tmp = buildLog(tmplightlog, type);
		return tmp.getAvailableDatas();
	}
	
	
	
//	public LogAnalyzerCustom buildCstmLog(LightLog log, HashMap<String,DataCatcher> param) {
//		//TODO :pass
////		return new LogAnalyzerCustom();
//	}
}
