<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FReader admin</title>

<link href="/admin/css/bootstrap.min.css" rel="stylesheet">
<link href="/admin/css/datepicker3.css" rel="stylesheet">
<link href="/admin/css/bootstrap-table.css" rel="stylesheet">
<link href="/admin/css/styles.css" rel="stylesheet">
<link rel="stylesheet" href="/admin/css/public.css">
<link rel="stylesheet" href="/admin/css/user.css">
<link rel="stylesheet" href="/admin/css/green-black.css">

<!--[if lt IE 9]>
<script src="/admin/js/html5shiv.js"></script>
<script src="/admin/js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><span
							class="glyphicon glyphicon-user"></span> User <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span>
									Profile</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span>
									Settings</a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
		<!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="/admin/index"><span class="glyphicon glyphicon-dashboard"></span>用户管理</a></li>
			<li><a href="/admin/getBooks/0"><span class="glyphicon glyphicon-th"></span>书籍管理</a></li>
			<li class="active"><a href=""><span class="glyphicon glyphicon-stats"></span>用户管理</a></li>
			<li><a href="/admin/getActivities/"><span class="glyphicon glyphicon-list-alt"></span>活动管理</a></li>
		</ul>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span>
				</a></li>
				<li class="active">用户</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">用户总数：${totalRaw}</div>
					<!-- Table -->
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th>用户名</th>
								<th>密码</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<#list users as user>
							<tr>
							<form action="/admin/updateUser/${current}" role="form">
								<td>${user_index + 1}</td>
								<td><input type="text" style="display: none" value='${user.id}' name="user.id">
								</td>
								<td><img src="/${user.picture?replace('\\' , '/')}?tempid="+Math.random()" alt="图片加载失败" width="60" height="34"/>
								</td>
								<td><input type="text" value='${user.name}' name="user.name"/>
								</td>
								<td><input type="text" value="${user.password}" name="user.password"/>
								</td>
								<td><span class='confirm'><input class="btn btn-primary" type="submit" value="确定">
								</span></td>
							</form>
								<td><span class='delete'><a class="btn btn-primary"
										href="/admin/delUser/${user.id}-${current}">删除</a>
								</span></td> 
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--/.row-->
		
		<div class="green-black">
		<a href="1">First Page</a>
		<#if current gt 2>
		...
		</#if>
		<#if current != 1>
		<a href='${current-1}'>${current-1}</a>
		</#if>
		<span class="current">${current}</span>
		<#if current lt totalPage>
		<a href='${current+1}'>${current+1}</a>
		</#if>
		<#if totalPage gt current+1>
		...
		</#if>
		<#if totalPage gte current>
		<a href='${totalPage}'>Last Page</a></div>
		<#else>
		<a href='${current}'>Last Page</a></div>
		</#if>
		
	<!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script type="text/javascript" src="../js/public/mock.js"></script>
	<script type="text/javascript" src="../js/mock-data.js"></script>
	<script type="text/javascript" src="js/public.js"></script>
	<script>
		!function($) {
			$(document)
					.on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
							});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function() {
			if ($(window).width() > 768)
				$('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function() {
			if ($(window).width() <= 767)
				$('#sidebar-collapse').collapse('hide')
		})
	</script>
	<script type="text/javascript" src="js/user.js"></script>
</body>

</html>