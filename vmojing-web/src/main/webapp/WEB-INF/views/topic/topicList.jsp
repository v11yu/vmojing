<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>话题列表</title>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<table id="topic" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>话题名称</th>
				<th>创建时间</th>
				<th>最后更新时间</th>
				<th>话题总数</th>
				<th>运行状态</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>话题名称</th>
				<th>创建时间</th>
				<th>最后更新时间</th>
				<th>话题总数</th>
				<th>运行状态</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${topics}" var="topic">
				<tr>
					<td>${topic.topicName}</td>
					<td>${topic.createAtTime}</td>
					<td>${topic.lastUpdateTime }</td>
					<td>${topic.sum }</td>
					<td>${topic.operateStatus }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#topic').DataTable();
		});
	</script>
</body>
</html>