<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>table</title>

	

	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/datatables/css/jquery.dataTables.min.css">
</head>

<body>
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
			
				<tr>
					<td>1233</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
				</tr>
				<tr>
					<td>1233</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
				</tr>
				<tr>
					<td>1233</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
				</tr>
				<tr>
					<td>1233</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
				</tr>
				<tr>
					<td>1233</td>
					<td>5656564</td>
					<td>13232</td>
					<td>no1</td>
					<td>apple</td>
				</tr>

		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#topic').DataTable();
		});
	</script>
</body>
</html>