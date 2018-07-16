package com.bdd;

import java.util.Collection;

import org.springframework.data.annotation.Id;

public class LightLog {
	
	@Id
	protected String id;
	protected Collection<String> Content;
	protected String source;
	
	@Override
	public String toString() {
		return "LightLog [id=" + id + ", Content=" + Content + ", source=" + source + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Content == null) ? 0 : Content.hashCode());
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
		if (Content == null) {
			if (other.Content != null)
				return false;
		} else if (!Content.equals(other.Content))
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
}
	