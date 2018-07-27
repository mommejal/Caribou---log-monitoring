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

	public Object catchData(String input) {
		switch (typeReturned) {
		case "Integer":
			return (Integer) catchData(input);
		case "Date":
			return (Date) catchData(input);
		case "Float":
			return (Float) catchData(input);
		//etc
		default:
			throw new IllegalArgumentException("Le type demandé n'est pas disponible");
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