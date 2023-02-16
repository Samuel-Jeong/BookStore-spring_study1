<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>Thank you</title>
</head>

<body>
	<!-- 최상단 Navigation bar -->
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">
					Home
				</a>
			</div>
		</div>
	</nav>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">주문 완료</h1>
		</div>
	</div>
	
	<div class="container">
		<h2 class="alert alert-danger">주문해 주셔서 감사합니다.</h2>
		<p>
			주문은
			<fmt:formatDate type="date" value="${order.shipping.date}"/>
			에 배송될 예정입니다.
		</p>
		<p>
			주문 번호 : ${order.orderId}
		</p>
	</div>
	
	<div class="container">
		<p>
			<a href="<c:url value="/books/" />" class="btn btn-primary">
				&laquo; 도서 목록
			</a>
		</p>
	</div>
	
	<!-- footer -->
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>

</html>