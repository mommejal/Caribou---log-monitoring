package com.agent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;

import com.agent.exchanges.GetREST;

public class ParamAgent {

	protected String regexDebutLog;
	protected String regexFinLog;
	protected int tpsVieMinStock;
	protected int nbLignesDeSuite;


	public ParamAgent(String regexDebutLog, String regexFinLog, int tpsVieMinStock,
				int nbLignesDeSuite) {
		super();
		this.regexDebutLog = regexDebutLog;
		this.regexFinLog = regexFinLog;
		this.tpsVieMinStock = tpsVieMinStock;
		this.nbLignesDeSuite = nbLignesDeSuite;
	}

	public int majParam(String source) {
		Queue<String> nvParam;
		try {
			nvParam = GetREST.getAll(new URL(source + "/getParamAgent"));
			
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

		return 0;
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
