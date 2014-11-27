<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>线索列表</title>
</head>

<body>
<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<table id="clue" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>用户昵称</th>
				<th>内容</th>
				<th>转发数</th>
				<th>评论数</th>
				<th>微博创建时间</th>
				<th>监测开始时间</th>
				<th>转发最后更新</th>
				<th>评论最后更新</th>
				<th>运行状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>用户昵称</th>
				<th>内容</th>
				<th>转发数</th>
				<th>评论数</th>
				<th>微博创建时间</th>
				<th>监测开始时间</th>
				<th>转发最后更新</th>
				<th>评论最后更新</th>
				<th>运行状态</th>
				<th>操作</th>
			</tr>
		</tfoot>
		<tbody>
		<c:forEach items="${clues}" var = "clue">
				<tr>
					<td><a href = "http://weibo.com/u/${clue.weibo.user.id}" target="_blank">${clue.weibo.user.name}</a></td>
					<td>
					<a href = "http://weibo.com/${clue.weibo.user.id}/${clue.weibo.mid}" target="_blank">
						${fn:substring(clue.weibo.text, 0, 10)}..
					</a>

					</td>
					<td>${clue.weibo.repostsCount}</td>
					<td>${clue.weibo.commentsCount}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${clue.weibo.createdAt}" />
					</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${clue.createAt}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${clue.lastUpdateRetweetTime}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            value="${clue.lastUpdateCommentTime}" /></td>
					<td>
					<c:if test="${clue.operateStatus eq 0}">
					<button type="button" class="btn btn-success disabled">运行</button>
					</c:if>
					<c:if test="${clue.operateStatus eq 1}">
					<button type="button" class="btn btn-warning disabled">暂停</button>
					</c:if>					
					</td>
					<td>
					<a href="${ctx}/clue/delete/${clue.id}">删除</a>
					<a href="${ctx}/clue/transfer/${clue.id}">暂停/开始</a>
					</td>
				</tr>
		</c:forEach>

		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#clue').DataTable();
		});
	</script>
</body>
</html>