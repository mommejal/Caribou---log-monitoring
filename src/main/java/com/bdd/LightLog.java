package com.bdd;

import java.util.Collection;

import org.springframework.data.annotation.Id;

public class LightLog {
	
	@Id
	protected String id;
	protected Collection<String> content;
	protected String source;
	
	@Override
	public String toString() {
		return "LightLog [id=" + id + ", content=" + content + ", source=" + source + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return id;
	}
	
	public Collection<String> getContent() {
		return content;
	}
}
	