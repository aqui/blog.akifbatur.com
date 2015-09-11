<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>akif batur - blog</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/images/terminal.ico" rel="icon">
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	</head>
	<body onload='document.loginForm.username.focus();'>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#mainNavBar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="#" class="navbar-brand">aqui@aqui~$</a>
				</div>
				<div class="collapse navbar-collapse" id="mainNavBar">
					<ul class="nav navbar-nav">
						<li>
							<a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="${pageContext.request.contextPath}/signup"><spring:message code="signup"/></a>
						</li>
						<li class="active">
							<a href=""><spring:message code="login"/></a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	
		<div align="center">
			<c:if test="${not empty loginModel.message}">
				<div class="alert alert-danger fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>${loginModel.message}
				</div>
			</c:if>
			<div>
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title"><spring:message code="login"/></h4>
						</div>
						<div class="modal-body">
							<form action="<c:url value='/login'/>" role="form" method="POST" name="loginForm">
								<sec:csrfInput />
								<div class="form-group">
									<input type="text" placeholder="<spring:message code="Username"/>" name="username" required="required">
								</div>
								<div class="form-group">
									<input type="password" placeholder="<spring:message code="Password"/>" name="password" required="required">
								</div>
							</form>
						</div>
						<div class="modal-footer ">
							<button class="btn btn-primary btn-block" type="submit" name="submit" onclick='document.loginForm.submit();'>
								<spring:message code="login"/>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>