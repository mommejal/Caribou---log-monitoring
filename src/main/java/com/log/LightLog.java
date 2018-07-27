package com.log;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LightLog {
	// Une classe la plus légére possible pour prendre le moins de place mémoire possible
	
	
	protected String idlog;
	protected Collection<String> content;
	protected String source;

	public LightLog(String id, Collection<String> content, String source) {
		super();
		this.idlog = id;
		this.content = content;
		this.source = source;
	}
	public LightLog() {
		// Sert pour l'instant uniquement à LogAnalyzerBuilder pour construire getAvailableDataByType
		super();
	}
	
	public LightLog(String id, String content, String source) {
		super();
		this.idlog = id;
		ArrayList<String> ar = new ArrayList<String>();
		ar.add(content);
		this.content = ar;
		this.source = source;
	}

	@Override
	public String toString() {
		return "LightLog [id=" + idlog + ", content=" + content + ", source=" + source + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((idlog == null) ? 0 : idlog.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LightLog other = (LightLog) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (idlog == null) {
			if (other.idlog != null)
				return false;
		} else if (!idlog.equals(other.idlog))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	
	public String getSource() {
		return source;
	}
	
	public String getId() {
		return idlog;
	}
	
	public Collection<String> getContent() {
		return content;
	}

	public void setContent(Collection<String> content) {
		this.content = content;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
	