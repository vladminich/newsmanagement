package com.epam.newscommon.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.epam.newscommon.constant.DBColumnName;
import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.dao.TagDAO;
import com.epam.newscommon.entity.Tag;

/**
 * Class TagDAOImpl. Implementation of TagDAO
 * 
 * @author Uladzislau_Minich
 *
 */
public class TagDAOImpl implements TagDAO {
	private static BasicDataSource dataSource;
	public static final String SQL_CREATE_TAG = "Insert into tag(tag_id,tag_name) values(?,?)";
	public static final String SQL_UPDATE_TAG = "Update tag SET tag_name=? where tag_id=?";
	public static final String SQL_FIND_BY_ID = "Select * from tag where tag_id=?";
	public static final String SQL_DELETE_TAG = "Delete from tag where tag_id=?";
	public static final String SQL_FIND_TAGS_BY_NEWS_ID = "Select * from TAG  JOIN news_tag ON (news_tag.tag_id=tag.tag_id) where news_tag.news_id=?";
	public static final String SQL_FIND_ALL_TAGS = "Select * from TAG";
	public static final String SQL_FIND_MAX_PK = "Select MAX(tag_id) from tag";

	/**
	 * @see {@link com.epam.newscommon.dao.TagDAO#create(Tag)}
	 */
	@Override
	public Long create(Tag entity) throws DAOException {
		Long id = (long) 0;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TAG)) {
			id = getValuePrimaryKey();
			preparedStatement.setLong(1, id);
			preparedStatement.setString(2, entity.getTagName());
			if (!(preparedStatement.executeUpdate() > 0)) {
				throw new DAOException("Error of creating tag.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
		return id;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.TagDAO#findById(int)}
	 */
	@Override
	public Tag findById(Long id) throws DAOException {
		Tag tag = null;
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				tag = new Tag();
				tag.setTagId(resultSet.getLong(DBColumnName.TAG_ID));
				tag.setTagName(resultSet.getString(DBColumnName.TAG_NAME));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return tag;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.TagDAO#update(Tag)}
	 */
	@Override
	public boolean update(Tag entity) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TAG)) {
			preparedStatement.setString(1, entity.getTagName());
			preparedStatement.setLong(2, entity.getTagId());
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
	 * @see {@link com.epam.newscommon.dao.TagDAO#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws DAOException {
		boolean flag = false;
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TAG)) {
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
	 * @see {@link com.epam.newscommon.dao.TagDAO#findAllNewsTag(int)}
	 */
	@Override
	public List<Tag> findAllNewsTag(Long newsId) throws DAOException {
		List<Tag> tags = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TAGS_BY_NEWS_ID)) {
			preparedStatement.setLong(1, newsId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Tag tag = new Tag();
				tag.setTagId(resultSet.getLong(DBColumnName.TAG_ID));
				tag.setTagName(resultSet.getString(DBColumnName.TAG_NAME));
				tags.add(tag);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return tags;
	}

	/**
	 * @see {@link com.epam.newscommon.dao.TagDAO#findAllTag()}
	 */
	@Override
	public List<Tag> findAllTag() throws DAOException {
		List<Tag> tags = new ArrayList<>();
		try (Connection connection = DataSourceUtils.getConnection(dataSource);
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_TAGS);
			while (resultSet.next()) {
				Tag tag = new Tag();
				tag.setTagId(resultSet.getLong(DBColumnName.TAG_ID));
				tag.setTagName(resultSet.getString(DBColumnName.TAG_NAME));
				tags.add(tag);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return tags;
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
				value = resultSet.getLong("MAX(tag_id)") + 1;
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
	public void setDataSource(BasicDataSource dataSource) {
		TagDAOImpl.dataSource = dataSource;
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
