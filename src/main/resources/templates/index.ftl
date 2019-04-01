<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
<link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">

			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">

						<form class="form-inline" id="search_from" name="search_from">
							<label class="control-label" for="inputBookName">用户名：</label> <input
								class="form-control" type="text" id="inputBookName" name="name" />

							<button style="margin-top: 5px;" type="button"
								onclick="findBook()" id="search" class="btn btn-warning">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								查询
							</button>
						</form>

					</div>

					<div class="ibox-content">
						<div class="row row-lg">
							<div class="col-sm-12">
								<!-- Example Card View -->
								<div class="example-wrap">
									<div class="example">
										<table class="table table-bordered" id="table_list"></table>
									</div>
								</div>
								<!-- End Example Card View -->
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

	<!-- Bootstrap table -->
	<script
		src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
	<script
		src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

	<!-- Peity -->
	<script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

	<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

	<!-- 自定义js -->
	
	<script type="text/javascript">
		/*
		 * 初始化BootstrapTable,动态从服务器加载数据
		 * */
		$(document)
				.ready(
						function() {

							$("#table_list")
									.bootstrapTable(
											{
												//使用get请求到服务器获取数据
												method : "POST",
												//必须设置，不然request.getParameter获取不到请求参数
												contentType : "application/x-www-form-urlencoded",
												//获取数据的Servlet地址
												url : "${ctx!}/findIndex",
												//表格显示条纹
												striped : true,
												//启动分页
												pagination : true,
												//每页显示的记录数
												pageSize : 10,
												//当前第几页
												pageNumber : 1,
												//记录数可选列表
												pageList : [ 5, 10, 15, 20, 25 ],
												//是否启用查询
												search : false,
												//表示服务端请求
												sidePagination : "server",
												//设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
												//设置为limit可以获取limit, offset, search, sort, order
												queryParamsType : "undefined",
												queryParams : queryParams,
												//json数据解析
												responseHandler : function(res) {
													return {
														"rows" : res.content,
														"total" : res.totalElements
													};
												},
												//数据列
												columns : [
														{
															title : "用户名",
															field : "name",
															align : 'center',
														},
														{
															title : "地址",
															field : "cityName",
															align : 'center'
														},
														{
															title : "手机号",
															field : "phone",
															align : 'center'
														},
														{
															title : "操作",
															field : "reserve",
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																var operateHtml = '<button type="button" class="btn btn-primary btn-sm addbtn" onclick="deleteUser('
																		+ row.id
																		+ ')"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 删除</button>';
																return operateHtml;
															}
														} ]
											});

							$("#confirmTable").on(
									"click",
									"#delete",
									function() {
										var bookID = $(this).parent().parent()
												.attr("id");
										$(this).parent().parent().remove();
										borrowlist.removebooks(bookID);
									});
						});
		/**
		 * 设置额外BootstrapTable请求参数
		 **/
		function queryParams(params) {
			return {
				//表格显示条纹
				striped : true,
				//启动分页
				pagination : true,
				pageSize : params.pageSize,
				//当前第几页
				pageNumber : params.pageNumber, //页码
				//记录数可选列表
				pageList : [ 5, 10, 15, 20, 25 ],
				name : $('#search_from input[name=\'name\']').val(), // 请求时向服务端传递的参数
			}
		}
		/**
		 * 查询用户
		 *
		 */
		function findBook() {
			$('#table_list').bootstrapTable(('refresh')); // 很重要的一步，刷新url！
		}

		/**
		 * 删除
		 * @param id
		 */
		function deleteUser(id) {
			$.ajax({
				type : "get",
				dataType : "json",
				url : "${ctx!}/delete",
				data : {
					id : id
				},
				success : function(data) {
					layer.msg(data.msg);
					$('#table_list').bootstrapTable(('refresh'));
				}
			});
		}
	</script>

</body>

</html>
