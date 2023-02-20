<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
<title>도서 상세 정보</title>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<img src="<c:url value="/img/${book.getBookId()}/${book.getFileName()}.png"/>"
							onerror="this.src='<c:url value="/resources/images/default/default_profile.png"/>'" 
							style="width: 100%"/>
			</div>
			<div class="col-md-8">
				<h3>${book.name}</h3>
				<p>${book.description}</p>
				
				<br>
				<p>
					<b>도서 코드 : </b>
					<span class="badge badge-info">${book.bookId}</span>
				</p>
				<p>
					<b>저자</b> : ${book.author}
				</p>
				<p>
					<b>출판사</b> : ${book.publisher}
				</p>
				<p>
					<b>출판일</b> : ${book.releaseDate}
				</p>
				<p>
					<b>분류</b> : ${book.category}
				</p>
				<p>
					<b>재고 수</b> : ${book.unitsInStock}
				</p>
				<h4>${book.unitPrice} 원</h4>
				<br>
				
				<form:form name="addForm" method="put">
					<p>
					<a href="javascript:addToCart('../cart/add/${book.bookId}')" class="btn btn-primary">
						도서 주문 &raquo;
					</a>
					<a href="<c:url value="/cart"/>" class="btn btn-warning">
						장바구니 &raquo;
					</a>
					<a href="<c:url value="/books"/>" class="btn btn-secondary">
						도서 목록 &raquo;
					</a>
				</form:form>
				
			</div>
		</div>
	</div>

</body>

</html>