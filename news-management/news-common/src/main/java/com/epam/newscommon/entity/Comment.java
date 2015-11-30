package com.epam.newscommon.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class Comment. Classes contain data from database's table "COMMENTS"
 * 
 * @author Uladzislau_Minich
 *
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long newsId;
	private Long commentId;
	private String text;
	private Timestamp creationDate;

	public Comment() {
		super();
	}

	/**
	 * Gets the comment id.
	 *
	 * @return the comment id
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * Sets the comment id.
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * Gets the comment text.
	 *
	 * @return the comment text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the comment text.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the comment creation date.
	 *
	 * @return the comment creation date
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the comment creation date.
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + commentId);
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = (int) (prime * result + newsId);
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Comment other = (Comment) obj;
		if (commentId != other.commentId)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (newsId != other.newsId)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
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
		return "Comment [newsId=" + newsId + ", commentId=" + commentId + ", text=" + text + ", creationDate="
				+ creationDate + "]";
	}

}
