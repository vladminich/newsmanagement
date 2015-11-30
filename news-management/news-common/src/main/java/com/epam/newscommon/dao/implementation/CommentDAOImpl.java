package com.epam.newscommon.dao.implementation;

import java.sql.Connection;
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
import com.epam.newscommon.dao.CommentDAO;
import com.epam.newscommon.entity.Comment;

/**
 * Class CommentDAOImpl. Implementation of CommentDAO
 * 
 * @author Uladzislau_Minich
 *
 */
public class CommentDAOImpl implements CommentDAO {
	private static BasicDataSource dataSource;
	private static final String SQL_FIND_ALL_NEWS_COMMENTS = "Select * from comments where news_id=? ORDER BY CREATION_DATE DESC";
	private static final String SQL_FIND_BY_ID = "Select * from comments where comments.comment_id=?";
	private static final String SQL_UPDATE_COMMENT = "Update comments SET news_id=?,comment_text=?,creation_date=? where comment_id=?";
	private static final String SQL_CREATE_COMMENT = "Insert into comments(comment_id,news_id,comment_text,creation_date) values(?,?,?,?)";
	private static final String SQL_DELETE_COMMENT = "Delete from comments where comments.comment_id=?";
	private static final String SQL_FIND_MAX_PK = "Select MAX(comment_id) from comments";

	/**
	 * @see {@link com.epam.newscommon.dao.CommentDAO#create(Comment)}
	 */
	@Override
	public Long create(Comment entity) throws DAOException {
		Long id = (long) 0;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_COMMENT)) {
			id = getValuePrimaryKey();
			preparedStatement.setLong(1, id);
			preparedStatement.setLong(2, entity.getNewsId());
			preparedStatement.setString(3, entity.getText());
			preparedStatement.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));

			if (!(preparedStatement.executeUpdate() > 0)) {
				throw new DAOException("Error of creating comment.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return id;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.CommentDAO#findById(int)}
	 */
	@Override
	public Comment findById(Long id) throws DAOException {
		Comment comment = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				comment = new Comment();
				comment.setCommentId(resultSet.getLong(DBColumnName.COMMENT_ID));
				comment.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
				comment.setText(resultSet.getString(DBColumnName.COMMENT_TEXT));
				comment.setCreationDate(resultSet.getTimestamp(DBColumnName.COMMENT_CREATION_DATE));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return comment;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.CommentDAO#update(Comment)}
	 */
	@Override
	public boolean update(Comment entity) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COMMENT)) {
			preparedStatement.setLong(1, entity.getNewsId());
			preparedStatement.setString(2, entity.getText());
			preparedStatement.setTimestamp(3, entity.getCreationDate());
			preparedStatement.setLong(4, entity.getCommentId());
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
	 * @see {@link com.epam.newscommon.dao.CommentDAO#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT)) {
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
	 * @see {@link com.epam.newscommon.dao.CommentDAO#showAllNewsComment(int)}
	 */
	@Override
	public List<Comment> showAllNewsComment(Long id) throws DAOException {
		List<Comment> allComments = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_NEWS_COMMENTS)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Comment comment = new Comment();
				comment.setCommentId(resultSet.getLong(DBColumnName.COMMENT_ID));
				comment.setNewsId(resultSet.getLong(DBColumnName.NEWS_ID));
				comment.setText(resultSet.getString(DBColumnName.COMMENT_TEXT));
				comment.setCreationDate(resultSet.getTimestamp(DBColumnName.COMMENT_CREATION_DATE));
				allComments.add(comment);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return allComments;
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
				value = resultSet.getLong("MAX(comment_id)") + 1;
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
		CommentDAOImpl.dataSource = dataSource;
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
