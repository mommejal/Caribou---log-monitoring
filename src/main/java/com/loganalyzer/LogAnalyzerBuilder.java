package com.loganalyzer;

import java.util.Map;

import com.bdd.LightLog;

public class LogAnalyzerBuilder {
	
	public Map <String, ParamLogAnalyzerCustom> customedParamLogs;
	public LogAnalyzer buildLog(LightLog content, String type) {
		// Si on a un nouveau type d eLogs � ajouter, il suffit d'ajouter un case qui renvoie le new <Object>()
		switch (type) {
		case "FirstLogAnalyzer": 
			return new FirstLogAnalyzer(content);
		case "LogAnalyzerCustom":
			return new LogAnalyzerCustom(content, customedParamLogs.get(type));
		default: //error handling
			throw new IllegalArgumentException("Le type : "+type+" n'a pas encore �t� d�fini");
		}
		
		// Eventuellement cette version est plus automatique mais aussi plus dangereuse au cas ou l'argument est mauvais ou du meme nom qu'une classe d�j� utilis�e
//		 try {
//// returns the Class object for the class with the specified name
//	         Class cls = Class.forName(type);
//	         Object res = cls.newInstance();
//	         return res;
//	      } catch(ClassNotFoundException ex) {
//	         System.out.println(ex.toString());
//	      }
	   }
		
	
	
	
//	public LogAnalyzerCustom buildCstmLog(LightLog log, HashMap<String,DataCatcher> param) {
//		//TODO :pass
////		return new LogAnalyzerCustom();
//	}
}
