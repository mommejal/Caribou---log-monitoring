package com.log.loganalyzer.datacatcher;

import java.util.Date;

public abstract class DataCatcher {
	
	protected String name;
	protected String typeReturned;
	
	public DataCatcher(String name, String typeReturned) {
		super();
		this.name = name;
		this.typeReturned = typeReturned;
	}

	protected abstract String catchDataString(String input);

	@SuppressWarnings("deprecation")
	public Object catchData(String input) {
		switch (typeReturned) {
		case "Integer":
			return Integer.parseInt(catchDataString(input));
		case "Date":
			return Date.parse(catchDataString(input));
		case "Float":
			return Float.parseFloat(catchDataString(input));
		case "String":
			return catchDataString(input);
		//etc
		default:
			throw new IllegalArgumentException("Le type \"" + typeReturned + "\"demandé au DataCatcher "+ name + " n'est pas disponible");
		}
	}
	
	public String getTypeReturned() {
		return typeReturned;
	}

	public void setTypeReturned(String typeReturned) {
		this.typeReturned = typeReturned;
	}

	public String getName() {
		return name;
	}
}