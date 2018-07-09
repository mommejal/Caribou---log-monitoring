package com.bdd;

import java.util.List;

public class ParametresRecherche {
	private List<String> selectedfilters;
	private List<String> selectedseveritylvls;
	private String selectedregexp;
	private String datebeginning;
	private String dateend;
	private String detectiondate;
	private String detectionidlog;
	private String detectionseveritylvl;
	private String agent;
	

	public List<String> getSelectedfilters() {
		return selectedfilters;
	}
	public void setSelectedfilters(List<String> selectedfilters) {
		this.selectedfilters = selectedfilters;
	}
	public List<String> getSelectedseveritylvls() {
		return selectedseveritylvls;
	}
	public void setSelectedseveritylvls(List<String> selectedseveritylvls) {
		this.selectedseveritylvls = selectedseveritylvls;
	}
	public String getSelectedregexp() {
		return selectedregexp;
	}
	public void setSelectedregexp(String selectedregexp) {
		this.selectedregexp = selectedregexp;
	}
	public String getDatebeginning() {
		return datebeginning;
	}
	public void setDatebeginning(String datebeginning) {
		this.datebeginning = datebeginning;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	
	public String getDetectiondate() {
		return detectiondate;
	}
	public void setDetectiondate(String detectiondate) {
		this.detectiondate = detectiondate;
	}
	public String getDetectionidlog() {
		return detectionidlog;
	}
	public void setDetectionidlog(String detectionidlog) {
		this.detectionidlog = detectionidlog;
	}
	public String getDetectionseveritylvl() {
		return detectionseveritylvl;
	}
	public void setDetectionseveritylvl(String detectionseveritylvl) {
		this.detectionseveritylvl = detectionseveritylvl;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public void addSelectedfilters(String filter) {
		selectedfilters.add(filter);
	}
	
	
	
}
