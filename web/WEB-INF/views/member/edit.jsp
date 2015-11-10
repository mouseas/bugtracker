<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h1>Maker: ${member.fullName}</h1>
<form:form>
	<div>
		<div>Username: ${member.username != null ? member.username : "<i>none</i>"}</div>
		<div>Id: ${member.id}</div>
		<div>Email: ${member.emailAddress != null ? member.emailAddress : "<i>none</i>"}</div>
		<div>Phone: ${member.phoneNumber}</div>
		<div>Address: ${member.address != null ? member.address : "<i>none</i>"}</div>
	</div>
</form:form>