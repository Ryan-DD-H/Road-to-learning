<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>费用管理-费用添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {

		$("#f1").validate();

		//给下拉菜单绑定事件
		$("#userId").change(function(){
			var userId=$("#userId").val();
			//查询用户信息
			$.ajax({
				url:"finance/SalaryQueryServlet",
				type:"post",
				dataType:"json",
				data:{
					usersId:userId
				},
				success:function(resp){
					$("#salaryBasic").val(resp.usersSalary);
				}
			});
		});
	})
</script>
<style type="text/css">
	.error {
		color: red;
	}
	.black-font{
		color: black;
	}
</style>

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>财务管理</li>
			<li>薪资发放</li>

		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert"
		style="display:${tips==null?'none':'block' };margin-bottom: 0px;">
		<h4 align="center" style="color: red">${tips }</h4>
	</div>
	<form action="finance/SalaryAddServlet" id="f1" method="post"
		class="form-horizontal">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">发放月份</label>
					<div class="col-xs-3 ">
						<input type="text" class="form-control" required  name="salaryMonth" value='<fmt:formatDate value="${salary.salaryMonth }" pattern="yyyy-MM"/>' onClick="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="请输入发放月份" />
					</div>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">领取人</label>
					<div class="col-xs-3 ">
						<select class="form-control black-font" required name="usersId" id="userId">
							<option value="">请选择</option>
							<c:forEach items="${usersList}" var="user">
								<option value="${user.usersId}">${user.usersName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">底薪</label>
					<div class="col-xs-3 ">
						<input type="text" class="form-control black-font" readonly="readonly"
							required name="salaryBasic" id="salaryBasic" value="${salary.salaryBasic}"
							placeholder="请输入底薪" />
					</div>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">提成</label>
					<div class="col-xs-3 ">
						<input type="text" class="form-control" required
							name="salaryComm" value="0"
							placeholder="请输入提成"  />
					</div>
				</div>
			</div>

		</div>
		<!--结束1 -->



		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="保存" />
			</div>

		</div>

	</form>


</body>
</html>