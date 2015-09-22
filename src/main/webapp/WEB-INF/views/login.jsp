<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
	
	<body onload='document.loginForm.userName.focus();'>
		
		<jsp:include page="/WEB-INF/templates/navbar.jsp"></jsp:include>
		
		<c:if test="${not empty securityUserName}">
			<div align="center">
				<h4>hello ${securityUserName}?</h4>
			</div>
		</c:if>
		<c:if test="${empty securityUserName}">
			<div align="center">
				<c:if test="${not empty loginModel.message}">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<spring:message code="${loginModel.message}" />
					</div>
				</c:if>
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">
								<spring:message code="login" />
							</h4>
						</div>
						<div class="modal-body">
							<form action="${pageContext.request.contextPath}/login" role="form" method="POST" name="loginForm">
								<sec:csrfInput />
								<div class="form-group">
									<input type="text" placeholder="<spring:message code="Username"/>" name="userName" required="required" maxlength="20">
								</div>
								<div class="form-group">
									<input type="password" placeholder="<spring:message code="Password"/>" name="password" required="required" maxlength="20">
								</div>
							</form>
						</div>
						<div class="modal-footer ">
							<button class="btn btn-primary btn-block" type="submit" name="submit" onclick='document.loginForm.submit();'>
								<spring:message code="login" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		
	</body>
</html>