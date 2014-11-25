<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>创建线索</title>
</head>
<body>
	<div class="jumbotron">
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
		<form role="form" action="${ctx }/clue/${action}" method="post">
			<div class="form-group">
				<label>线索URL</label> 
				<input type="text" class="form-control" name = "url" placeholder="URL">
			</div>

			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>