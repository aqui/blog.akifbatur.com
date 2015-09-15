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
	
	<body>
	
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
	</body>
</html>