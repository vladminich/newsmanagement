package com.epam.newscommon.service.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.Author;
import com.epam.newscommon.entity.Comment;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.NewsValueObject;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.entity.Tag;
import com.epam.newscommon.service.AuthorService;
import com.epam.newscommon.service.CommentService;
import com.epam.newscommon.service.NewsService;
import com.epam.newscommon.service.NewsValueObjectService;
import com.epam.newscommon.service.TagService;

/**
 * CLass NewsValueObjectServiceImpl. Implementation of interface
 * NewsValueObjectService
 * 
 * @author Uladzislau_Minich
 *
 */
public class NewsValueObjectServiceImpl implements NewsValueObjectService {
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private TagService tagService;
	@Autowired
	private AuthorService authorService;

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#saveNews(News, Author, List)}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
	public NewsValueObject saveNews(News news, Author author, List<Tag> tags) throws ServiceException {
		NewsValueObject newsObj = new NewsValueObject();

		Long newsId = newsService.save(news);
		Long authorId = authorService.save(author);
		newsService.saveNewsAuthors(newsId, authorId);
		if (!(tags == null || tags.isEmpty())) {
			for (Tag tag : tags) {

				Long tagId = tagService.save(tag);
				newsService.addNewsTag(tagId, newsId);
				
			}

		}
		newsObj.setAuthor(author);
		newsObj.setNews(news);
		newsObj.setTags(tags);
		
		return newsObj;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#deleteNews(int)}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteNews(Long idNews) throws ServiceException {// T
		newsService.deleteById(idNews);
		newsService.deleteNewsTag(idNews);
		newsService.deleteNewsAuthor(idNews);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findNews(int)}
	 */
	@Override
	public NewsValueObject findNews(Long newsId) throws ServiceException {
		NewsValueObject newsObj = new NewsValueObject();
		newsObj.setNews(newsService.findById(newsId));
		newsObj.setAuthor(authorService.findAuthorByNews(newsId));
		newsObj.setTags(tagService.findAllNewsTag(newsId));
		newsObj.setComments(commentService.showAllNewsComment(newsId));
		return newsObj;

	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#updateNews(NewsValueObject)}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public NewsValueObject updateNews(NewsValueObject obj) throws ServiceException {// T
		newsService.update(obj.getNews());
		authorService.update(obj.getAuthor());
		for (Tag tag : obj.getTags()) {
			tagService.update(tag);
		}
		for (Comment com : obj.getComments()) {
			commentService.update(com);
		}
		return obj;

	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findAllNews()}
	 */
	@Override
	public List<NewsValueObject> findAllNews(int indexPage, int countNews) throws ServiceException {
		List<NewsValueObject> newsVOAll = new ArrayList<>();
		List<News> newsAll = newsService.findAllNews(indexPage,countNews);
		for (News news : newsAll) {
			NewsValueObject newsVO = new NewsValueObject();
			newsVO.setNews(news);
			newsVO.setAuthor(authorService.findAuthorByNews(news.getNewsId()));
			newsVO.setTags(tagService.findAllNewsTag(news.getNewsId()));
			newsVO.setComments(commentService.showAllNewsComment(news.getNewsId()));
			newsVOAll.add(newsVO);
		}
		return newsVOAll;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findBySearchCriteria(SearchCriteriaObject)}
	 */
	@Override
	public List<NewsValueObject> findBySearchCriteria(SearchCriteriaObject sc,int indexPageNews,int countNews) throws ServiceException {
		List<NewsValueObject> newsVOAll = new ArrayList<>();
		if (sc != null) {
			List<News> newsAll = newsService.findNewsBySearchCriteria(sc,indexPageNews,countNews);
			for (News news : newsAll) {
				NewsValueObject newsVO = new NewsValueObject();
				newsVO.setNews(news);
				newsVO.setAuthor(authorService.findAuthorByNews(news.getNewsId()));
				newsVO.setTags(tagService.findAllNewsTag(news.getNewsId()));
				newsVO.setComments(commentService.showAllNewsComment(news.getNewsId()));
				newsVOAll.add(newsVO);
			}
		}
		return newsVOAll;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#getCurrentAuthors()}
	 */
	@Override
	public List<Author> getCurrentAuthors() throws ServiceException {
		List<Author> authors = authorService.findNotExpiredAuthors();
		return authors;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#addComment(Comment)}
	 */
	@Override
	public void addComment(Comment comment) throws ServiceException {
		commentService.save(comment);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#deleteComment(int)}
	 */
	@Override
	public void deleteComment(Long commentId) throws ServiceException {
		commentService.deleteById(commentId);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#addTag(Tag, int)}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
	public void addTag(Tag tag, Long newsId) throws ServiceException {// T
		tagService.save(tag);
		newsService.addNewsTag(tag.getTagId(), newsId);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#expiredAuthor(Author)}
	 */
	@Override
	public void expiredAuthor(Author author) throws ServiceException {
		authorService.expiredAuthor(author.getAuthorId());
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#saveAuthor(Author)}
	 */
	@Override
	public void saveAuthor(Author author) throws ServiceException {
		authorService.save(author);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#deleteAuthor(int)}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
	public void deleteAuthor(Long authorId) throws ServiceException {
		List<News> list = newsService.findAllAuthorNews(authorId);
		for (News news : list) {
			newsService.deleteNewsAuthor(news.getNewsId());
			newsService.deleteById(news.getNewsId());
			newsService.deleteNewsTag(news.getNewsId());
		}
		authorService.deleteById(authorId);
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findAuthorByName(String)}
	 */
	@Override
	public Author findAuthorByName(String name) throws ServiceException {
		Author author = authorService.findAuthorByName(name);
		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findAllAuthorNews(int)}
	 */
	@Override
	public List<NewsValueObject> findAllAuthorNews(Long authorId) throws ServiceException {
		List<NewsValueObject> newsVOAll = new ArrayList<>();
		List<News> newsAll = newsService.findAllAuthorNews(authorId);
		for (News news : newsAll) {
			NewsValueObject newsVO = new NewsValueObject();
			newsVO.setNews(news);
			newsVO.setAuthor(authorService.findAuthorByNews(news.getNewsId()));
			newsVO.setTags(tagService.findAllNewsTag(news.getNewsId()));
			newsVO.setComments(commentService.showAllNewsComment(news.getNewsId()));
			newsVOAll.add(newsVO);
		}
		return newsVOAll;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#findAllTags()}
	 */
	@Override
	public List<Tag> findAllTags() throws ServiceException {
		List<Tag> list = tagService.findAllTag();
		return list;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#deleteTag(int, int)}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = ServiceException.class)
	public void deleteTag(Long tagId, Long newsId) throws ServiceException {
		newsService.deleteNewsTag(newsId);
		tagService.deleteById(tagId);
	}
	/**
	 * @see {@link com.epam.newscommon.service.NewsValueObjectService#countNews()}
	 */
	public int countNews() throws ServiceException{
		int amount  = newsService.countNews();
		return amount;
		
	}
	@Override
	public List<Author> findAllAuthors() throws ServiceException {
		List<Author> authors = authorService.findAllAuthors();
		return authors;
	}
	@Override
	public Long findPreviousNews(News currentNews) throws ServiceException {
		Long previousNewsId=0L;
		previousNewsId = newsService.findNextNews(currentNews);
		return previousNewsId;
	}

	@Override
	public Long findNextNews(News currentNews) throws ServiceException {
		Long nextNewsId=0L;
		nextNewsId = newsService.findNextNews(currentNews);
		return nextNewsId;
	}
	/**
	 * Set a newsService field
	 * 
	 * @param newsService
	 *            the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/**
	 * Set a commentService field
	 * 
	 * @param commentService
	 *            the commentService to set
	 */
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * Set a tagService field
	 * 
	 * @param tagService
	 *            the tagService to set
	 */
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * Set a authorService field
	 * 
	 * @param authorService
	 *            the authorService to set
	 */
	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	

}
