<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/template/includes.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" defaultValue="Bug Tracker"/></title>
	<link rel="stylesheet" type="text/css" href="/resources/css/site.css"/>
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<div class="body">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>