package com.agent.paramagent;

public abstract class ParamAgent {

	protected String id;
	protected String appName = "N/A";
	protected String logPath = "N/A";
	protected String outputPath = "N/A";
	
	protected String regexDebutLog = "^\\d?:\\d\\d:\\d\\d";
	protected String regexFinLog = "";
	protected int tpsVieMinStock = 3000;
	protected int nbLignesDeSuite = 500;

	
	public ParamAgent(String id, String appName, String logPath, String outputPath) {
		super();
		this.id = id;
		this.appName = appName;
		this.logPath = logPath;
		this.outputPath = outputPath;
	}
	
	public ParamAgent(String id, String appName, String logPath, String outputPath, String regexDebutLog,
			String regexFinLog, int tpsVieMinStock, int nbLignesDeSuite) {
		this(id, appName, logPath, outputPath);
		this.regexDebutLog = regexDebutLog;
		this.regexFinLog = regexFinLog;
		this.tpsVieMinStock = tpsVieMinStock;
		this.nbLignesDeSuite = nbLignesDeSuite;
	}
	
	
	public String getId() {
		return id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getRegexDebutLog() {
		return regexDebutLog;
	}

	public void setRegexDebutLog(String regexDebutLog) {
		this.regexDebutLog = regexDebutLog;
	}

	public String getRegexFinLog() {
		return regexFinLog;
	}

	public void setRegexFinLog(String regexFinLog) {
		this.regexFinLog = regexFinLog;
	}

	public int getTpsVieMinStock() {
		return tpsVieMinStock;
	}

	public void setTpsVieMinStock(int tpsVieMinStock) {
		this.tpsVieMinStock = tpsVieMinStock;
	}

	public int getNbLignesDeSuite() {
		return nbLignesDeSuite;
	}

	public void setNbLignesDeSuite(int nbLignesDeSuite) {
		this.nbLignesDeSuite = nbLignesDeSuite;
	}

	
	@Override
	public String toString() {
		return "ParamAgent [id=" + id + ", appName=" + appName + ", logPath=" + logPath + ", outputPath=" + outputPath
				+ ", regexDebutLog=" + regexDebutLog + ", regexFinLog=" + regexFinLog + ", tpsVieMinStock="
				+ tpsVieMinStock + ", nbLignesDeSuite=" + nbLignesDeSuite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logPath == null) ? 0 : logPath.hashCode());
		result = prime * result + nbLignesDeSuite;
		result = prime * result + ((outputPath == null) ? 0 : outputPath.hashCode());
		result = prime * result + ((regexDebutLog == null) ? 0 : regexDebutLog.hashCode());
		result = prime * result + ((regexFinLog == null) ? 0 : regexFinLog.hashCode());
		result = prime * result + tpsVieMinStock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParamAgent other = (ParamAgent) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logPath == null) {
			if (other.logPath != null)
				return false;
		} else if (!logPath.equals(other.logPath))
			return false;
		if (nbLignesDeSuite != other.nbLignesDeSuite)
			return false;
		if (outputPath == null) {
			if (other.outputPath != null)
				return false;
		} else if (!outputPath.equals(other.outputPath))
			return false;
		if (regexDebutLog == null) {
			if (other.regexDebutLog != null)
				return false;
		} else if (!regexDebutLog.equals(other.regexDebutLog))
			return false;
		if (regexFinLog == null) {
			if (other.regexFinLog != null)
				return false;
		} else if (!regexFinLog.equals(other.regexFinLog))
			return false;
		if (tpsVieMinStock != other.tpsVieMinStock)
			return false;
		return true;
	}

	
	
	
}
