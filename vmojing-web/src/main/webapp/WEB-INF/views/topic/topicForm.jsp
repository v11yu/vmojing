<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>创建话题</title>

</head>
<body>
	<div class="jumbotron">
		<form:form role="form" action="${ctx }/topic/${action}" method="post"
			modelAttribute="topicBean">
			<div class="form-group">
				<label>话题名称</label> 
				<form:input type="text" class="form-control" path="topicName" placeholder="TopicName" />
			</div>
			<div class="form-group">
				<label>更新频率</label> <form:input type="text" class="form-control"
					path="updateFrequency" placeholder="分钟" />
			</div>
			<div class="form-group">
				<label>采集最早时间</label> <form:input type="text" class="form-control"
					path="beginTime" placeholder="yyyy-mm-dd"/>
			</div>
			<div class="form-group">
				<label>话题类型</label> <form:input type="text" class="form-control"
					path="type" placeholder="type"/>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		
		</form:form>
	</div>
</body>
</html>