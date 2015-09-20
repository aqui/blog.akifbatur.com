<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>akif batur - blog</title>
		<meta name="viewport" content="width-device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
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
					<c:if test="${not empty securityUserName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/post"><spring:message code="post"/></a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/category"><spring:message code="category"/></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty securityUserName}">
						<ul class="nav navbar-nav">
							<li>
								<a href="${pageContext.request.contextPath}/"><spring:message code="home"/></a>
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
								<form action="${pageContext.request.contextPath}/logout" method="post" name="logoutForm">
									<sec:csrfInput />
								</form> 
								<a href="#" onclick='document.logoutForm.submit();'><spring:message code="logout"/></a>
							</li>
						</ul>
					</c:if>
					<%-- If user is not logged-in --%>
					<c:if test="${empty securityUserName}">
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
				
		<%-- Body Start --%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><spring:message code="PostFacility"/></h3>
						</div>
						<div class="panel-body">
							<c:if test="${not empty savePostModel.message}">
								<div class="alert alert-danger fade in" align="center">
									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									${savePostModel.message}
								</div>
							</c:if>
							<form:form action="${pageContext.request.contextPath}/post" role="form" method="POST" name="postForm" modelAttribute="post">
								<div class="form-group">
									<label for="categoryId.id"><spring:message code="Category"/> <form:errors path="categoryId"/></label>
									<br/>
									<form:select path="categoryId.id">
										<form:options path="categoryId.id" items="${categories}" itemLabel="categoryTitle" itemValue="id"/>
									</form:select>
									<c:if test="${empty categories}">
										<a href="${pageContext.request.contextPath}/category"><spring:message code="AddCategory"/></a>
									</c:if>
								</div>
								<div class="form-group">
									<label for="postTitle"><spring:message code="Title"/> <form:errors path="postTitle"/></label>
									<br/>
									<form:input type="text" path="postTitle" maxlength="20" title="Title"/>
								</div>
								<div class="form-group">
									<label for="tags"><spring:message code="Tags"/> </label>
									<br/>
									<input type="text" name="tags" maxlength="20" title="Tags" value="${savePostModel.tags}"/>
									<br>(<spring:message code="useCommaToSeparateTags"/>)
								</div>
								<div class="form-group">
									<label for="postBody"><spring:message code="Post"/> <form:errors path="postBody"/></label>
									<br/>
									<form:textarea class="form-control" rows="10" cols="10" path="postBody" title="Post"></form:textarea>
								</div>
								<div class="form-group">
									<button class="btn btn-success btn-block" type="submit" name="submit" onclick='document.postForm.submit();'>
										<spring:message code="send" />
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