package LogAnalyzer;

import java.util.Map;

import com.bdd.LightLog;

public class LogAnalyzerBuilder {
	
	public Map <String,DataCatcher> customedParamLogs;
	public Object buildLog(LightLog content, String type) {
		// Il faut retourner unb new <Type>
		
		 try {
			 
// returns the Class object for the class with the specified name
//	         Class cls = Class.forName(type);
//	         Object res = cls.newInstance();
//	         return res;
	      } catch(ClassNotFoundException ex) {
	         System.out.println(ex.toString());
	      }
	   }
		
	
	
	
//	public LogAnalyzerCustom buildCstmLog(LightLog log, HashMap<String,DataCatcher> param) {
//		//TODO :pass
////		return new LogAnalyzerCustom();
//	}
}
