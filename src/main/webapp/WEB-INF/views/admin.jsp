<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/images/terminal.ico"
	rel="icon">
<title>akif batur - blog</title>
</head>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="userName" />
</sec:authorize>
<body>
	<div align="center">
		<c:if test="${not empty userName}">
			<div>${userName}'s author page</div>
			<br>
			<br>
			<div align="right">
				<c:url var="logoutUrl" value="/logout" />
				<form action="${logoutUrl}" method="post">
					<input type="submit" value="logout" />
					<sec:csrfInput />
				</form>
			</div>
		</c:if>
	</div>
	<br>
	<br>
	<div align="center">
		<c:if test="${empty userName}">
			<c:url var="loginUrl" value="/login" />
			<a href="${loginUrl}">login</a>
		</c:if>
	</div>
</body>
</html>