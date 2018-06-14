package com.logs;
import java.lang.String;
import java.util.Date;

public abstract class GeneLog {

	protected String data;
	
	GeneLog(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public abstract int getID();
	
	public abstract Date getDate();
	
	public abstract String getHostname();
	
	public abstract int getFacility();
	
	public abstract int getSeverityLvl();
	
	public abstract String getMSG();
	
	public boolean equals(GeneLog log) {
		return (log != null && this.hashCode() == log.hashCode());
	}
	
	public int hashCode() {
		return data.hashCode();
	}
	
	public String toString() {
		return "" + getID() + getDate() + getHostname() + getFacility() + getSeverityLvl() + getMSG();
	}
}
