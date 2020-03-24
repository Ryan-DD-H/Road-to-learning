<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加蔬菜</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
span {
	color: red;
}


body {
	margin: 0;
	padding: 0;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 550px;
	padding: 19px 29px 29px;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

table { /* max-width: 100%; */
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
}

.table {
	width: 40%;
	margin-bottom: 20px;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.labelError {
	line-height: 9px;
	font-size: 10pt;
	color: #f40000;
	border: 1px #ffb8b8 solid;
	padding: 8px 8px 8px 35px;
	background: url(/Shopping/image/error.png) no-repeat;
	background-color: #fef2f2;
}
</style>
	</head>

	<%-- 判断是否为登录状态，否则返回登陆界面 --%>>
  <c:if test="${empty sessionScope.user}">
  <c:redirect url="login.jsp">
  </c:redirect>
  </c:if>
  
	
	<body>
	<div class="container">
		<jsp:include page="common/head.jsp"></jsp:include>
	</div>
		<form class="form-signin" action="VegetablesAdd" method="post">
			<table >
				<tr>
					<td>
						蔬菜名：
					</td>
					<td>
						<input class="input" type="text" name="name" value="${name}" onblur="checkstName()"
							onFocus="clearstName()">
						<span id="Name"></span>
					</td>
				</tr>
				<tr>
					<td>
						入库日期：
					</td>
					<td>
						<input type="date" name="indate" value="${indate}" style="height: auto">
						<span id="indate"></span>
					</td>
				</tr>
				<tr>
					<td>
						保质期(天)：
					</td>
					<td>
						<input class="input" type="text" name="QGPDay" value="${QGPDay}">
						<span id="QGPDay"></span>
					</td>
				</tr>
				<tr>
					<td>
						类别：
					</td>
					<td>
						<input class="input" type="text" name="category" value="${category}">
						<span id="category"></span>
					</td>
				</tr>
				<tr>
					<td>
						产地：
					</td>
					<td>
						<input class="input" type="text" name="address" value="${address}">
						<span id="Address"></span>
					</td>
				</tr>
				<tr>
					<td>
						库存：
					</td>
					<td>
						<input class="input" type="text" name="stock" value="${stock}">
						<span id="stock"></span>
					</td>
				</tr>

			</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="提交" class="btn btn-primary">
			&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="window.location.href='main.jsp'"
				value="返回" class="btn btn-success">
		</form>

	</body>
</html>
