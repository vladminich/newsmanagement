package com.epam.newscommon.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Class NewsValueObject. Classes uses for representation simple news which
 * contain news, author, tags, comment.
 * 
 * @author Uladzislau_Minich
 *
 */
public class NewsValueObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private News news;
	private List<Comment> comments;
	private List<Tag> tags;
	private Author author;

	public NewsValueObject() {
		super();
	}

	/**
	 * Gets the news.
	 *
	 * @return the news
	 */
	public News getNews() {
		return news;
	}

	/**
	 * Sets the news.
	 */
	public void setNews(News news) {
		this.news = news;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((news == null) ? 0 : news.hashCode());
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
		NewsValueObject other = (NewsValueObject) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (news == null) {
			if (other.news != null)
				return false;
		} else if (!news.equals(other.news))
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
		StringBuilder str = new StringBuilder("\n News: ");
		str.append(news.getTitle());
		str.append("\n ");
		str.append(news.getShortText());
		str.append("\n ");
		str.append(news.getFullText());
		str.append("\n CtreationDate - ");
		str.append(news.getCreationTime());
		str.append("\n ModificationDate - ");
		str.append(news.getModificationDate());
		str.append("\n Author: ");
		str.append(author.getAuthorName());
		if (!(tags == null || tags.isEmpty())) {
			str.append("\n Tags: ");
			for (Tag tag : tags) {
				str.append("#" + tag.getTagName());
			}
		}
		if (!(comments == null || comments.isEmpty())) {
			str.append("\n Comments:\n ");
			for (Comment com : comments) {
				str.append(com.getText() + " " + com.getCreationDate() + "\n ");
			}
		}
		return new String(str);
	}

}
