package com.log.loganalyzer.datacatcher;

import java.util.Date;

public abstract class DataCatcher {

	protected String typeReturned;

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
}