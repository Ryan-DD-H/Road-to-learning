<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询结果页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	
	<script type="text/javascript">
		function del() {
			 if (!confirm("确认要删除？")) {
		            window.event.returnValue = false;
		        }
		}
	</script>

  </head>
  
  <body>

	<%-- 判断是否为登录状态，否则返回登陆界面 --%>>
  <c:if test="${empty sessionScope.user}">
  <c:redirect url="login.jsp">
  </c:redirect>
  </c:if>

  <div class="container">
	  <jsp:include page="common/head.jsp"></jsp:include>
	  
	  <c:if test="${not empty requestScope.ve}">
	  
	  
	  <table  class="table table-hover table-condensed">
		  <tr>
			  <td align="center">
				  <strong>编号</strong>
			  </td>
			  <td align="center">
				  <strong>蔬菜名</strong>
			  </td>
			  <td align="center">
				  <strong>入库日期</strong>
			  </td>
			  <td align="center">
				  <strong>保质期(天)</strong>
			  </td>
			  <td align="center">
				  <strong>类别</strong>
			  </td>
			  <td align="center">
				  <strong>产地</strong>
			  </td>
			    <td align="center">
				  <strong>库存</strong>
			  </td>
			  <td align="center" >
				  <strong>执行操作</strong>
			  </td>
		  </tr>
		  <tr>
			  <td>${requestScope.ve.getVegetablesID()}</td>
			  <td>${requestScope.ve.getName()}</td>
			  <td>${requestScope.ve.getIndate()}</td>
			  <td>${requestScope.ve.getQGPDay()}</td>
			  <td>${requestScope.ve.getCategory()}</td>
			  <td>${requestScope.ve.getAddress()}</td>
			  <td>${requestScope.ve.getStock()}</td>
			  <td>
				  <a href="VegetablesDelOrUpdate?op=del&veId=${requestScope.ve.getVegetablesID()}" class="btn btn-danger" onclick="del()">删除</a>
				  <a href="VegetablesDelOrUpdate?op=update&veId=${requestScope.ve.getVegetablesID()}" class="btn btn-primary">更新</a>
			  </td>
		  </tr>
	  </table>
	  </c:if>

	   <c:if test="${empty requestScope.ve}">
	   <center><font color="red" size="+1">对不起！没有该编号对应的蔬菜....</font></center>
	   </c:if>

	  <hr>
	  <a href="main.jsp" class="btn btn-success">返回主菜单</a>
	  <hr>
  </div>
  </body>
</html>
