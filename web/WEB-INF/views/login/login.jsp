<%@ include file="/WEB-INF/template/includes.jsp" %>

<misc:messages/>

<form:form id="loginForm" method="post" action="login" modelAttribute="loginForm">
	<form:label path="username">Username</form:label>
	<form:input path="username"/><br/>
	<form:label path="password">Password</form:label>
	<form:password path="password"/><br/>
	<input type="submit" value="Submit"/>
</form:form>
