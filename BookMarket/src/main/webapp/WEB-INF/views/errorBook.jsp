<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>예외처리</title>
</head>

<body>
	
	<div class="container">
		<p>${url}</p>
		<p>${exception}</p>
	</div>
	<div class="container">
		<p>
			<a href="<c:url value="/books"/>" class="btn btn-secondary">
				도서 목록 &raquo;
			</a>
		</p>
	</div>

</body>

</html>