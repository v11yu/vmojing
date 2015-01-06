<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Static navbar -->
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">控制台</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${ctx}/topic">话题列表</a></li>
				<li><a href="${ctx}/blogger">博主列表</a></li>
				<li><a href="${ctx}/clue">线索列表</a></li>
				<li><a href="${ctx}/topic/create">创建话题</a></li>
				<li><a href="${ctx}/blogger/create">创建博主</a></li>
				<li><a href="${ctx}/clue/create">创建线索</a></li>
				<li><a href="${ctx}/api">Api接口</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>
