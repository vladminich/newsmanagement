<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<script src="js/jquery.js"></script>
	
<c:if test="${sessionScope.locale == 'en' or empty sessionScope.locale}">
	<fmt:setBundle basename="message_EN" />
</c:if>
<c:if test="${sessionScope.locale == 'ru'}">
	<fmt:setBundle basename="message_RU" />
</c:if>

<title><fmt:message key="newslist.title" /></title>
</head>

<body link="blue" vlink="blue" alink="blue" >
	  <%@include file="/jsp/elements/header.jspf"%>
	<br/>
	<div id="maincontent">
	<br/>
	<div align="center">
		<form action="Controller" method = "GET">
    		<select  name = "author">
    			<c:choose>
  		 			<c:when test="${empty tags }">
  		 				<option selected="selected" disabled="disabled"><fmt:message key="newslist.choose-author" /></option>
  		 			</c:when>
  		 			<c:otherwise>
  		 				<option value = "${author.authorId}">${author.authorName}</option>
  		 			</c:otherwise>
  		 		</c:choose>
    			<c:forEach var="author" items="${authors}">
        			<option value = "${author.authorId}">${author.authorName}</option>
        		</c:forEach>
  		 	</select>
  		 	<select  name = "tags" multiple="multiple" >
  		 		<c:choose>
  		 			<c:when test="${empty tags }">
  		 				<option selected="selected" disabled="disabled"><fmt:message key="newslist.choose-tags" /></option>
  		 			</c:when>
  		 			<c:otherwise>
  		 				<c:forEach var="tag" items="${tags}">
        					<option selected="selected" value = "${tag.tagId}">${tag.tagName}</option>
        				</c:forEach>
  		 			</c:otherwise>
  		 		</c:choose>
    			<c:forEach var="tag" items="${tags}">
        			<option value = "${tag.tagId}">${tag.tagName}</option>
        		</c:forEach>
  		 	</select>
  		 	<input type=hidden name="command" value="show_list_news"/>
  		 	<input type="submit" value="find"/>
  		 	<input type="hidden" name="indexNewsPage" value="1"/>
		</form>
		<form action="Controller" method = "GET">
			<input type=hidden name="command" value="show_list_news"/>
  		 	<input type="submit" value="reset"/>
  		 	<input type="hidden" name="indexNewsPage" value="1"/>
  		 	<input type="hidden" name="clear" value="true"/>
		</form>
	</div>
	<br/>
	<div>
		<c:if test="${not empty newsList }">
			<c:forEach var="newsVO" items="${newsList}">
				<div id="newsTitle">${newsVO.news.title}</div>
				<div id="newsAuthor"> ( by ${newsVO.author.authorName} )</div>
				<div id="newsModificationDate">${newsVO.news.modificationDate}</div>
				<br/>
				<br/>
				<div id="newsShortText">${newsVO.news.shortText}</div>
				<c:if test="${not empty newsVO.tags }">
					<c:forEach var="tag" items="${newsVO.tags}">
						<div id="newsTags">#${tag.tagName}</div>
					</c:forEach>
				</c:if>
				<br/>
				<c:choose>
					<c:when test="${not empty newsVO.comments }">
						<div id="newsComments" >
							<fmt:message key="newslist.comments" />(<c:out value="${fn:length(newsVO.comments)}" />)</div>
					</c:when>
					<c:otherwise>
						<div id="newsComments">
							<fmt:message key="newslist.comments" />(0)</div>
					</c:otherwise>
				</c:choose>
				<div id="newsShowMore">
				<a href="controller?command=view_news&news_id=${newsVO.news.newsId}&indexPreviousPage=${indexNewsPage}"><fmt:message
						key="newslist.view" /></a></div>
				<br/>
				<hr />
				<br/>
			</c:forEach>
		</c:if>
	</div>
	<div  align="center" id="paganition" >
	<ctg:pagination-page countNews="${countNews}" currentPage="${indexNewsPage}"/>
	</div>
	</div>
	<div align="center">
		<%@include file="/jsp/elements/footer.jspf"%>
	</div>
</body>
</html>