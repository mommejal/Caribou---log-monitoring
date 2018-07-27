package com.log.loganalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.agent.paramagent.ParamAgentToManage;
import com.log.LightLog;

public class LogAnalyzerBuilder {
	// Construit des nouveaux types de Log, il suffit d'ajouter à buildLog un case
	// pour créer la bonne classe
	private Map<String, ParamLogAnalyzerCustom> customedParamLogs;

	@Autowired
	Map<String, ParamAgentToManage> paramAgentStock;

	public LogAnalyzerBuilder() {
		super();
		this.customedParamLogs = new HashMap<String, ParamLogAnalyzerCustom>();
	}

	public LogAnalyzer buildLog(LightLog log, String type) {
		// Si on a un nouveau type d eLogs ï¿½ ajouter, il suffit d'ajouter un case qui
		// renvoie le new <Object>()
		switch (type) {
		case "FirstLogAnalyzer":
			return new FirstLogAnalyzer(log.getId(), log.getContent(), log.getSource());

		default: // error handling
			if (customedParamLogs.containsKey(type))
				return new LogAnalyzerCustom(type, log.getId(), log.getContent(), log.getSource(),
						customedParamLogs.get(type));
			else
				throw new IllegalArgumentException("Le type : " + type + " n'a pas encore Ã©tÃ© dÃ©fini");
		}

		// Eventuellement cette version est plus automatique mais aussi plus dangereuse
		// au cas ou l'argument est mauvais ou du meme nom qu'une classe dï¿½jï¿½
		// utilisï¿½e
		// try {
		//// returns the Class object for the class with the specified name
		// Class cls = Class.forName(type);
		// Object res = cls.newInstance();
		// return res;
		// } catch(ClassNotFoundException ex) {
		// System.out.println(ex.toString());
		// }
	}

	public Collection<LogAnalyzer> buildLogAnalyzer(Collection<LightLog> logs, String type) {
		ArrayList<LogAnalyzer> res = new ArrayList<LogAnalyzer>();
		for (LightLog log : logs)
			res.add(buildLog(log, type));
		return res;
	}

	public Collection<LogAnalyzer> buildLogAnalyzer(Collection<LightLog> logs) {
		ArrayList<LogAnalyzer> res = new ArrayList<LogAnalyzer>();
		for (LightLog log : logs)
			res.add(buildLog(log, paramAgentStock.get(log.getSource()).getAssociatedAnalyzer()));
		return res;
	}

	public Collection<String> getAvailableDataByType(String type) {
		// Renvoie les availabledata pour le log de type "type"
		LightLog tmplightlog = new LightLog();
		LogAnalyzer tmp = buildLog(tmplightlog, type);
		return tmp.getAvailableDatas();
	}

	public Map<String, ParamLogAnalyzerCustom> getCustomedParamLogs() {
		return customedParamLogs;
	}

	public void setCustomedParamLogs(Map<String, ParamLogAnalyzerCustom> customedParamLogs) {
		this.customedParamLogs = customedParamLogs;
	}

	// public LogAnalyzerCustom buildCstmLog(LightLog log,
	// HashMap<String,DataCatcher> param) {
	// //TODO :pass
	//// return new LogAnalyzerCustom();
	// }
}
