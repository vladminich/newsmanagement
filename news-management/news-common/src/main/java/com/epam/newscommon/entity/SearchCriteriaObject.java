package com.epam.newscommon.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Class SearchCriteriaObject. Class uses for searching news
 * 
 * @author Uladzislau_Minich
 *
 */
public class SearchCriteriaObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Author author;
	private List<Tag> tags;

	public SearchCriteriaObject() {
		super();
	}

	public SearchCriteriaObject(Author author, List<Tag> tags) {
		super();
		this.author = author;
		this.tags = tags;
	}
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}
	/**
	 * Sets the author. 
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}
	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
	/**
	 * Sets the tags. 
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		SearchCriteriaObject other = (SearchCriteriaObject) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
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
		return String.format("SearchCriteriaObject [author=%s, tags=%s]", author, tags);
	}

}
