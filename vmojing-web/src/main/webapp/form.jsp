<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>form
</head>
<body>
	<div class="jumbotron">
		<form role="form">
			<div class="form-group">
				<label>话题名称</label> <input type="text" class="form-control"
					id="topicName" placeholder="topicName">
			</div>
			<div class="form-group">
				<label>采集最早时间</label> <input type="text" class="form-control"
					id="beginTime" placeholder="yyyy-mm-dd">
			</div>
			<div class="form-group">
				<label>更新频率</label>
				 <input type="text" class="form-control"
					id="updateFrequency" placeholder="yyyy-mm-dd">
			</div>
			<div class="form-group">
				<label>话题类型</label> <select class="form-control">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>