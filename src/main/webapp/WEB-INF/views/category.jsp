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
		<sec:authentication property="principal.username" var="securityUserName"/>
	</sec:authorize>

	<body>
	
		<%-- Navigation Bar Start --%>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#mainNavBar">
						<%-- Collapse Button --%>
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="${pageContext.request.contextPath}/" class="navbar-brand">aqui@aqui~$</a>
				</div>
				<div class="collapse navbar-collapse" id="mainNavBar">
	
					<%-- Navigation Bar Left Side --%>
					<%-- If user is logged-in --%>
					<c:if test="${not empty securityUserName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home" /></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/post"><spring:message code="post" /></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/category"><spring:message code="category" /></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty securityUserName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home" /></a>
							</li>
						</ul>
					</c:if>
	
					<%-- Navigation Bar Right Side --%>
					<%-- If user is logged-in --%>
					<c:if test="${not empty securityUserName}">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/author"><span class="glyphicon glyphicon-user"></span> ${securityUserName}</a>
							</li>
							<li>
								<c:url var="logoutUrl" value="/logout"/>
								<form action="${logoutUrl}" method="post" name="logoutForm">
									<sec:csrfInput/>
								</form> <a href="#" onclick='document.logoutForm.submit();'><spring:message code="logout" /></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty securityUserName}">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/signup"><spring:message code="signup" /></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/login"><spring:message code="login" /></a>
							</li>
						</ul>
					</c:if>
				</div>
			</div>
		</nav>
		<%-- Navigation Bar End --%>		
		
		<%-- Body Start --%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><spring:message code="CategoryFacility"/></h3>
						</div>
						<div class="panel-body">
							<c:if test="${not empty saveCategoryModel.message}">
								<div class="alert alert-danger fade in" align="center">
									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									${saveCategoryModel.message}
								</div>
							</c:if>
							<form:form action="${pageContext.request.contextPath}/category" role="form" method="POST" name="categoryForm" modelAttribute="category">
								<div class="form-group">
									<label for="categoryTitle"><spring:message code="CategoryTitle"/> <form:errors path="categoryTitle"/></label>
									<br>
									<form:input type="text" path="categoryTitle" maxlength="20" title="Category Title"/>
								</div>
								<div class="form-group">
									<button class="btn btn-success btn-block" type="submit" name="submit" onclick='document.categoryForm.submit();'>
										<spring:message code="send"/>
									</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%-- Body End --%>
		
	</body>
</html>