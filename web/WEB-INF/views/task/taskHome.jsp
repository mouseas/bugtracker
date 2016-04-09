<%@ include file="/WEB-INF/template/includes.jsp" %>
<h1>Tasks List</h1>

<misc:messages/>

<div class="searchResults">
	<ul>
		<c:forEach items="${taskList.searchResults}" var="task">
			<li>
				<a href="<c:url value='/task/view'/>?id=${task.id}">${fn:escapeXml(task.name)}</a>
			</li>
		</c:forEach>
		<c:if test="${empty taskList.searchResults}">
			<li class="noResult">No tasks found.</li>
		</c:if>
	</ul>
</div>