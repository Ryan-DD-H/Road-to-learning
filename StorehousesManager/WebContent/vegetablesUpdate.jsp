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

		<title>更新蔬菜信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	  span{color:red;}
	  .form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}
.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
.form-signin {
	max-width: 550px;
	padding: 19px 29px 29px;
	margin:0 auto;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
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
		<form class="form-signin" action="VegetablesUpdate" method="post">
			蔬菜编号:
			<input type="text" name="vegetablesID" value="${requestScope.ve.getVegetablesID()}" readonly="readonly"  class="input">
			<br>
			蔬菜名：
			<input class="input" type="text" name="name" value="${requestScope.ve.getName()}"
			onblur="checkstName()" onFocus="clearstName()" >
			<span id="name"></span>
			<br>
			入库日期：
			<input type="date" name="indate" value="${requestScope.ve.getIndate()}" style="height: auto">
			<span id="indate"></span>
			<br>
			保质期(天)：
			<input class="input" type="text" name="QGPDay" value="${requestScope.ve.getQGPDay()}">
			<span id="QGPDay"></span>
			<br>
			类别：
			<input class="input" type="text" name="category" value="${requestScope.ve.getCategory()}">
			<span id="category"></span>
			<br>
			产地：
			<input class="input" type="text" name="address" value="${requestScope.ve.getAddress()}">
			<span id="address"></span>
			<br>
			库存：
			<input class="input" type="text" name="stock" value="${requestScope.ve.getStock()}">
			<span id="stock"></span>
			<br>
			<input type="submit" value="提交" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-success" onclick="window.location.href='DisplayVegetables'"  value="返回">
		</form>
	</body>
</html>
