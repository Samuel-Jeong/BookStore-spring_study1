<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>Books</title>
</head>

<body>
	<!-- 최상단 Navigation bar -->
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/BookMarket/home">
					Home
				</a>
			</div>
		</div>
	</nav>
	
	<!-- 도서 목록 -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	
	<!-- 도서 리스트 -->
	<div class="container">
		<div class="row" align="center">
			<c:forEach items="${bookList}" var="book">
				<div class="col-md-4">
					<img src="<c:url value="/img/${book.getBookId()}/${book.getBookId()}.png"/>" 
								onerror="this.src='<c:url value="/resources/images/default/default_profile.png"/>'" 
								style="width: 100%"/>
					
					<hr>
					<h3>${book.name}</h3>
					<p>${book.author}
						<br>${book.publisher} | ${book.releaseDate}
					<p align=left>${fn:substring(book.description, 0, 100)}...
					<p>${book.unitPrice}원
					<p><a href="<c:url value="/books/book?id=${book.bookId}"/>"
						class="btn btn-Secondary" role="button">
						상세정보 &raquo;</a>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<!-- footer -->
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>

</html>