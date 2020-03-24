<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>蔬菜管理系统主界面</title>
		<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/setDateTime.js"></script>
		<script type="text/javascript">
		      function check() {
		      	if(confirm("您确定要退出吗?")){
			        window.location.href = "logout.jsp"
		         }
		      }
        </script>
	</head>

	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="head">
					<div class="headLeft">
						<img src="${pageContext.request.contextPath}/images/logo.png" /> 
		
					</div><br/>
					<div class="headRight">
						欢迎管理员：
						<font color="red">${sessionScope.user.getUsername()}</font>&nbsp;&nbsp;&nbsp;
						 <i class="icon-time"></i>&nbsp;&nbsp;<font id="today"></font>
					</div>
				</div>
			</div>
		</div> 
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
					   <a class="brand" href="main.jsp"><i class="icon-home"></i>&nbsp;首页</a>
						<ul class="nav">
							<li><a href="vegetablesAdd.jsp"><i class="icon-pencil"></i>&nbsp;添加蔬菜信息 </a></li>
							<li><a href="DisplayVegetables"><i class="icon-book"></i>&nbsp;蔬菜信息维护 </a></li>
							<li><a href="updatepwd.jsp"><i class=" icon-cog"></i>&nbsp;修改密码</a></li>
							<li><a style="cursor:pointer" onclick="check()"><i class="icon-user"></i>&nbsp;退出系统</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
