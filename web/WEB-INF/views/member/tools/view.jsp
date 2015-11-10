<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1><a href="/member/${member.username}/">${member.fullName}</a>'s Tools</h1>
<div>
	<c:if test="${not empty member.tools}">
		<c:forEach var="tool" items="${member.tools}">
			<div>
				<div><h3>${tool.name}</h3></div>
				<div><p>${tool.description}</p></div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty member.tools}">
		<div>I ain't got no tools.</div>
	</c:if>
</div>