<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>akif batur - blog</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/images/terminal.ico"
	rel="icon">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</head>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="userName" />
</sec:authorize>

<body>

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
					<li><a href="${pageContext.request.contextPath}/"><spring:message
								code="home" /></a></li>
					<c:if test="${not empty userName}">
						<li class="active"><a href=""><spring:message code="post" /></a>
						</li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty userName}">
						<li><a href="${pageContext.request.contextPath}/signup"><spring:message
									code="signup" /></a></li>
						<li><a href="${pageContext.request.contextPath}/login"><spring:message
									code="login" /></a></li>
					</c:if>
					<c:if test="${not empty userName}">
						<li><a href="${pageContext.request.contextPath}/author">${userName}</a>
						</li>
						<li><c:url var="logoutUrl" value="/logout" />
							<form action="${logoutUrl}" method="post" name="logoutForm">
								<sec:csrfInput />
							</form> <a href="#" onclick='document.logoutForm.submit();'><spring:message
									code="logout" /></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<c:if test="${not empty savePostModel.message}">
		<div class="alert alert-danger fade in" align="center">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			${savePostModel.message}
		</div>
	</c:if>
	<div align="center">
		<form:form action="${pageContext.request.contextPath}/post"
			role="form" method="POST" name="postForm" modelAttribute="post">
			<div class="form-group">
				<label for="categoryId.id">Category <form:errors
						path="categoryId" /></label>
				<form:select path="categoryId.id">
					<form:options path="categoryId.id" items="${categories}"
						itemLabel="categoryTitle" itemValue="id" />
				</form:select>
			</div>
			<div class="form-group">
				<label for="postTitle">Title <form:errors path="postTitle" /></label>
				<form:input type="text" path="postTitle" maxlength="20"
					title="Title" />
			</div>
			<div class="form-group">
				<label for="postBody">Post <form:errors path="postBody" /></label>
				<form:textarea class="form-control" rows="10" cols="10"
					path="postBody" title="Post"></form:textarea>
			</div>
			<div class="form-group">
				<button class="btn btn-success btn-block" type="submit"
					name="submit" onclick='document.postForm.submit();'>
					<spring:message code="send" />
				</button>
			</div>
		</form:form>
	</div>
</body>
</html>