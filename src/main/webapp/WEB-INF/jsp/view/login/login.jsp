<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div style="padding: 60px 10px 0;">
			<div class="title">
				<h3>
					<spring:message code="login" />
				</h3>
			</div>
			<div class="section">
				<c:choose>
					<c:when test="${not empty errorCode and errorMessage ne 'null'}">
						<div id="main-message">
							<div id="form-container">
								<div id="error">
									<spring:message code="${errorCode}" /> : <spring:message code="${errorMessage}" />
								</div>
							</div>
						</div>
					</c:when>
				</c:choose>				
			
				<form name="loginForm" action="<c:url value='perform-login' />" method='POST'>
					<div class="login">
						<ul>
							<li class="huge">
								<label path="username"><strong><spring:message code="username" /></strong></label> 
								<input path="username" name="username" value="admin@email.com"/>
							</li>
							<li class="huge">
								<label path="password"><strong><spring:message code="password" /></strong></label> 
								<input path="password" name="password" type="password" value="password"/>
							</li>
						</ul>
					</div>				
					<ul>
						<li class="huge-btn">
							<button type="submit" name="submit"><spring:message code="login" /></button>
						</li>
					</ul>				
				</form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>