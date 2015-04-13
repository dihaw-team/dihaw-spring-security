<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
	<div id="home-title">
		<a href="<spring:url value="/"></spring:url>">
			<h3><spring:message code="application.name"/></h3>
		</a>
	</div>
	<div class="user-area">
		<sec:authorize access="isAnonymous()">
				<a href="<spring:url value="/login"></spring:url>" >
					<img class="action-login" src='<c:url value="/static/images/action/login.png" />'>
				</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
				<a href="<spring:url value="/logout"></spring:url>" >
					<img class="action-logout" src='<c:url value="/static/images/action/logout.png" />'>
				</a>
		</sec:authorize>
	</div>
	<hr>
</div>
