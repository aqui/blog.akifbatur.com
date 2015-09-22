<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="securityUserName" />
	</sec:authorize>
	
	<jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
	
	<body>
	
		<jsp:include page="/WEB-INF/templates/navbar.jsp"></jsp:include>
		
		<%-- Body Start --%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
				<c:forEach items="${posts}" var="posts">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<a href="${pageContext.request.contextPath}/post/category/${posts.categoryId.categoryTitle}">${posts.categoryId.categoryTitle}</a>
								: 
								<a href="${pageContext.request.contextPath}/post/title/${posts.postTitle}">${posts.postTitle}</a>
							</h3>
						</div>
						<div class="panel-body">
							${posts.postBody}
						</div>
						<div class="panel-footer panel-info">										
							<div class="row">
								<div class="col-md-4" align="left">
									<a href="${pageContext.request.contextPath}/post/author/${posts.authorId.userName}">
										<span class="glyphicon glyphicon-user"></span> ${posts.authorId.userName}
									</a>
									<c:if test="${posts.authorId.userName == securityUserName}">
										/ 
										<a href="${pageContext.request.contextPath}/post/delete/${posts.id}">
											delete 
										</a>
										- 
										<a href="${pageContext.request.contextPath}/post/edit/${posts.id}">
											edit 
										</a>
									</c:if>
								</div>
								<div class="col-md-4" align="center">
									<spring:message code="Tags"/>:
									<c:forEach items="${posts.tagId}" var="tags">
										<a href="${pageContext.request.contextPath}/post/tag/${tags.tagText}">${tags.tagText}</a>
									</c:forEach>
								</div>
								<div class="col-md-4" align="right">
									<fmt:formatDate value="${posts.postDate}" pattern="dd:MM:YYYY/hh:mm"/>
									<c:if test="${posts.postDate != posts.postEditDate}">
										 - (<fmt:formatDate value="${posts.postEditDate}" pattern="dd:MM:YYYY/hh:mm"/>)
									</c:if>											
								</div>
							</div>			
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<%-- Body End --%>
		
	</body>
</html>