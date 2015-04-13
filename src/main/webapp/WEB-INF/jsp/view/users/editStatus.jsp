<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="timePattern" var="timePattern" />
<spring:message code="datePattern" var="datePattern" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div style="padding: 50px 0 0;">
			<div class="title">
				<h3><spring:message code="user.edit.status" /></h3>
			</div>
			<div class="section" style="width: 80%;">
				<ul>
					<li class="huge">
						<strong><spring:message code="email" /></strong>
						<em>${user.email}</em>
					</li>
					<li class="huge">
						<strong><spring:message code="firstName" /></strong>
						<em>${user.firstName}</em>
					</li>
					<li class="huge">
						<c:if test="${user.accountNonExpired eq 1}"><div id="user-connect" >
							<a href="changeAccountExpired?id=${user.id}&value=${user.accountNonExpired}" >
								<span><spring:message code="changeAccountExpired" /></span>
							</a>
						</div></c:if>
						<c:if test="${user.accountNonExpired eq 0}"><div id="user-block" >
							<a href="changeAccountExpired?id=${user.id}&value=${user.accountNonExpired}" >
								<span><spring:message code="changeAccountExpired" /></span>
							</a>
						</div></c:if>
						
						<c:if test="${user.accountNonLocked eq 1}"><div id="user-connect" >
							<a href="changeAccountLocked?id=${user.id}&value=${user.accountNonLocked}" >
								<span><spring:message code="changeAccountLocked" /></span>
							</a>
						</div></c:if>
						<c:if test="${user.accountNonLocked eq 0}"><div id="user-block" >
							<a href="changeAccountLocked?id=${user.id}&value=${user.accountNonLocked}" >
								<span><spring:message code="changeAccountLocked" /></span>
							</a>
						</div></c:if>
						
						<c:if test="${user.credentialsNonExpired eq 1}"><div id="user-connect" >
							<a href="changeCredentialsExpired?id=${user.id}&value=${user.credentialsNonExpired}" >
								<span><spring:message code="changeCredentialsExpired" /></span>
							</a>
						</div></c:if>	
						<c:if test="${user.credentialsNonExpired eq 0}"><div id="user-block" >
							<a href="changeCredentialsExpired?id=${user.id}&value=${user.credentialsNonExpired}" >
								<span><spring:message code="changeCredentialsExpired" /></span>
							</a>
						</div></c:if>							
					</li>
				</ul>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>