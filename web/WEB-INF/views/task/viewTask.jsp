<%@ include file="/WEB-INF/template/includes.jsp" %>
<h1>Task ${task.id}: ${fn:escapeXml(task.name)}</h1>

<misc:messages/>

<div class="recordDetails">
	<div class="detail">
		<div class="detailLabel">Description:</div>
		<div class="detailValue pre">${fn:escapeXml(task.description)}</div>
	</div>
	<div class="detail">
		<div class="detailLabel">Created By:</div>
		<div class="detailValue">
			<c:if test="${not empty task.lazyLoadedTaskCreator}">
				<a href="<c:url value="/user/view"/>?id=${task.lazyLoadedTaskCreator.objId}">
					${fn:escapeXml(task.lazyLoadedTaskCreator.objName)}
				</a>
			</c:if>
			<c:if test="${empty task.lazyLoadedTaskCreator}">
				<span class="noValue">unknown</span>
			</c:if>
		</div>
	</div>
</div>
