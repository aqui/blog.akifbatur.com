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
	
	<body onload='document.signupForm.name.focus();'>

		<jsp:include page="/WEB-INF/templates/navbar.jsp"></jsp:include>

		<div align="center">
			<c:if test="${not empty securityUserName}">
				<div align="center">
					<h4>hello ${securityUserName}?</h4>
				</div>
			</c:if>
			<c:if test="${empty securityUserName}">
				<c:if test="${not empty saveAuthorModel.message}">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<spring:message code="${saveAuthorModel.message}" />
					</div>
				</c:if>
				<form:form action="${pageContext.request.contextPath}/signup"
					role="form" method="POST" name="signupForm" modelAttribute="author">
					<sec:csrfInput />
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">
									<spring:message code="signup" />
								</h4>
							</div>
							<div class="modal-body">
		
								<div class="form-group">
									<div align="center">
										<spring:message code="Name" />
										<form:errors path="name" />
									</div>
									<form:input type="text" path="name" maxlength="30" title="Name" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Username" />
										<form:errors path="userName" />
									</div>
									<form:input type="text" path="userName" maxlength="20"
										title="Username" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Email" />
										<form:errors path="email" />
									</div>
									<form:input type="email" path="email" maxlength="240"
										title="Email" />
								</div>
								<div class="form-group">
									<div align="center">
										<spring:message code="Password" />
										<form:errors path="password" />
									</div>
									<form:input type="password" path="password" maxlength="20"
										title="Password" />
								</div>
							</div>
							<div class="modal-footer ">
								<button class="btn btn-primary btn-block" type="submit"
									name="submit" onclick='document.signupForm.submit();'>
									<spring:message code="signup" />
								</button>
							</div>
						</div>
					</div>
				</form:form>
			</c:if>
		</div>
		
	</body>
</html>