package LogAnalyzer;

import java.util.Map;

import com.bdd.LightLog;

public class LogAnalyzerBuilder {
	
	public Map <String, ParamLogAnalyzerCustom> customedParamLogs;
	public LogAnalyzer buildLog(LightLog content, String type) {
		// Si on a un nouveau type d eLogs à ajouter, il suffit d'ajouter une boucle si qui renvoie le new <Object>()
		try {
		if (type.equals("FirstLogAnalyzer")) {
			return new FirstLogAnalyzer(content);
		}
		else
			return null;
		}
		catch (Exception e){
			System.out.println("Le type du Log n'a pas été reconnu");
			//TODO Faire une meilleure exception
		return null;
		}
		
		// Eventuellement cette version est plus automatique mais aussi plus dangereuse au cas ou l'argument est mauvais ou du meme nom qu'une classe déjà utilisée
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
