<%@ include file="/WEB-INF/template/includes.jsp" %>
<h1>${fn:escapeXml(user.fullName)}</h1>

<misc:messages/>

<div class="recordDetails">
	<div class="detail">
		<div class="detailLabel">Username:</div>
		<div class="detailValue">${fn:escapeXml(user.username)}</div>
	</div>
	<div class="detail">
		<div class="detailLabel">Email:</div>
		<div class="detailValue">
			<c:if test="${not empty user.emailAddress}">
				<a href="mailto:${fn:escapeXml(user.emailAddress)}">
					${fn:escapeXml(user.emailAddress)}
				</a>
			</c:if>
		</div>
	</div>
	<div class="detail">
		<div class="detailLabel">Phone:</div>
		<div class="detailValue">${fn:escapeXml(user.phoneNumber)}</div>
	</div>
	<div class="detail">
		<div class="detailLabel">Address:</div>
		<div class="detailValue pre">${fn:escapeXml(user.address)}</div>
	</div>
</div>
