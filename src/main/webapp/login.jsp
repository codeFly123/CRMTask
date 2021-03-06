<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath=request.getScheme()+"://"+
		request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">

		$(function(){
			if(window.top!==window){
				window.top.location=window.location;
			}

			//加载后，清空输入框；
			$("#loginACT").val("");
			//页面加载后，文本框就获得焦点；
			$("#loginACT").focus();
			//$("#loginACT").blur();
			//$("#loginPW").focus();

			//为登录按钮绑定事件；
			$("#submitBtn").click(function(){
				login()

			})

			//回车绑定窗口页面；
			$(window).keydown(function(event){
				if(event.keyCode==13){
					login()
				}
			})

		})

		//普通自定义方法一定要写在外面；
		function login(){
			alert("登录页面操作成功！");
			//将输入框中的内容传到此处；
			//$.trim()去掉两边的空格；
			var loginACT = $.trim($("#loginACT").val());
			var loginPW = $.trim($("#loginPW").val());

			if(loginACT==""||loginPW==""){
				$("#msg").html("账户或密码不能为空！");
				//验证失败，强制中止；
				return false;
			}

			//去后台验证相关信息；
			$.ajax({
				url:"setting/user/login.do",//联通XML文件找到Controller类;
				dataType:"json",
				//contentType:"application/json",
				data:{
					"loginACT":loginACT,
					"loginPW":loginPW

				},
				type:"post",//为了账户与密码安全；
				success:function(data){
					if(data.success){//成功函数
						window.location.href="workbench/index.jsp";//验证成功后继续跳转；

					}else{
						$("#msg").html(data.msg);//失败信息函数；
					}


				}

			})





		}


	</script>

</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
	<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
	<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
		CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
	<div style="position: absolute; top: 0px; right: 60px;">
		<div class="page-header">
			<h1>登录</h1>
		</div>
		<form action="workbench/index.jsp" class="form-horizontal" role="form">
			<div class="form-group form-group-lg">
				<div style="width: 350px;">
					<input class="form-control" type="text" placeholder="用户名"  id="loginACT">
				</div>
				<div style="width: 350px; position: relative;top: 20px;">
					<input class="form-control" type="password" placeholder="密码" id="loginPW">
				</div>
				<div class="checkbox" style="position: relative;top: 30px; left: 10px;">

					<span id="msg" style="color:red"></span>

				</div>
				<button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"
						style="width: 350px; position: relative;top: 45px;">登录
				</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>