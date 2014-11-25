<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<th>线索ID</th>
				<th>用户昵称</th>
				<th>内容</th>
				<th>发布时间</th>
				<th>监测时间</th>
				<th>运行状态</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>线索ID</th>
				<th>用户昵称</th>
				<th>内容</th>
				<th>发布时间</th>
				<th>监测时间</th>
				<th>运行状态</th>
			</tr>
		</tfoot>
		<tbody>
		<c:forEach items="${clues}" var = "clue">
				<tr>
					<td>${clue.id}</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
					<td>apple</td>
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