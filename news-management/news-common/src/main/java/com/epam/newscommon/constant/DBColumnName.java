package com.epam.newscommon.constant;

/**
 * Class DBColumnName. Contains names of database columns which used in DAO
 * layer
 * 
 * @author Uladzislau_Minich
 *
 */
public class DBColumnName {
	/* news */
	public static final String NEWS_TITLE = "title";
	public static final String NEWS_ID = "news_id";
	public static final String NEWS_SHORT_TEXT = "short_text";
	public static final String NEWS_FULL_TEXT = "full_text";
	public static final String NEWS_CREATION_DATE = "creation_date";
	public static final String NEWS_MODIFICATION_DATE = "modification_date";
	/* author */
	public static final String AUTHOR_ID = "author_id";
	public static final String AUTHOR_NAME = "author_name";
	public static final String AUTHOR_EXPIRED = "expired";
	/* comment */
	public static final String COMMENT_ID = "comment_id";
	public static final String COMMENT_TEXT = "comment_text";
	public static final String COMMENT_CREATION_DATE = "creation_date";
	/* tag */
	public static final String TAG_ID = "tag_id";
	public static final String TAG_NAME = "tag_name";
}
