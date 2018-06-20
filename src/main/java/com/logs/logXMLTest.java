package com.logs;

import java.util.Date;

public class logXMLTest extends GeneLog {

	public logXMLTest(String data) {
		super(data);
	}
	
	protected String postMotCle(final String cible)  {
		String[] bases = data.split("LOG]!><");
		String[] infos = bases[1].substring(0, bases[1].length() - 2).split(" ");
		String res = "";
		
		for (String info:infos)
			if (info.indexOf(cible)!=-1)
				res = info.split("=")[1];
		return res.substring(1, res.length() -2);
	}
	
	
	
	@Override
	public int getID() {
		return Integer.parseInt(postMotCle("ID"));
	}

	@SuppressWarnings("deprecation") // Il a pas l'air d'aimer non plus quand on initialise la date manuellement
	@Override
	public Date getDate() {
		String[] date = postMotCle("date").split("-");
		String[] time = postMotCle("time").split(":");
		return new Date(Integer.parseInt(date[2]), 
				Integer.parseInt(date[0]), 
				Integer.parseInt(date[1]), 
				Integer.parseInt(time[0]), 
				Integer.parseInt(time[1]), 
				Integer.parseInt(time[2].split(".")[0]) ); 
	}


	@Override
	public int getFacility() {
		return Integer.parseInt(postMotCle("Facility"));
	}

	@Override
	public int getSeverityLvl() {
		return Integer.parseInt(postMotCle("type"));
	}

	@Override
	public String getMSG() {
		return data.split("]LOG]!>")[0].substring(7);
	}

	@Override
	public String getHostname() {
		return postMotCle("component");
	}
}
