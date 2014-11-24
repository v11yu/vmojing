<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${!ajaxRequest}">
<html>
<head>
<title>topic form</title>
<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
<meta name="decorator" content="no" />
</head>
<body>
</c:if>
<div id="formsContent">
		<h2>Forms</h2>
		<form:form id="form" action="topic/create" method="post" modelAttribute="topicBean" cssClass="cleanform">
		<fieldset>
		<form:label path="updateFrequency">
		  			更新频率 <form:errors path="updateFrequency" cssClass="error" />
		</form:label>
		<form:input path="updateFrequency" />
		<form:label path="beginTime">
		  			采集最早时间： (in form yyyy-mm-dd) <form:errors path="beginTime" cssClass="error" />
		 		</form:label>
		  		<form:input path="beginTime" />
		  		<form:label path="topicName">
		  			话题名称 <form:errors path="topicName" cssClass="error" />
		</form:label>
		<form:input path="topicName" />
		<form:label path="type">
		  			话题类型 <form:errors path="type" cssClass="error" />
		</form:label>
		<form:input path="type" />
		</fieldset>
		<p><button type="submit">Submit</button></p>
		</form:form>
</div>
<script type="text/javascript">
			$(function() {
				$("#form").submit(function() {  
					$.post($(this).attr("action"), $(this).serialize(), function(html) {
						
					});
					return false;  
				});			
			});
		</script>
<c:if test="${!ajaxRequest}">
</body>
</html>
</c:if>