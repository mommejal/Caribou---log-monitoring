package com.agent.paramagent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;

import com.agent.exchanges.GetREST;

public class ParamAgentToWork extends ParamAgent {

	public ParamAgentToWork(String id, String appName, String logPath, String outputPath, String regexDebutLog,
			String regexFinLog, int tpsVieMinStock, int nbLignesDeSuite) {
		super(id, appName, logPath, outputPath, regexDebutLog, regexFinLog, tpsVieMinStock, nbLignesDeSuite);
	}

	public ParamAgentToWork(String id, String appName, String logPath, String outputPath) {
		super(id, appName, logPath, outputPath);
	}

	
	
	/*
	 * 			appName + System.lineSeparator()
				+ logPath + System.lineSeparator()
				+ outputPath + System.lineSeparator()
				+ regexDebutLog + System.lineSeparator()
				+ regexFinLog + System.lineSeparator()
				+ tpsVieMinStock + System.lineSeparator()
				+ nbLignesDeSuite;
	 */
	public String birthAnnouncement() {
		return id + System.lineSeparator()
			+ appName + System.lineSeparator()
			+ logPath + System.lineSeparator()
			+ outputPath;
	}
	
	public int majStandard() {
		Queue<String> nvParam;
		try {
			nvParam = GetREST.getAll(new URL(outputPath + "/getParamAgent"));		
			if (!nvParam.isEmpty()) {
				appName = nvParam.poll();
				logPath = nvParam.poll();
				outputPath = nvParam.poll();
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

}
