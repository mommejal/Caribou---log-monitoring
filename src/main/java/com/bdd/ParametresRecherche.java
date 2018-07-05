package com.bdd;

import java.util.List;

public class ParametresRecherche {
	private List<String> selectedfilters;
	private List<String> selectedseveritylvls;
	private List<String> selectedregexps;
	private long datebeginning;
	private long dateend;
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
	public List<String> getSelectedregexps() {
		return selectedregexps;
	}
	public void setSelectedregexps(List<String> selectedregexps) {
		this.selectedregexps = selectedregexps;
	}
	public long getDatebeginning() {
		return datebeginning;
	}
	public void setDatebeginning(long datebeginning) {
		this.datebeginning = datebeginning;
	}
	public long getDateend() {
		return dateend;
	}
	public void setDateend(long dateend) {
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
