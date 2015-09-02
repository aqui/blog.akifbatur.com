<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/resources/images/terminal.ico" rel="icon" >
		<title>akif batur - blog</title>
	</head>
	
	<body>
		<c:if test="${!empty author.authorId}">
			<c:set var="authorId" scope="request" value="${author.authorId}"/>
			<c:set var="authorName" scope="request" value="${author.authorName}"/>
		</c:if>
		Author ID = ${authorId}
		Author Name = ${authorName}
	</body>
	
</html>