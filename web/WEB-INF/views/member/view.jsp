<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1>Maker: ${member.fullName}</h1>
<div>
	<div>Username: ${member.username != null ? member.username : "<i>none</i>"}</div>
	<div>Id: ${member.id}</div>
	<div>Email: ${member.emailAddress != null ? member.emailAddress : "<i>none</i>"}</div>
	<div>Phone: ${member.phoneNumber}</div>
	<div>Address: ${member.address != null ? member.address : "<i>none</i>"}</div>
	<div class="menu">
		<ul>
			<spring:url value="/member/${member.username}/tools" var="toolsUrl" htmlEscape="true" />
			<li><a href="${toolsUrl}">Tools</a></li>
			<spring:url value="/member/${member.username}/materials" var="materialsUrl" htmlEscape="true" />
			<li><a href="${materialsUrl}">Materials</a></li>
		</ul>
	</div>
</div>