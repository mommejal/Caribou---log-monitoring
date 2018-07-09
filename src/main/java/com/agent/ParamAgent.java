package com.agent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;

import com.agent.exchanges.GetREST;

public class ParamAgent {

	protected String id;
	protected String logPath;
	protected String outputPath;
		
	protected String regexDebutLog;
	protected String regexFinLog;
	protected int tpsVieMinStock;
	protected int nbLignesDeSuite;
	

	public ParamAgent(String id, String logPath, String outputPath, String regexDebutLog, String regexFinLog,
			int tpsVieMinStock, int nbLignesDeSuite) {
		super();
		this.id = id;
		this.logPath = logPath;
		this.outputPath = outputPath;
		this.regexDebutLog = regexDebutLog;
		this.regexFinLog = regexFinLog;
		this.tpsVieMinStock = tpsVieMinStock;
		this.nbLignesDeSuite = nbLignesDeSuite;
	}
	
	public ParamAgent(String id, String logPath, String outputPath) { // TODO voir sans logPath
		this(id, logPath, outputPath, "^\\d?\\d:\\d\\d:\\d\\d", "", 3000, 500);		
	}
	
	public ParamAgent(String newAgentId) {
		this(newAgentId, "N/A", "N/A");
	}

	public String toSendStandard() {
		return regexDebutLog + System.lineSeparator() + regexFinLog + System.lineSeparator() + tpsVieMinStock
				+ System.lineSeparator() +nbLignesDeSuite;
	}
	
	public int majStandard() {
		Queue<String> nvParam;
		try {
			nvParam = GetREST.getAll(new URL(outputPath + "/getParamAgent"));		
			if (!nvParam.isEmpty()) {
				regexDebutLog = nvParam.poll();
				regexFinLog = nvParam.poll();
				tpsVieMinStock = Integer.parseInt(nvParam.poll());
				nbLignesDeSuite = Integer.parseInt(nvParam.poll());
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
			return 2;

		} catch (IOException e) {

			e.printStackTrace();
			return 1;

		} catch (NumberFormatException e) {

			e.printStackTrace();
			return 3;
		}
		
		System.out.println("ParamMaj : " + toString());

		return 0;
	}
	
	@Override
	public String toString() {
		return "ParamAgent [regexDebutLog=" + regexDebutLog + ", regexFinLog=" + regexFinLog + ", tpsVieMinStock="
				+ tpsVieMinStock + ", nbLignesDeSuite=" + nbLignesDeSuite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nbLignesDeSuite;
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
		if (nbLignesDeSuite != other.nbLignesDeSuite)
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
	
	public String getId() {
		return id;
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

	public int getNbLignesDeSuite() {
		return nbLignesDeSuite;
	}

	public void setNbLignesDeSuite(int nbLignesDeSuite) {
		this.nbLignesDeSuite = nbLignesDeSuite;
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
}
