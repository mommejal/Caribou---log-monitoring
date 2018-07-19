package com.log.loganalyzer;

import java.util.Collection;

import com.log.LightLog;

public abstract class LogAnalyzer extends LightLog {
	
	protected String type;
	
	public LogAnalyzer(String type, String id, Collection<String> content, String source) {
		super(id, content, source);
		this.type = type;
	}

	public abstract Collection<String> getAvailableDatas();

	public abstract Object getData(String data);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogAnalyzer other = (LogAnalyzer) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

//	public int isBefore(LogAnalyzer log, String data) {
//		return (log.getData(data).compareTo(this.getData(data)));
//	}

}
