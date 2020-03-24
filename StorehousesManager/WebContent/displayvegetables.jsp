<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>显示蔬菜信息</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<style type="text/css">
input[type="text"] {
	height: auto;
	margin-bottom: 15px;
	padding: 3px 9px;
}
</style>

<script type="text/javascript">
	function del() {
		if (!confirm("确认要删除？")) {
			window.event.returnValue = false;
		}
	}
</script>
</head>

	<%-- 判断是否为登录状态，否则返回登陆界面 --%>>
  <c:if test="${empty sessionScope.user}">
  <c:redirect url="login.jsp">
  </c:redirect>
  </c:if>
  
<body>
	<div class="container">
		<jsp:include page="common/head.jsp"></jsp:include>
		<form action="CheckVegetablesById" method="post">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 编号：<input
				type="text" name="searchId" placeholder="蔬菜编号...">
			<i class="icon-search"></i>&nbsp;
			<button type="submit" class="btn btn-inverse">查找</button>
		</form>
		<table class="table table-hover table-condensed">
			<tr>
				<td align="center"><strong>蔬菜编号</strong></td>
				<td align="center"><strong>蔬菜名</strong></td>
				<td align="center"><strong>入库日期</strong></td>
				<td align="center"><strong>保质期(天)</strong></td>
				<td align="center"><strong>类别</strong></td>
				<td align="center"><strong>产地</strong></td>
				<td align="center"><strong>库存</strong></td>
				<td align="center"><strong>操作</strong></td>
			</tr>
			
			<c:forEach var="i" begin="0" end="${requestScope.arr.size()-1}">
				<tr>
					<td>${requestScope.arr[i].getVegetablesID()}</td>
					<td>${requestScope.arr[i].getName()}</td>
					<td>${requestScope.arr[i].getIndate()}</td>
					<td>${requestScope.arr[i].getQGPDay()}</td>
					<td>${requestScope.arr[i].getCategory()}</td>
					<td>${requestScope.arr[i].getAddress()}</td>
					<td>${requestScope.arr[i].getStock()}</td>
					<td><a href="VegetablesDelOrUpdate?op=del&veId=${requestScope.arr[i].getVegetablesID()}" class="btn btn-danger"
						onclick="del()">删除</a> <a href="VegetablesDelOrUpdate?op=update&veId=${requestScope.arr[i].getVegetablesID()}"
						class="btn btn-primary">更新</a></td>
				</tr>
			</c:forEach>
		</table>

		<hr>
		<a href="main.jsp" class="btn btn-success">返回主菜单</a>
		<hr>
	</div>



</body>
</html>
