<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Lumino - Tables</title>

		<link href="/admin/css/bootstrap.min.css" rel="stylesheet">
		<link href="/admin/css/datepicker3.css" rel="stylesheet">
		<link href="/admin/css/bootstrap-table.css" rel="stylesheet">
		<link href="/admin/css/styles.css" rel="stylesheet">
		<link rel="stylesheet" href="/admin/css/public.css">
		<link rel="stylesheet" href="/admin/css/table.css">
		<link rel="stylesheet" href="/admin/css/green-black.css">

		<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
					<ul class="user-menu">
						<li class="dropdown pull-right">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a>
								</li>
								<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a>
								</li>
								<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
								</li>
							</ul>
						</li>
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
				<li class="active"><a href=""><span class="glyphicon glyphicon-th"></span>书籍管理</a></li>
				<li><a href="/admin/getUser/"><span class="glyphicon glyphicon-stats"></span>用户管理</a></li>
				<li><a href="/admin/getActivities/"><span class="glyphicon glyphicon-list-alt"></span>活动管理</a></li>
			</ul>
		</div>
		<!--/.sidebar-->

		<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
			<div class="row">
				<ol class="breadcrumb">
					<li><a href="#"><span class="glyphicon glyphicon-home"></span></a>
					</li>
					<li class="active">书籍</li>
				</ol>
			</div>
			<!--/.row-->

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">
						<span>书籍列表</span>
						<span class='add'><a href="#">新增</a></span>
						</div>
						<!-- Table -->
						<table class="table">
							<thead>
								<tr>
									<th></th>
									<th>图片</th>
									<th width="100">书名</th>
									<th width="100">作者</th>
									<th width="100">类型</th>
									<th width="500">简介</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<#list books as book>
								<tr>
									<td>${book_index + 1}</td>
									<td><img src="/${book.picture?replace('\\' , '/')}?tempid="+Math.random()" alt="图片加载失败" height="80"/></td>
									<td>${book.name}</td>
									<td>${book.author}</td>
									<td>${book.getType().name}</td>
									<td>
										<#if book.descri?length lt 100 > 
											${book.descri}
	                                   <#else>
	                                    	${book.descri[0..100]}...
	                                   </#if>
	                                </td>
									<td>
										<span class='delete'><a href="/admin/delBook/${book.id}-${current}">删除</a></span>
									</td>
								</tr>
							</#list>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--/.row-->
			<!-- 分页 -->
			<div class="green-black">
			<a href="0-1">First Page</a>
			<#if current gt 2>
			...
			</#if>
			<#if current != 1>
			<a href='0-${current-1}'>${current-1}</a>
			</#if>
			<span class="current">${current}</span>
			<#if current lt totalPage>
			<a href='0-${current+1}'>${current+1}</a>
			</#if>
			<#if totalPage gt current+1>
			...
			</#if>
			<#if totalPage gte current>
			<a href='0-${totalPage}'>Last Page</a></div>
			<#else>
			<a href='0-${current}'>Last Page</a></div>
			</#if>
			</div>
			
		<div id="addNewActivityModel" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">活动信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group has-success">
							<form action="">
								<input id="activity-name" class="form-control" placeholder="活动名">
								<input id='activity-describe' class="form-control" placeholder="活动描述">
								<label for="">开始时间：</label>
								<input id='start-date' type="date"/>
								<input id='start-time' type="time"/>
								<label for="">结束时间：</label>
								<input id="end-date" type="date"/>
								<input id="end-time" type="time"/><br />
								<label for="">折扣：</label>
								<input id="promotion-num" type="number"/>
							</form>
						</div>					
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button class="commit-activity btn btn-primary" type="button">提交</button>
					</div>
				</div>
			</div>
		</div>
		<!--/.main-->

		<script src="/admin/js/jquery-1.11.1.min.js"></script>
		<script src="/admin/js/bootstrap.min.js"></script>
		<script src="/admin/js/chart.min.js"></script>
		<script src="/admin/js/chart-data.js"></script>
		<script src="/admin/js/easypiechart.js"></script>
		<script src="/admin/js/easypiechart-data.js"></script>
		<script src="/admin/js/bootstrap-datepicker.js"></script>
		<script src="/admin/js/bootstrap-table.js"></script>
		<script type="text/javascript" src="/js/public/mock.js" ></script>
		<script type="text/javascript" src="/js/mock-data.js" ></script>
		<script type="text/javascript" src="/admin/js/public.js" ></script>
		<script>
			! function($) {
				$(document).on("click", "ul.nav li.parent > a > span.icon", function() {
					$(this).find('em:first').toggleClass("glyphicon-minus");
				});
				$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
			}(window.jQuery);

			$(window).on('resize', function() {
				if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
			})
			 $(window).on('resize', function() {
				if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
			})
		</script>
		<script type="text/javascript" src="/admin/js/table.js"></script>
	</body>

</html>