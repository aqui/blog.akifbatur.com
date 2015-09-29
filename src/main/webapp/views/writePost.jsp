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

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username"
		var="securityUserName" />
</sec:authorize>

<jsp:include page="/templates/head.jsp"></jsp:include>

<body>

	<jsp:include page="/templates/navbar.jsp"></jsp:include>

	<%-- Body Start --%>
	<div class="container-fluid">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="PostFacility" />
					</h3>
				</div>
				<div class="panel-body">
					<c:if test="${not empty savePostModel.message}">
						<div class="alert alert-danger fade in" align="center">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							${savePostModel.message}
						</div>
					</c:if>
					<form:form action="${pageContext.request.contextPath}/post/write"
						role="form" method="POST" name="postForm" modelAttribute="post">
						<div class="form-group">
							<label for="categoryId.id"><spring:message
									code="Category" /> <form:errors path="categoryId" /></label> <br />
							<form:select path="categoryId.id">
								<form:options path="categoryId.id" items="${categories}"
									itemLabel="categoryTitle" itemValue="id" />
							</form:select>
							<c:if test="${empty categories}">
								<a href="${pageContext.request.contextPath}/category/write"><spring:message
										code="AddCategory" /></a>
							</c:if>
						</div>
						<div class="form-group">
							<label for="postTitle"><spring:message code="Title" /> <form:errors
									path="postTitle" /></label> <br />
							<form:input type="text" path="postTitle" maxlength="20"
								title="Title" />
						</div>
						<div class="form-group">
							<label for="tagField"><spring:message code="Tags" /> </label> <br />
							<input type="text" name="tagField" maxlength="20" title="Tags"
								value="${savePostModel.tagField}" /> <br>(
							<spring:message code="useCommaToSeparateTags" />
							)
						</div>
						<div class="form-group">
							<label for="postBody"><spring:message code="Post" /> <form:errors
									path="postBody" /></label> <br />
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
			</div>
		</div>
	</div>
	<%-- Body End --%>

</body>
</html>