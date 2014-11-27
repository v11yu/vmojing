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
				<th>操作</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>话题名称</th>
				<th>创建时间</th>
				<th>最后更新时间</th>
				<th>话题总数</th>
				<th>运行状态</th>
				<th>操作</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${topics}" var="topic">
				<tr>
					<td>${topic.topicName}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${topic.createAtTime}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${topic.lastUpdateTime}" /></td>
					<td>${topic.sum }</td>
					<td>
					<c:if test="${topic.operateStatus eq 0}">
					<button type="button" class="btn btn-success disabled">运行</button>
					</c:if>
					<c:if test="${topic.operateStatus eq 1}">
					<button type="button" class="btn btn-warning disabled">暂停</button>
					</c:if>					
					</td>
					<td>
					<a href="${ctx}/topic/delete/${topic.id}">删除</a>
					<a href="${ctx}/topic/transfer/${topic.id}">暂停/开始</a>
					</td>
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