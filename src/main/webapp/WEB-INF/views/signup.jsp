<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8" />
		<title>akif batur - blog</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/images/terminal.ico" rel="icon">
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.js"></script>
	</head>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="userName"/>
	</sec:authorize>

	<body onload='document.signupForm.userName.focus();'>

		<%-- Navigation Bar Start --%>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
						<%-- Collapse Button --%>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="${pageContext.request.contextPath}/" class="navbar-brand">aqui@aqui~$</a>
				</div>
				<div class="collapse navbar-collapse" id="mainNavBar">
				
					<%-- Navigation Bar Left Side --%>
					<%-- If user is logged-in --%>
					<c:if test="${not empty userName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/post"><spring:message code="post"/></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/category"><spring:message code="category" /></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty userName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a>
							</li>
						</ul>
					</c:if>
					
					<%-- Navigation Bar Right Side --%>
					<%-- If user is logged-in --%>
					<c:if test="${not empty userName}">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/author">${userName}</a>
							</li>
							<li>
								<form action="${pageContext.request.contextPath}/logout" method="post" name="logoutForm">
									<sec:csrfInput />
								</form> 
								<a href="#" onclick='document.logoutForm.submit();'><spring:message code="logout"/></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty userName}">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/signup"><spring:message code="signup"/></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/login"><spring:message code="login"/></a>
							</li>
						</ul>
					</c:if>
				</div>
			</div>
		</nav>
		<%-- Navigation Bar End --%>

		<div align="center">
			<c:if test="${not empty userName}">
				<div align="center">
					<h4>hello ${userName}?</h4>
				</div>
			</c:if>
			<c:if test="${empty userName}">
				<c:if test="${not empty saveAuthorModel.message}">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<spring:message code="${saveAuthorModel.message}" />
					</div>
				</c:if>
				<form:form action="${pageContext.request.contextPath}/signup"
					role="form" method="POST" name="signupForm" modelAttribute="author">
					<sec:csrfInput />
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">
									<spring:message code="signup" />
								</h4>
							</div>
							<div class="modal-body">
		
								<div class="form-group">
									<div align="center">
										<spring:message code="Name" />
										<form:errors path="name" />
									</div>
									<form:input type="text" path="name" maxlength="30" title="Name" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Username" />
										<form:errors path="userName" />
									</div>
									<form:input type="text" path="userName" maxlength="20"
										title="Username" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Email" />
										<form:errors path="email" />
									</div>
									<form:input type="email" path="email" maxlength="240"
										title="Email" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Password" />
										<form:errors path="password" />
									</div>
									<form:input type="password" path="password" maxlength="20"
										title="Password" />
								</div>
							</div>
							<div class="modal-footer ">
								<button class="btn btn-success btn-block" type="submit"
									name="submit" onclick='document.signupForm.submit();'>
									<spring:message code="signup" />
								</button>
							</div>
						</div>
					</div>
				</form:form>
			</c:if>
		</div>
		
	</body>
</html>