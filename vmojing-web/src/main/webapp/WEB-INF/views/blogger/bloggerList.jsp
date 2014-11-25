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
				<th>博主Id</th>
				<th>昵称</th>
				<th>内容</th>
				<th>创建时间</th>
				<th>粉丝最后更新</th>
				<th>微博最后更新</th>
				<th>运行状态</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>博主Id</th>
				<th>昵称</th>
				<th>内容</th>
				<th>创建时间</th>
				<th>粉丝最后更新</th>
				<th>微博最后更新</th>
				<th>运行状态</th>
			</tr>
		</tfoot>
		<tbody>
		<c:forEach items="${bloggers}" var = "blogger">
				<tr>
					<td>${blogger.id}</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
					<td>apple</td>
					<td>apple</td>
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