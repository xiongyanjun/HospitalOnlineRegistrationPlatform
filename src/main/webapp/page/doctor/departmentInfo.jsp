
<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>复诊管理</title>

<link href="${contextPath}/css/bootstrap.min14ed.css" rel="stylesheet">
<link href="${contextPath}/css/font-awesome.min93e3.css"
	rel="stylesheet">
<link href="${contextPath}/css/animate.min.css" rel="stylesheet">
<link href="${contextPath}/css/style.min862f.css" rel="stylesheet">


</head>

<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-content mailbox-content">
						<div class="file-manager">
							<a class="btn btn-block btn-info compose-mail"
								href="${contextPath}/page/doctor/doctorMain.jsp">医生守则</a>
							<div class="space-25"></div>
							<ul class="folder-list m-b-md" style="padding: 0">
								<li><a href="${contextPath}/page/doctor/doctorInfo.jsp">
										<i class="fa fa-user "></i> 医生信息
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorYyManage.jsp">
										<i class="fa fa-cog"></i> 预约管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorJzManage.jsp">
										<i class="fa fa-user-md"></i> 就诊管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorFzManage.jsp">
										<i class="fa fa-calendar"></i> 复诊管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/departmentInfo.jsp">
										<i class="fa fa-hospital-o"></i> 科室成员
								</a></li>
							</ul>

							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div id="departmentTable">
		</div>
	</div>
	</div>
	</div>

	<script src="${contextPath}/js/jquery.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
	<script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
	<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
	<script src="${contextPath}/js/demo/layer-demo.min.js"></script>
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

	<script>

    var contextPath="<%=contextPath%>";

		 $(function() {

			$.ajax({
				url : contextPath + "/Doctor/departmentMembers",
				type : "POST",
				dataType : "text",
				success : function(datas) {
					$("#departmentTable").html(datas);
				}
			});
		});

	</script>

</body>

</html>
