package com.agent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;

import com.agent.exchanges.GetREST;

public class ParamAgent {

	protected String regexDebutLog;
	protected String regexFinLog;
	protected String regexFiltre;
	protected int tpsVieMinStock;
	protected int tpsAttenteLog;
	protected int tpsCheckLog;

	public ParamAgent(String regexDebutLog, String regexFinLog, String regexFiltre, int tpsVieMinStock,
			int tpsAttenteLog, int tpsCheckLog) {
		super();
		this.regexDebutLog = regexDebutLog;
		this.regexFinLog = regexFinLog;
		this.regexFiltre = regexFiltre;
		this.tpsVieMinStock = tpsVieMinStock;
		this.tpsAttenteLog = tpsAttenteLog;
		this.tpsCheckLog = tpsCheckLog;
	}

	public int majParam(String source) {
		Queue<String> nvParam;
		try {
			nvParam = GetREST.getAll(new URL(source + "/getParamAgent"));
			
			if (!nvParam.isEmpty()) {
				regexDebutLog = nvParam.poll();
				regexFinLog = nvParam.poll();
				regexFiltre = nvParam.poll();
				tpsVieMinStock = Integer.parseInt(nvParam.poll());
				tpsAttenteLog = Integer.parseInt(nvParam.poll());
				tpsCheckLog = Integer.parseInt(nvParam.poll());
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

	public int getTpsCheckLog() {
		return tpsCheckLog;
	}

	public void setTpsCheckLog(int tpsCheckLog) {
		this.tpsCheckLog = tpsCheckLog;
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

	public String getRegexFiltre() {
		return regexFiltre;
	}

	public void setRegexFiltre(String regexFiltre) {
		this.regexFiltre = regexFiltre;
	}

	public int getTpsVieMinStock() {
		return tpsVieMinStock;
	}

	public void setTpsVieMinStock(int tpsVieMinStock) {
		this.tpsVieMinStock = tpsVieMinStock;
	}

	public int getTpsAttenteLog() {
		return tpsAttenteLog;
	}

	public void setTpsAttenteLog(int tpsAttenteLog) {
		this.tpsAttenteLog = tpsAttenteLog;
	}
}
