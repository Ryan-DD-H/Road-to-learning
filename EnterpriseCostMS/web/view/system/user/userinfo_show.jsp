<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-个人信息</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	 $("#f1").validate();
	 

})

function xmTanUploadImg(obj) {
    var file = obj.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function (e) {    //成功读取文件
        var img = document.getElementById("selectImg");
        img.src = e.target.result;   //或 img.src = this.result / e.target == this
    };


/*    var fileObj = document.getElementById("uploadImg").files[0]; // 获取文件对象  
    var FileController = "system/UsersMyUpdateServlet"; // 接收上传文件的后台地址  
    if(fileObj){
        // FormData 对象  
        var form = new FormData();
        form.append("file", fileObj);// 文件对象     

        // XMLHttpRequest 对象  
        var xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);

        xhr.send(form);

    }else{
        alert("未选择文件");
    }*/


}


</script>	
<style type="text/css">
	.error{color: red;}

	#selectImg{
		width: 200px;
		height: 200px;
		border-radius: 100%
	}
	#uploadImg{
		position: absolute;
		overflow: hidden;
		right: 0;
		top: 0;
		opacity: 0;
	}


</style>

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>个人信息</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tips==null?'none':'block'};margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tips}</h4>
    </div>

<%--<form action="" id="f1" method="post" class="form-horizontal"  enctype="multipart/form-data">



</form>--%>


<form action="system/UsersMyUpdateServlet" id="f1" method="post" class="form-horizontal"  enctype="multipart/form-data">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
			<input type="hidden" name="usersId" value="${users.usersId}" />
		<!-- 开始1 -->



	<img src="${users.profileUrl==null?'resource/assets/avatars/user.jpg':users.profileUrl}" alt="" id="selectImg" class="col-xs-offset-5">
	<br/><br/>
	<div class="row ">
		<div class="col-xs-5 col-xs-offset-4">
			<div class="form-group ">
				<label class="col-xs-3 control-label" style="opacity: 0;">修改头像</label>
				<div class="col-xs-9 ">
					<button type="button" class="btn btn-info" style="position: relative;">修改头像
						<input type="file" name="profile" id="uploadImg" onchange="xmTanUploadImg(this)" multiple="multiple" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<br/><br/>






	<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户姓名</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="usersName" value="${users.usersName}" placeholder="请输入用户姓名" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户年龄</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="usersAge" value="${users.usersAge}" placeholder="请输入用户年龄" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<!-- 开始2 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户性别</label>
					<div class="col-xs-3 ">
						<select name="usersSex" class="form-control">
							<option ${users.usersSex=='保密'?'selected':'' } >保密</option>
							<option ${users.usersSex=='男'?'selected':'' }>男</option>
							<option ${users.usersSex=='女'?'selected':'' }>女</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">电话号码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="usersPhone" value="${users.usersPhone }" placeholder="请输入电话号码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束2 -->
		<!-- 开始3 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-4 ">
						 <p class="form-control-static">${users.roleName}</p>
						<input type="hidden" name="roleName" value="${users.roleName}">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户薪资</label>
					<div class="col-xs-9 ">
						 <p class="form-control-static">${users.usersSalary}</p>
					<input type="hidden" name="usersSalary" value="${users.usersSalary}">
					</div>
				</div>
			</div>
		</div>
		<!--结束3 -->

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
		<!-- 开始5 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户账号</label>
					<div class="col-xs-9">
					 <p class="form-control-static">${users.usersAccount}</p>
						<input type="hidden" name="usersAccount" value="${users.usersAccount}">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control"  value="${users.usersPwd}"  required name="usersPwd" placeholder="请输入用户密码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束5 -->

		<div class="row">
			<div class="col-xs-3 col-xs-offset-5">
				<input type="submit" class="btn btn-success" value="修改个人信息" /> 
			</div>
		</div>
	</form>
</body>
</html>