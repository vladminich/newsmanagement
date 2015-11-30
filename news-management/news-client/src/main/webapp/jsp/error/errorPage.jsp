<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Error Page</title>
</head>
<body>
	<div align="center">
		<%@include file="/jsp/elements/header.jspf"%>
	</div>
	<div id="maincontent">
		${message}
	</div>
	<div align="center">
		<%@include file="/jsp/elements/footer.jspf"%>
	</div>
</body>
</html>