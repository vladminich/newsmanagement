<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<title>Error Page</title>
</head>
<body>
	<div align="center">
		<%@include file="/jsp/elements/header.jspf"%>
	</div>
	<br/>
	<br/>
	<div id="maincontent">
	<div align="center" style="color:red; font-size: x-large;font-family: fantasy;font-style: oblique;">
		${message}
		</div>
	</div>
	<div align="center">
		<%@include file="/jsp/elements/footer.jspf"%>
	</div>
</body>
</html>