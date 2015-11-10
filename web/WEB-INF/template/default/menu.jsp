<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="menu">
	<ul>
		<li><spring:url value="/member/" var="memberUrl" htmlEscape="true" />
			<a href="${memberUrl}">Members</a></li>
		<li><spring:url value="/home" var="testUrl" htmlEscape="true" />
			<a href="${testUrl}">Test Page</a></li>
	</ul>
</div>