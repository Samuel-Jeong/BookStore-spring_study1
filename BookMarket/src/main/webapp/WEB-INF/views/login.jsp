<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>로그인</title>
</head>

<body>
	<!-- 최상단 Navigation bar -->
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">
					로그인
				</a>
			</div>
		</div>
	</nav>
	
	<!-- Header -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">로그인</h1>
		</div>
	</div>
	
	<!-- Content -->
	<div class="container col-md-4">
		<div class="text-center">
			<h3 class="form-signin-heading">Please login</h3>
		</div>
		<c:if test="${not empty error}"> <!-- 인증에 실패했을 때 모델 속성 error 에 저장된 값이 있다면 오류 출력 -->
			<h3 class="alert alert-danger">
				UserName 과 Password 가 올바르지 않습니다. <br />
			</h3>
		</c:if>
		<form class="form-signin" action="<c:url value="/login"/>" method="post">
			<div class="form-group row"> <!-- <form> 태그 선언 및 로그인 인증을 위한 요청 경로 설정 -->
				<input type="text" name="username" class="form-control" placeholder="User Name" required autofocus/>
			</div>
			<div class="form-group row"> <!-- <input> 태그로 사용자 계정 이름 설정 -->
				<input type="password" name="password" class="form-control" placeholder="Password" required/>
			</div>
			<div class="form-group row"> <!-- <input> 태그로 사용자 비밀번호 설정 -->
				<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <!-- CSRF 공격 방어 -->
			</div>
		</form>
	</div>
	
	<!-- footer -->
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>

</html>