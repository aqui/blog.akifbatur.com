<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username"
		var="securityUserName" />
</sec:authorize>

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
					<li><a href="${pageContext.request.contextPath}/"><span
							class="glyphicon glyphicon-home"></span> <spring:message
								code="home" /></a></li>
					<li><a href="${pageContext.request.contextPath}/post/write"><span
							class="glyphicon glyphicon-pencil"></span> <spring:message
								code="post" /></a></li>
					<li><a href="${pageContext.request.contextPath}/category"><span
							class="glyphicon glyphicon-list-alt"></span> <spring:message
								code="category" /></a></li>
				</ul>
			</c:if>
			<%-- If user is not logged-in --%>
			<c:if test="${empty securityUserName}">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/"><span
							class="glyphicon glyphicon-home"></span> <spring:message
								code="home" /></a></li>
				</ul>
			</c:if>

			<%-- Navigation Bar Right Side --%>
			<%-- If user is logged-in --%>
			<c:if test="${not empty securityUserName}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/author"><span
							class="glyphicon glyphicon-user"></span> ${securityUserName}</a></li>
					<li>
						<form action="${pageContext.request.contextPath}/logout"
							method="post" name="logoutForm">
							<sec:csrfInput />
						</form> <a href="#" onclick='document.logoutForm.submit();'><span
							class="glyphicon glyphicon-log-out"></span> <spring:message
								code="logout" /></a>
					</li>
				</ul>
			</c:if>
			<%-- If user is not logged-in --%>
			<c:if test="${empty securityUserName}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/signup"><span
							class="glyphicon glyphicon-user"></span> <spring:message
								code="signup" /></a></li>
					<li><a href="${pageContext.request.contextPath}/login"><span
							class="glyphicon glyphicon-log-in"></span> <spring:message
								code="login" /></a></li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>
<%-- Navigation Bar End --%>