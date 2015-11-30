package com.epam.newsclient.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {
	private static final int COUNT_NEWS_ON_PAGE = 3;
	private int countNews;
	private int currentPage;

	@Override
	public int doStartTag() throws JspException {
		int i = 0;
		try {
			int totalAmountPage = 0;
			if (countNews % COUNT_NEWS_ON_PAGE == 0) {
				totalAmountPage = countNews / COUNT_NEWS_ON_PAGE;
			} else {
				totalAmountPage = countNews / COUNT_NEWS_ON_PAGE + 1;
			}
			if (totalAmountPage < 5) {
				for (i = 1; i <= totalAmountPage; i++) {
					if (i != currentPage) {
						pageContext.getOut()
								.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					} else {
						pageContext.getOut()
								.write("<a style=\"font-size:25; text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					}
				}
			} else if (totalAmountPage - currentPage <= 2 && currentPage>2) {
				pageContext.getOut()
						.write("<a style=\" text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
								+ 1 + "\"> << </a>");
				for (i = totalAmountPage - 3; i <= totalAmountPage; i++) {
					if (i != currentPage) {
						pageContext.getOut()
								.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					} else {
						pageContext.getOut()
								.write("<a style=\"font-size:25; text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					}
				}
			} else if (currentPage > 2) {
				pageContext.getOut()
						.write("<a style=\" text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
								+ 1 + "\"> << </a>");
				for (i = currentPage - 1; i <= currentPage + 2; i++) {
					if (i != currentPage) {
						pageContext.getOut()
								.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					} else {
						pageContext.getOut()
								.write("<a style=\"font-size:25; text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					}
				}
				if(totalAmountPage - currentPage > 3){
					pageContext.getOut().write("...");
				}
				pageContext.getOut()
						.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
								+ totalAmountPage + "\">" + totalAmountPage + " </a>");
				
			} else {
				for (i = 1; i <= 4; i++) {
					if (i != currentPage) {
						pageContext.getOut()
								.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					} else {
						pageContext.getOut()
								.write("<a style=\"font-size:25; text-decoration: none;\" href=\"controller?command=show_list_news&indexNewsPage="
										+ i + "\">" + i + " </a>");
					}
				}
				pageContext.getOut().write("...");
				pageContext.getOut()
						.write("<a style=\"text-decoration: none;\"href=\"controller?command=show_list_news&indexNewsPage="
								+ totalAmountPage + "\">" + totalAmountPage + " </a>");
			}
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setCountNews(int countNews) {
		this.countNews = countNews;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
