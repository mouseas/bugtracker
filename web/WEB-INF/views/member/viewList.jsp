<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Member List</h1>
<div>
	<c:if test="${not empty members}">
		<c:forEach var="member" items="${members}">
			<div>
				<div>
					<a href="/member/${member.username}/">
						${member.fullName}
					</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty members}">
		No members! Join and be the first!
	</c:if>
</div>