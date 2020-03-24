<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% 
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="common/default.jsp";
	}
%>

<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>蔬菜仓库系统主界面</title>
		<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
	</head>

	<%-- 判断是否为登录状态，否则返回登陆界面 --%>>
  <c:if test="${empty sessionScope.user}">
  <c:redirect url="login.jsp">
  </c:redirect>
  </c:if>
  
	<body>
      <div class="container">
	         <jsp:include page="common/head.jsp"></jsp:include>
	         <jsp:include page="<%=mainPage %>"></jsp:include>
      </div>
	</body>
</html>
