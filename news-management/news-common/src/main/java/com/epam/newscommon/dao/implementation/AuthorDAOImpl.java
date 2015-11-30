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
import com.epam.newscommon.dao.AuthorDAO;
import com.epam.newscommon.entity.Author;

/**
 * Class AuthorDAOImpl. Implementation of AuthorDAO
 * 
 * @author Uladzislau_Minich
 *
 */
public class AuthorDAOImpl implements AuthorDAO {

	private static BasicDataSource dataSource;

	private static final String SQL_FIND_ALL_AUTHORS = "SELECT * FROM AUTHORS";
	private static final String SQL_FIND_AUTHOR_BY_ID = "Select * from authors where authors.author_id = ?";
	private static final String SQL_FIND_AUTHOR_BY_NEWS_ID = "Select * from authors  JOIN news_author ON (authors.author_id=news_author.author_id) where news_id=?";
	private static final String SQL_FIND_AUTHOR_BY_NAME = "Select * from authors where authors.author_name = ?";
	private static final String SQL_FIND_NOT_EXPIRED_AUTHORS = "Select * from authors where authors.expired IS NULL";
	private static final String SQL_CREATE_AUTHOR = "Insert into authors(author_id,author_name,expired) values(?,?,?) ";
	private static final String SQL_UPDATE_AUTHOR = "Update authors SET author_name=?,expired=? where author_id=?";
	private static final String SQL_UPDATE_AUTHOR_EXPIRED = "Update authors SET expired=? where author_id=?";
	private static final String SQL_DELETE_AUTHOR = "DELETE From authors where author_id=?";
	private static final String SQL_FIND_MAX_PK = "Select MAX(author_id) from authors";

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#create(Author)}
	 */
	@Override
	public Long create(Author entity) throws DAOException {
		Long id = (long) 0;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_AUTHOR)) {
			id = getValuePrimaryKey();
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, entity.getAuthorName());
			preparedStatement.setDate(3, null);
			if (!(preparedStatement.executeUpdate() > 0)) {
				throw new DAOException("Error of creating author.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return id;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#findById(int)}
	 */
	@Override
	public Author findById(Long id) throws DAOException {
		Author author = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				author = new Author();
				author.setAuthorId(resultSet.getLong(DBColumnName.AUTHOR_ID));
				author.setAuthorName(resultSet.getString(DBColumnName.AUTHOR_NAME));
				author.setExpired(resultSet.getTimestamp((DBColumnName.AUTHOR_EXPIRED)));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#update(Author)}
	 */
	@Override
	public boolean update(Author entity) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTHOR)) {
			preparedStatement.setString(1, entity.getAuthorName());
			preparedStatement.setDate(2, (Date) entity.getExpired());
			preparedStatement.setLong(3, entity.getAuthorId());
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
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_AUTHOR)) {
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
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#findAuthorByName(String)}
	 */
	@Override
	public Author findAuthorByName(String authorName) throws DAOException {
		Author author = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_NAME)) {
			preparedStatement.setString(1, authorName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				author = new Author();
				author.setAuthorId(resultSet.getLong(DBColumnName.AUTHOR_ID));
				author.setAuthorName(resultSet.getString(DBColumnName.AUTHOR_NAME));
				author.setExpired(resultSet.getTimestamp(DBColumnName.AUTHOR_EXPIRED));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#expiredAuthor(int)}
	 */
	@Override
	public boolean expiredAuthor(Long idAuthor) throws DAOException {
		boolean flag = false;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTHOR_EXPIRED)) {
			preparedStatement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
			preparedStatement.setLong(2, idAuthor);
			if (preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#findAuthorByNews(int)}
	 */
	@Override
	public Author findAuthorByNews(Long idNews) throws DAOException {
		Author author = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_NEWS_ID)) {
			preparedStatement.setLong(1, idNews);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				author = new Author();
				author.setAuthorId(resultSet.getLong(DBColumnName.AUTHOR_ID));
				author.setAuthorName(resultSet.getString(DBColumnName.AUTHOR_NAME));
				author.setExpired(resultSet.getTimestamp(DBColumnName.AUTHOR_EXPIRED));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#findNotExpiredAuthors()}
	 */
	@Override
	public List<Author> findNotExpiredAuthors() throws DAOException {
		List<Author> authors = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_NOT_EXPIRED_AUTHORS);
			while (resultSet.next()) {
				Author author = new Author();
				author.setAuthorId(resultSet.getLong(DBColumnName.AUTHOR_ID));
				author.setAuthorName(resultSet.getString(DBColumnName.AUTHOR_NAME));
				author.setExpired(resultSet.getTimestamp(DBColumnName.AUTHOR_EXPIRED));
				authors.add(author);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return authors;
	}
	/**
	 * @see {@link com.epam.newscommon.dao.AuthorDAO#findAllAuthors()}
	 */
	@Override
	public List<Author> findAllAuthors() throws DAOException {
		List<Author> authors = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_AUTHORS);
			while (resultSet.next()) {
				Author author = new Author();
				author.setAuthorId(resultSet.getLong(DBColumnName.AUTHOR_ID));
				author.setAuthorName(resultSet.getString(DBColumnName.AUTHOR_NAME));
				author.setExpired(resultSet.getTimestamp(DBColumnName.AUTHOR_EXPIRED));
				authors.add(author);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return authors;	
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
				value = resultSet.getLong("MAX(author_id)") + 1;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return value;
	}

	/**
	 * Set a field dataSource
	 * 
	 * @param dataSource
	 */
	public static void setDataSource(BasicDataSource dataSource) {
		AuthorDAOImpl.dataSource = dataSource;
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
