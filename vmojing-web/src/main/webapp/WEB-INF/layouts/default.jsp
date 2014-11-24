<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>vmojing爬虫web控制台:<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/resources/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/styles/default.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/form.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/jqueryui/1.8/themes/base/jquery.ui.core.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/jqueryui/1.8/themes/base/jquery.ui.theme.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/jqueryui/1.8/themes/base/jquery.ui.tabs.css"rel="stylesheet" type="text/css" />
<script src="${ctx}/resources/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/jquery/1.6/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/jqueryform/2.8/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resources/jqueryui/1.8/jquery.ui.core.js"></script>
<script type="text/javascript" src="${ctx}/resources/jqueryui/1.8/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/resources/jqueryui/1.8/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="${ctx}/resources/json2.js"></script>
<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
	
</body>
</html>