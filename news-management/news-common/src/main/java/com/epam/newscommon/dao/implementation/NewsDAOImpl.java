package com.epam.newscommon.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newscommon.constant.DBColumnName;
import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.dao.NewsDAO;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.entity.Tag;

/**
 * Class NewsDAOImpl. Implementation of NewsDAO
 * 
 * @author Uladzislau_Minich
 *
 */
public class NewsDAOImpl implements NewsDAO {

	private static BasicDataSource dataSource;
	public static final String SQL_CREATE_NEWS = "Insert into news (news_id,title,short_text,full_text,creation_date,modification_date) values(?,?,?,?,?,?)";
	public static final String SQL_CREATE_NEWS_AUTHORS = "INSERT INTO NEWS_AUTHOR(news_id,author_id) VALUES(?,?)";
	public static final String SQL_CREATE_NEWS_TAG = "Insert into news_tag values(?,?)";
	public static final String SQL_FIND_BY_ID = "Select * from news where news.news_id=?";
	public static final String SQL_FIND_BY_TITLE = "Select * from news where news.title=?";
	public static final String SQL_FIND_ALL_AUTHORS_NEWS = "Select * from news JOIN news_author ON(news_author.news_id=news.news_id) where news_author.author_id=?";
	public static final String SQL_SHOW_ALL_NEWS = "SELECT NEWS.NEWS_ID, NEWS.TITLE,NEWS.SHORT_TEXT,NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE, COUNT (COMMENTS.COMMENT_ID) FROM NEWS JOIN COMMENTS ON (NEWS.NEWS_ID=COMMENTS.NEWS_ID) GROUP BY NEWS.NEWS_ID, NEWS.TITLE, NEWS.CREATION_DATE, NEWS.SHORT_TEXT, NEWS.MODIFICATION_DATE, NEWS.FULL_TEXT ORDER BY NEWS.MODIFICATION_DATE DESC, COUNT (COMMENTS.COMMENT_ID)";
	public static final String SQL_UPDATE = "Update news SET title=?,short_text=?,full_text=?,creation_date=?,modification_date=? where news_id=?";
	public static final String SQL_DELETE = "Delete from news where news.news_id=?";
	public static final String SQL_DELETE_NEWS_AUTHORS = "DELETE FROM news_author where news_id=?";
	public static final String SQl_DELETE_NEWS_TAG = "DELETE FROM news_tag where news_id=?";
	public static final String SQL_FIND_NEWS_BY_SEARCH_CRITERIA = "Select news_author.AUTHOR_ID,news.NEWS_ID,news.TITLE,news.SHORT_TEXT,news.FULL_TEXT,news.CREATION_DATE,news.MODIFICATION_DATE from news  JOIN news_author ON (news.news_id=news_author.news_id) JOIN news_tag ON(news_author.news_id=news_tag.news_id) JOIN COMMENTS ON (NEWS.NEWS_ID=COMMENTS.NEWS_ID)";
	public static final String SQL_FIND_NEWS_BY_SEARCH_CRITERIA_END_QUERY = " GROUP BY news_author.AUTHOR_ID,news.NEWS_ID,news.TITLE,news.SHORT_TEXT,news.FULL_TEXT,news.CREATION_DATE,news.MODIFICATION_DATE ORDER BY news.MODIFICATION_DATE, COUNT (COMMENTS.COMMENT_ID)";
	public static final String SQL_FIND_MAX_PK = "Select MAX(news_id) from news";
	public static final String SQL_COUNT_NEWS = "Select COUNT(news_id) from news";
	public static final String SQP_PAGINATION_PART_1 = "Select *   from ( select news_by_one_page.*, rownum rnum from (";
	public static final String SQP_PAGINATION_PART_2 = ") news_by_one_page where rownum <= ?) where rnum >= ? ORDER BY MODIFICATION_DATE DESC";
	public static final String SQL_FIND_PREVIOUS_NEWS = "SELECT news.* FROM (SELECT NEWS.NEWS_ID, NEWS.TITLE,NEWS.SHORT_TEXT,NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE, COUNT (COMMENTS.COMMENT_ID) FROM NEWS JOIN COMMENTS ON (NEWS.NEWS_ID=COMMENTS.NEWS_ID) GROUP BY NEWS.NEWS_ID, NEWS.TITLE, NEWS.CREATION_DATE, NEWS.SHORT_TEXT, NEWS.MODIFICATION_DATE, NEWS.FULL_TEXT ORDER BY NEWS.MODIFICATION_DATE DESC, COUNT (COMMENTS.COMMENT_ID)) news WHERE NEWS.MODIFICATION_DATE >= ? and NEWS.NEWS_ID != ? ORDER BY NEWS.MODIFICATION_DATE";
	public static final String SQL_FIND_NEXT_NEWS = "SELECT news.* FROM (SELECT NEWS.NEWS_ID, NEWS.TITLE,NEWS.SHORT_TEXT,NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE, COUNT (COMMENTS.COMMENT_ID) FROM NEWS JOIN COMMENTS ON (NEWS.NEWS_ID=COMMENTS.NEWS_ID) GROUP BY NEWS.NEWS_ID, NEWS.TITLE, NEWS.CREATION_DATE, NEWS.SHORT_TEXT, NEWS.MODIFICATION_DATE, NEWS.FULL_TEXT ORDER BY NEWS.MODIFICATION_DATE DESC, COUNT (COMMENTS.COMMENT_ID)) news WHERE NEWS.MODIFICATION_DATE <= ? and NEWS.NEWS_ID != ? ORDER BY NEWS.MODIFICATION_DATE DESC";
	

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#create(News)}
	 */
	@Override
	public Long create(News entity) throws DAOException {
		Long id = (long) 0;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS)) {
			id = getValuePrimaryKey();
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, entity.getTitle());
			preparedStatement.setString(3, entity.getShortText());
			preparedStatement.setString(4, entity.getFullText());
			preparedStatement.setTimestamp(5, new Timestamp(entity.getCreationTime().getTime()));
			preparedStatement.setDate(6, new Date(entity.getModificationDate().getTime()));
			if (!(preparedStatement.executeUpdate() > 0)) {
				throw new DAOException("Error of creating news.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return  id;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findById(int)}
	 */
	@Override
	public News findById(Long id) throws DAOException {
		News news = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				news = new News();
				news.setCreationTime(resultSet.getTimestamp(DBColumnName.NEWS_CREATION_DATE));
				news.setFullText(resultSet.getString(DBColumnName.NEWS_FULL_TEXT));
				news.setModificationDate(resultSet.getDate(DBColumnName.NEWS_MODIFICATION_DATE));
				news.setNewsId(id);
				news.setShortText(resultSet.getString(DBColumnName.NEWS_SHORT_TEXT));
				news.setTitle(resultSet.getString(DBColumnName.NEWS_TITLE));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return news;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#update(News)}
	 */
	@Override
	public boolean update(News entity) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, entity.getTitle());
			preparedStatement.setString(2, entity.getShortText());
			preparedStatement.setString(3, entity.getFullText());
			preparedStatement.setTimestamp(4, entity.getCreationTime());
			preparedStatement.setDate(5, new Date(entity.getModificationDate().getTime()));
			preparedStatement.setLong(6, entity.getNewsId());
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.setLong(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findAllNews()}
	 */
	@Override
	public List<News> findAllNews(int indexPageNews,int countNews) throws DAOException {
		List<News> allNews = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQP_PAGINATION_PART_1+SQL_SHOW_ALL_NEWS+SQP_PAGINATION_PART_2)) {
			int numberStartNews =  (indexPageNews-1)*countNews+1;
			int numberEndNews = indexPageNews*countNews;
			preparedStatement.setInt(1, numberEndNews);
			preparedStatement.setInt(2, numberStartNews);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				News news = new News();
				news.setCreationTime(resultSet.getTimestamp(DBColumnName.NEWS_CREATION_DATE));
				news.setFullText(resultSet.getString(DBColumnName.NEWS_FULL_TEXT));
				news.setModificationDate(resultSet.getDate(DBColumnName.NEWS_MODIFICATION_DATE));
				news.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
				news.setShortText(resultSet.getString(DBColumnName.NEWS_SHORT_TEXT));
				news.setTitle(resultSet.getString(DBColumnName.NEWS_TITLE));
				allNews.add(news);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return allNews;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findNewsByTitle(String)}
	 */
	@Override
	public News findNewsByTitle(String title) throws DAOException {
		News news = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TITLE)) {
			preparedStatement.setString(1, title);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				news = new News();
				news.setCreationTime(resultSet.getTimestamp(DBColumnName.NEWS_CREATION_DATE));
				news.setFullText(resultSet.getString(DBColumnName.NEWS_FULL_TEXT));
				news.setModificationDate(resultSet.getDate(DBColumnName.NEWS_MODIFICATION_DATE));
				news.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
				news.setShortText(resultSet.getString(DBColumnName.NEWS_SHORT_TEXT));
				news.setTitle(resultSet.getString(DBColumnName.NEWS_TITLE));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return news;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#saveNewsAuthors(int, int)}
	 */
	@Override
	public boolean saveNewsAuthors(Long newsId, Long authorId) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS_AUTHORS)) {
			preparedStatement.setLong(1, newsId);
			preparedStatement.setLong(2, authorId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#deleteNewsAuthor(int)}
	 */
	@Override
	public boolean deleteNewsAuthor(Long newsId) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS_AUTHORS)) {
			preparedStatement.setLong(1, newsId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#addNewsTag(int, int)}
	 */
	@Override
	public boolean addNewsTag(Long tagId, Long newsId) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS_TAG)) {
			preparedStatement.setLong(1, newsId);
			preparedStatement.setLong(2, tagId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#deleteNewsTag(int)}
	 */
	@Override
	public boolean deleteNewsTag(Long newsId) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQl_DELETE_NEWS_TAG)) {
			preparedStatement.setLong(1, newsId);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findNewsBySearchCriteria(SearchCriteriaObject)}
	 */
	@Override
	public List<News> findNewsBySearchCriteria(SearchCriteriaObject sc,int indexPageNews,int countNews) throws DAOException {
		List<News> findNews = new ArrayList<>();
		String sqlQuery = null;
		if (sc.getAuthor() != null || sc.getTags() != null) {
			sqlQuery = new String(buildSQLQuerly(sc));
		}
		if (sqlQuery != null) {
			try (Connection connection = DataSourceUtils.getConnection(dataSource);
					Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(sqlQuery);
				while (resultSet.next()) {
					News news = new News();
					news.setCreationTime(resultSet.getTimestamp(DBColumnName.NEWS_CREATION_DATE));
					news.setFullText(resultSet.getString(DBColumnName.NEWS_FULL_TEXT));
					news.setModificationDate(resultSet.getDate(DBColumnName.NEWS_MODIFICATION_DATE));
					news.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
					news.setShortText(resultSet.getString(DBColumnName.NEWS_SHORT_TEXT));
					news.setTitle(resultSet.getString(DBColumnName.NEWS_TITLE));
					findNews.add(news);
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return findNews;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findAllAuthorsNews(int)}
	 */
	@Override
	public List<News> findAllAuthorsNews(Long authorId) throws DAOException {
		List<News> list = new ArrayList<>();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_AUTHORS_NEWS)) {
			preparedStatement.setLong(1, authorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				News news = new News();
				news.setCreationTime(resultSet.getTimestamp(DBColumnName.NEWS_CREATION_DATE));
				news.setFullText(resultSet.getString(DBColumnName.NEWS_FULL_TEXT));
				news.setModificationDate(resultSet.getDate(DBColumnName.NEWS_MODIFICATION_DATE));
				news.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
				news.setShortText(resultSet.getString(DBColumnName.NEWS_SHORT_TEXT));
				news.setTitle(resultSet.getString(DBColumnName.NEWS_TITLE));
				list.add(news);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return list;
	}
	
	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#countNews()}
	 */
	@Override
	public int countNews() throws DAOException{
		int amount = 0;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_COUNT_NEWS);
			if(resultSet.next()){
				amount = resultSet.getInt("COUNT(NEWS_ID)");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return amount;
		
	}
	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findNextNews(News)}
	 */
	@Override
	public Long findPreviousNews(News currentNews) throws DAOException {
		Long previousNewsId=0L;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PREVIOUS_NEWS)) {
			preparedStatement.setDate(1, new Date(currentNews.getModificationDate().getTime()));
			preparedStatement.setLong(2, currentNews.getNewsId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				previousNewsId = resultSet.getLong(DBColumnName.NEWS_ID);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return previousNewsId;
	}
	/**
	 * @see {@link com.epam.newscommon.dao.NewsDAO#findNextNews(News)}
	 */
	@Override
	public Long findNextNews(News currentNews) throws DAOException {
		Long nextNewsId=0L;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_NEXT_NEWS)) {
			preparedStatement.setDate(1, new Date(currentNews.getModificationDate().getTime()));
			preparedStatement.setLong(2, currentNews.getNewsId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				nextNewsId = resultSet.getLong(DBColumnName.NEWS_ID);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return nextNewsId;
	}
	/**
	 * Build SQL query to find news by Search Criteria
	 * 
	 * @param sc
	 * @return StringBuilder query
	 */
	private StringBuilder buildSQLQuerly(SearchCriteriaObject sc) {
		StringBuilder query = new StringBuilder(SQL_FIND_NEWS_BY_SEARCH_CRITERIA);
		if (sc.getAuthor() != null && !(sc.getTags() == null || sc.getTags().isEmpty())) {
			query.append(" WHERE news_author.author_id=");
			query.append(sc.getAuthor().getAuthorId());
			query.append(" AND news_tag.tag_id IN(");
			for (Tag tag : sc.getTags()) {
				query.append(tag.getTagId());
				query.append(",");
			}
			query.deleteCharAt(query.length() - 1);
			query.append(") ");
		} else if (sc.getAuthor() == null && !(sc.getTags() == null || sc.getTags().isEmpty())) {
			query.append(" WHERE news_tag.tag_id IN(");
			for (Tag tag : sc.getTags()) {
				query.append(tag.getTagId());
				query.append(",");
			}
			query.deleteCharAt(query.length() - 1);
			query.append(") ");
		} else if (sc.getAuthor() != null && (sc.getTags() == null || sc.getTags().isEmpty())) {
			query.append(" WHERE news_author.author_id=");
			query.append(sc.getAuthor().getAuthorId());
		}
		query.append(SQL_FIND_NEWS_BY_SEARCH_CRITERIA_END_QUERY);
		return query;
	}
	
	/**
	 * Get value of primary key for new database's entity
	 * 
	 * @return value of primary key
	 * @throws DAOException
	 */
	private Long getValuePrimaryKey() throws DAOException {
		Long value = (long) 0;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_MAX_PK);
			if (resultSet.next()) {
				value = resultSet.getLong("MAX(news_id)") + 1;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return value;
	}

	/**
	 * Set the DataSource
	 * 
	 * @param dataSource
	 */
	public static void setDataSource(BasicDataSource dataSource) {
		NewsDAOImpl.dataSource = dataSource;
	}

	/**
	 * Get a dataSource
	 * 
	 * @return
	 */
	public static BasicDataSource getDataSource() {
		return dataSource;
	}


}
