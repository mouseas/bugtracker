<%@ include file="/WEB-INF/template/includes.jsp" %>
<div class="menu">
	<ul>
		<li><a href="<c:url value='/'/>">Home</a></li>
		
		<li><a href="<c:url value="/task/"/>">Tasks</a></li>
		
		<c:if test="${empty sessionScope.CURRENT_USER}">
			<li><a href="<c:url value="/login"/>">Login</a>
		</c:if>
		<c:if test="${not empty sessionScope.CURRENT_USER}">
			<li><a href="<c:url value="/logout"/>">Logout</a>
		</c:if>
	</ul>
</div>