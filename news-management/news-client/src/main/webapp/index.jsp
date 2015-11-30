<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Index</title>
<script src="js/jquery.js"></script>
<script src="js/api.js"></script>
</head>
<body>
	<jsp:forward page="Controller/?command=show_list_news&indexNewsPage=1"></jsp:forward>
</body>
</html>