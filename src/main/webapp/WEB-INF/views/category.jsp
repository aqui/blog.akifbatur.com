<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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