package com.epam.newscommon.entity;

import java.io.Serializable;
/**
 * Class Tag. Classes contain data from database's table "TAG"
 * 
 * @author Uladzislau_Minich
 *
 */
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long tagId;
	private String tagName;

	public Tag() {
		super();
	}
	/**
	 * Gets the tag id.
	 *
	 * @return the tag id
	 */
	public Long getTagId() {
		return tagId;
	}
	/**
	 * Sets the tag id.
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	/**
	 * Gets the tag name.
	 *
	 * @return the tag name
	 */
	public String getTagName() {
		return tagName;
	}
	/**
	 * Sets the tag name.
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + tagId);
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		return result;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagId != other.tagId)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Tag [tagId=%d, tagName=%s]",tagId,tagName);
	}

}
