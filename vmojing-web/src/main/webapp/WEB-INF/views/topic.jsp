<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>topic</title>
<link href="<c:url value="/resources/form.css" />" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.core.css" />"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.theme.css" />"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.tabs.css" />"
	rel="stylesheet" type="text/css" />


</head>
<body>

	<div id="tabs">
		<ul>
			<li><a href="#simple">simple</a></li>
			<li><a href="#clue">clue</a></li>
			<li><a href="<c:url value="/"/>" title="home">home</a>
		</ul>
		<div id="simple">
			<ul>
				<li><a id="simpleLink" class="textLink"
					href="<c:url value="/topic/hello" />">hello</a></li>
			</ul>
		</div>
		<div id="clue"></div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/jqueryform/2.8/jquery.form.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/jqueryui/1.8/jquery.ui.core.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/jqueryui/1.8/jquery.ui.widget.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/jqueryui/1.8/jquery.ui.tabs.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/json2.js" />"></script>

	<script type="text/javascript">
	 $(function() {
		    $( "#tabs" ).tabs();
		    $("a.textLink").click(function(){
				var link = $(this);
				$.ajax({ url: link.attr("href"), dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, link); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, link); }});
				return false;
			});
		  });
	 </script>

</body>
</html>