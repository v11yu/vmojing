<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="reqUrl" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<html>
<head>
	<title>Restful API 列表</title>
</head>

<body>

<h3>Restful API 列表</h3>
<h4>标签 API</h4>
<ul>

	<li>获取标签权重(name=tagName) ： <a href="${reqUrl}/api/v1/tag/旅游">${reqUrl}/api/v1/tag/旅游</a></li>
</ul>


</body>
</html>