<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<title>登录</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${ctx!}/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
<link href="${ctx!}/assets/css/style.css" rel="stylesheet">
<link href="${ctx!}/assets/css/login.css" rel="stylesheet">
<style>
button {
	display: table width: auto margin-left: auto margin-right: auto
}
</style>

<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>
</head>

<body class="signin">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-12">
				<#if message?exists>
				<div class="alert alert-danger">${message!}</div>
				</#if>
				<form method="post" action="${ctx!}/login" id="frm">
					<h4 class="no-margins">登录：</h4>
					<input type="text" class="form-control uname" name="name" id="name"
						placeholder="用户名" /> <input type="password"
						class="form-control pword m-b" name="password" id="password"
						placeholder="密码" /><br>
					<button class="btn btn-success block full-width">登录</button>
									</form>
			</div>
		</div>
	</div>




	<!-- 全局js -->
	<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

	<!-- 自定义js -->
	<script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

	<!-- jQuery Validation plugin javascript-->
	<script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			$("#frm").validate({
				rules : {
					name : {
						required : true,
						minlength : 5
					},
					password : {
						required : true,
						minlength : 6
					}

				},
				messages : {
					name : {
						required : "请输入用户码",
						minlength : "用户名必需由五个以上字符组成"
					},
					password : {
						required : "请输入密码",
						minlength : "密码长度不能小于6个字符"
					}
				},
				submitHandler : function(form) {
					console.log(form)
					$.ajax({
						type : "POST",
						dataType : "json",
						url : "${ctx!}/login",
						data : $(form).serialize(),
						success : function(data) {
							if (data.code == 0) {
								layer.msg(data.msg);
							} else {
								location = "${ctx}/showIndex";
							}
						}
					});
					//form.submit();
				}
			});
		});
		//首页注册
		function register() {
			layer.open({
				type : 2,
				title : "用户注册",
				area : [ '893px', '600px' ],
				shadeClose : true,
				shade : false,
				content : '${ctx!}/showRegister',
			});
		}
	</script>
</body>

</html>
