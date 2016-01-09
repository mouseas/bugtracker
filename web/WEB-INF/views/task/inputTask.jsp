<%@ include file="/WEB-INF/template/includes.jsp" %>
<c:set var="isNew" value="${taskForm.id <= 0}"/>
<c:if test="${isNew}">
	<h1>New Task</h1>
</c:if>
<c:if test="${!isNew}">
	<h1>Task ${taskForm.id}: ${fn:escapeXml(taskForm.name)}</h1>
</c:if>

<misc:messages/>

<form:form action="${isNew ? 'insert' : 'update'}" modelAttribute="taskForm" method="post">
	<div class="recordDetails">
		<div class="detail">
			<div class="detailLabel">Task Name:</div>
			<div class="detailValue">
				<form:input path="name" cssClass="longInput"/>
			</div>
		</div>
		<div class="detail">
			<div class="detailLabel">Description:</div>
			<div class="detailValue">
				<form:textarea path="description"/>
			</div>
		</div>
		<%--div class="detail">
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
		</div--%>
	</div>
	<div class="buttons">
		<button type="submit" name="save" class="save">Save</button>
	</div>
	<form:hidden path="id"/>
</form:form>
