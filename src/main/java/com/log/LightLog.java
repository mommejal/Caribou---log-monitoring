package com.log;

import java.util.Collection;

import org.springframework.data.annotation.Id;

public class LightLog {
	
	@Id
	protected String _id;
	protected Collection<String> content;
	protected String source;

	public LightLog(String id, Collection<String> content, String source) {
		super();
		this._id = id;
		this.content = content;
		this.source = source;
	}

	@Override
	public String toString() {
		return "LightLog [id=" + _id + ", content=" + content + ", source=" + source + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
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
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
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
		return _id;
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
	