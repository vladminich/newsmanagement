package com.epam.newscommon.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Class Author. Classes contain data from database's table "NEWS"
 * 
 * @author Uladzislau_Minich
 *
 */
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long authorId;
	private String authorName;
	private Timestamp expired;

	public Author() {
		super();
	}

	/**
	 * Gets the author id.
	 *
	 * @return the author id
	 */
	public Long getAuthorId() {
		return authorId;
	}

	/**
	 * Sets the author id.
	 */
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	/**
	 * Gets the author name.
	 *
	 * @return the author name
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Sets the author name.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Gets the author expired.
	 *
	 * @return the author expired
	 */
	public Date getExpired() {
		return expired;
	}

	/**
	 * Sets the author expired.
	 */
	public void setExpired(Timestamp expired) {
		this.expired = expired;
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
		result = (int) (prime * result + authorId);
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((expired == null) ? 0 : expired.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (expired == null) {
			if (other.expired != null)
				return false;
		} else if (!expired.equals(other.expired))
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
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", expired=" + expired + "]";
	}
}
