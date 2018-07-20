package com.agent.paramagent;

public class ParamAgentToManage extends ParamAgent {

	private String associatedAnalyzer = "N/A";
	
	public ParamAgentToManage(String id, String appName, String logPath, String outputPath, String regexDebutLog,
			String regexFinLog, int tpsVieMinStock, int nbLignesDeSuite, String associatedAnalyzer) {
		super(id, appName, logPath, outputPath, regexDebutLog, regexFinLog, tpsVieMinStock, nbLignesDeSuite);
		this.associatedAnalyzer = associatedAnalyzer;
	}
	
	public ParamAgentToManage(String id, String appName, String logPath, String outputPath) {
		super(id, appName, logPath, outputPath);
	}

	public String toSendStandard() {
		return appName + System.lineSeparator()
				+ logPath + System.lineSeparator()
				+ outputPath + System.lineSeparator()
				+ regexDebutLog + System.lineSeparator()
				+ regexFinLog + System.lineSeparator()
				+ tpsVieMinStock + System.lineSeparator()
				+ nbLignesDeSuite;
	}

	public String getAssociatedAnalyzer() {
		return associatedAnalyzer;
	}

	public void setAssociatedAnalyzer(String associatedAnalyzer) {
		this.associatedAnalyzer = associatedAnalyzer;
	}
}
