<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<link href="/BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>Customer</title>
</head>

<body>
	
	<div class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<fieldset>
				<legend>고객 세부 사항</legend>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">고객 ID</label>
					<div class="col-sm-3">
						<form:input path="customerId" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">성명</label>
					<div class="col-sm-3">
						<form:input path="name" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">전화번호</label>
					<div class="col-sm-3">
						<form:input path="phone" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">국가명</label>
					<div class="col-sm-3">
						<form:input path="address.country" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">우편번호</label>
					<div class="col-sm-3">
						<form:input path="address.zipCode" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">주소</label>
					<div class="col-sm-5">
						<form:input path="address.addressName" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">세부 주소<label>
					<div class="col-sm-5">
						<form:input path="address.detailName" class="form-control"/>
					</div>
				</div>
				
				<!-- http://localhost:8080/Chapter15/checkout?execution=els1 > flowExecutionKey : els1 -->
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				
				<div class="form-group row">
					<input type="submit" class="btn btn-primary" value="등록" name="_eventId_customerInfo"/>
					<button class="btn btn-default" name="_eventId_cancel">취소</button>
				</div>
			</fieldset>
		</form:form>
	</div>

</body>

</html>