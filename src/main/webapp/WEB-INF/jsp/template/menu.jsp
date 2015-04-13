<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/" var="helloUrl" />
<spring:url value="/about" var="aboutUrl" />
<spring:url value="/persons/list" var="personListUrl"  />
<spring:url value="/users/list" var="userListUrl"  />
<spring:url value="/email" var="emailUrl"  />

<%-- c:set var="RacinePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/--%>
 
<div class="menu">

	<sec:authorize access="isAuthenticated()">
		<div class="text">
			<p>
				<c:set var="username"><sec:authentication property="principal.username" /></c:set>
				<spring:message code="welcome" arguments="${username}" /> 
			</p>
		</div>
	</sec:authorize>

	<ul>
		<li ${fn:contains(pageContext.request.requestURI, 'about') ? 'class="selected"' : ''}>
			<a href="${aboutUrl}"><spring:message code="menu.about" /></a>
		</li>
		<li ${fn:contains(pageContext.request.requestURI, 'hello') ? 'class="selected"' : ''}>
			<a href="${helloUrl}"><spring:message code="menu.hello" /></a>
		</li>
		<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
			<li ${fn:contains(pageContext.request.requestURI, 'persons') ? 'class="selected"' : ''}>
				<a href="${personListUrl}"><spring:message code="menu.persons" /></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li ${fn:contains(pageContext.request.requestURI, 'users') ? 'class="selected"' : ''}>
				<a href="${userListUrl}"><spring:message code="menu.users" /></a>
			</li>
			<li ${fn:contains(pageContext.request.requestURI, 'email') ? 'class="selected"' : ''}>
				<a href="${emailUrl}"><spring:message code="menu.email" /></a>
			</li>
		</sec:authorize>
	</ul>
</div> 
