package com.epam.newscommon.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Class News. Classes contain data from database's table "NEWS"
 * 
 * @author Uladzislau_Minich
 *
 */
public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long newsId;
	private String title;
	private String shortText;
	private String fullText;
	private Timestamp creationTime;
	private java.util.Date modificationDate;

	public News() {
		super();
	}

	/**
	 * Gets the news id.
	 *
	 * @return the news id
	 */
	public Long getNewsId() {
		return newsId;
	}

	/**
	 * Sets the news id.
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	/**
	 * Gets the news title.
	 *
	 * @return the news title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the news title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the news short text.
	 *
	 * @return the news short text
	 */
	public String getShortText() {
		return shortText;
	}
	/**
	 * Sets the news short text.
	 */
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	/**
	 * Gets the news full text.
	 *
	 * @return the news full text
	 */
	public String getFullText() {
		return fullText;
	}
	/**
	 * Sets the news full text.
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * Gets the news creation date.
	 *
	 * @return the news creation date
	 */
	public Timestamp getCreationTime() {
		return creationTime;
	}
	/**
	 * Sets the news creation date.
	 */
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Gets the news modification date.
	 *
	 * @return the news modification date
	 */
	public java.util.Date getModificationDate() {
		return modificationDate;
	}
	/**
	 * Sets the news modification date.
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
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
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = (int) (prime * result + newsId);
		result = prime * result + ((shortText == null) ? 0 : shortText.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (fullText == null) {
			if (other.fullText != null)
				return false;
		} else if (!fullText.equals(other.fullText))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (newsId != other.newsId)
			return false;
		if (shortText == null) {
			if (other.shortText != null)
				return false;
		} else if (!shortText.equals(other.shortText))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "News [newsId=" + newsId + ", title=" + title + ", shortText=" + shortText + ", fullText=" + fullText
				+ ", creationTime=" + creationTime + ", modificationDate=" + modificationDate + "]";
	}

}
