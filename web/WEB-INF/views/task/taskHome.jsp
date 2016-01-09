<%@ include file="/WEB-INF/template/includes.jsp" %>
<h1>Tasks List</h1>

<misc:messages/>

<div class="searchResults">
	<ul>
		<c:forEach items="${taskList}" var="task">
			<li>
				<a href="<c:url value='/task/view'/>?id=${task.objId}">${fn:escapeXml(task.objName)}</a>
			</li>
		</c:forEach>
		<c:if test="${empty taskList}">
			<li class="noResult">No tasks found.</li>
		</c:if>
	</ul>
</div>