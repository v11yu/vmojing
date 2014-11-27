<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>博主列表</title>
</head>

<body>
<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<table id="blogger" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>博主昵称</th>
				<th>微博数</th>
				<th>粉丝数</th>
				<th>开始时间</th>
				<th>粉丝最后更新</th>
				<th>微博最后更新</th>
				<th>运行状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>博主昵称</th>
				<th>微博数</th>
				<th>粉丝数</th>
				<th>开始时间</th>
				<th>粉丝最后更新</th>
				<th>微博最后更新</th>
				<th>运行状态</th>
				<th>操作</th>
			</tr>
		</tfoot>
		<tbody>
		<c:forEach items="${bloggers}" var = "blogger">
				<tr>
					<td>
					<a href = "http://weibo.com/u/${blogger.user.id}" target="_blank">${blogger.user.name}</a>
					</td>
					<td>${blogger.user.statusesCount}</td>
					<td>${blogger.user.followersCount}</td>
					<td>
					<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${blogger.createAt}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${blogger.lastUpdateFansTime}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${blogger.lastUpdateWeiboTime}" /></td>
					<td>
					<c:if test="${blogger.operateStatus eq 0}">
					<button type="button" class="btn btn-success disabled">运行</button>
					</c:if>
					<c:if test="${blogger.operateStatus eq 1}">
					<button type="button" class="btn btn-warning disabled">暂停</button>
					</c:if>					
					</td>
					<td>
					<a href="${ctx}/blogger/delete/${blogger.id}">删除</a>
					<a href="${ctx}/blogger/transfer/${blogger.id}">暂停/开始</a>
					</td>
				</tr>
		</c:forEach>

		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#blogger').DataTable();
		});
	</script>
</body>
</html>