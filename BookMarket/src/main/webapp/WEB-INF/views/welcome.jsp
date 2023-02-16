<%@ page contentType="text/html; charset=utf-8" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>Welcome</title>
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
	
	<!-- greeting -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">${greeting}</h1>
		</div>
	</div>
	
	<!-- strapline -->
	<div class="container">
		<div class="text-center">
			<h3>${strapline}</h3>
		</div>
	</div>
	
	<!-- footer -->
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>

</html>