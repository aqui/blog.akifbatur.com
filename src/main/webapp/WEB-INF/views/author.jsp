<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
			<link href="${pageContext.request.contextPath}/resources/images/terminal.ico" rel="icon" >
			<title>akif batur - blog</title>
	</head>
	<body onload='document.loginForm.username.focus();'>
		<div align="center">
			<c:if test="${not empty username}">
				<div>Hello ${username}. This is your author page.</div>
				<br>
					<a href="${pageContext.request.contextPath}/">home</a>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/admin">admin</a>
				</sec:authorize>
				<br><br>
				<c:url var="logoutUrl" value="/j_spring_security_logout" />
				<form action="${logoutUrl}" method="post">
					<input type="submit" value="logout" /> 
					<sec:csrfInput/>
				</form>
			</c:if>
			<c:if test="${empty username}">
				<c:url var="loginUrl" value="/login" />
				<a href="${loginUrl}">login</a>
			</c:if>
		</div>
	</body>
</html>