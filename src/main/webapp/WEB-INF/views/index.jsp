<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

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
<%-- 											<sec:authorize access="hasRole('ROLE_ADMIN')"> --%>
<%-- 												<a href="${pageContext.request.contextPath}/admin">admin</a> --%>
<%-- 											</sec:authorize> --%>
											<c:if test="${posts.authorId.userName == securityUserName}">
											 / 
												<a href="${pageContext.request.contextPath}/post/delete/${posts.id}">
													<span class="glyphicon glyphicon-trash"></span> delete 
												</a>
												- 
												<a href="${pageContext.request.contextPath}/post/edit/${posts.id}">
													<span class="glyphicon glyphicon-edit"></span> edit 
												</a>
											</c:if>
										</div>
										<div class="col-md-4" align="center">
											<c:if test="${not empty posts.tagId}">
												<c:forEach items="${posts.tagId}" var="tags">
													
													<a href="${pageContext.request.contextPath}/post/tag/${tags.tagText}"><span class="glyphicon glyphicon-tag"></span> ${tags.tagText}</a>
												</c:forEach>
											</c:if>
										</div>
										<div class="col-md-4" align="right">
											<span class="glyphicon glyphicon-calendar"></span> <fmt:formatDate value="${posts.postDate}" pattern="dd:MM:YYYY/hh:mm"/>
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