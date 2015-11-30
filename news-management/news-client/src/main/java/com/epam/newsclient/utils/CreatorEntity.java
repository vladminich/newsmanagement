package com.epam.newsclient.utils;

import java.util.ArrayList;
import java.util.List;

import com.epam.newscommon.entity.Author;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.entity.Tag;
import com.epam.newscommon.exception.ServiceException;

public class CreatorEntity {

	public static SearchCriteriaObject createSearchCriteria(String authorId, String[] tags) throws ServiceException {
		SearchCriteriaObject sc = new SearchCriteriaObject();
		List<Tag> tagsSC = new ArrayList<>();
		try {
			if (authorId != null) {
				Long authotID = Long.parseLong(authorId);
				Author author = new Author();
				author.setAuthorId(authotID);
				sc.setAuthor(author);
			}
			if (tags!=null) {
				for (String id : tags) {
					Long tagID = Long.parseLong(id);
					Tag tag = new Tag();
					tag.setTagId(tagID);
					tagsSC.add(tag);
				}
				sc.setTags(tagsSC);
			}

		} catch (NumberFormatException e) {
			throw new ServiceException(e);
		}
		return sc;

	}
}
