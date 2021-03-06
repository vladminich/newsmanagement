<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
<title><fmt:message key="news.title" /></title>
</head>
<body link="blue" vlink="blue" alink="blue" >
	<div align="center">
		<%@include file="/jsp/elements/header.jspf"%>
	</div>
	<div id="maincontent">
		<div id="back">
			<a  href="Controller/?command=show_list_news&indexNewsPage=${sessionScope.idPreviousNewsPage}">
			<fmt:message key="news.back" />
			</a>
		</div>
		<br />
		<br />
		<div id="newsTitle">${newsVO.news.title}</div>
		<div id="newsAuthor"> ( by ${newsVO.author.authorName} )</div>
		<div id="newsModificationDate">${newsVO.news.modificationDate}</div>
		<br /> <br />
		<div id="newsShortText">${newsVO.news.shortText}</div>
		<br />
		<div id="newsFullText">${newsVO.news.fullText}</div>

		<c:if test="${not empty newsVO.comments }">
			<c:forEach var="comment" items="${newsVO.comments}">
				<br />
				<div id="commentCreationDate">${comment.creationDate}</div>
				<br />
				<div id="commentText">${comment.text}</div>
				<br />
				<br />
			</c:forEach>
		</c:if>
		<br />
	
			<form action="Controller" method="GET">
			<textarea id="textareaNewComment" name="newComment"></textarea>
			<br /> <br /> <br /> <br /> <br /> 
			<input id="addComment"
				type="submit" value="<fmt:message key="news.add.comment" />" />
				<input type="hidden" name="command" value="add_comment"/>
				<input type="hidden" name="idNews" value="${newsVO.news.newsId}"/>
			</form>
		<script type="text/javascript">
			$(document).ready(function(){
				
				$('#textareaNewComment').keyup(function(){
					$('.msg').empty();
					$('#textareaNewComment').removeClass('error');	
				})
				
				
				$('#addComment').click(function(){
					var a = $('#textareaNewComment').val();
					$('.msg').empty();
					$('#textareaNewComment').removeClass('error');	
						if(a.trim() == ''){
							$('#textareaNewComment').addClass('error');
							$('#addComment').before('<div class="msg"> <fmt:message key="news.add.comment.error.empty" /></div>');
						}
						if(a.trim().length > 300){
							$('#textareaNewComment').addClass('error');
							$('#addComment').before('<div class="msg"><fmt:message key="news.add.comment.error.overflow" /></div>');
						}
					
				})
			})
			
		</script>
		<br/>
		<br/>
		<div id="previous">
			<a href="Controller?command=view_news&news_id=${idPreviousNews}&indexNewsPage=${sessionScope.indexPreviousPage}">
				<fmt:message key="news.previous" />
			</a>
		</div>
		<div id="next">
			<a href="Controller?command=view_news&news_id=${idNextNews}&indexNewsPage=${sessionScope.indexPreviousPage}">
				<fmt:message key="news.next" />
			</a>
		</div>
		
	</div>
	<div align="center">
		<%@include file="/jsp/elements/footer.jspf"%>
	</div>
</body>
</html>