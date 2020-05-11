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

    <title>医生信息</title>

    <link href="${contextPath}/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="${contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min862f.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        		<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-content mailbox-content">
						<div class="file-manager">
							<a class="btn btn-block btn-info compose-mail" href="${contextPath}/page/doctor/doctorMain.jsp"
								>医生守则</a>
							<div class="space-25"></div>
							<ul class="folder-list m-b-md" style="padding: 0">
								<li><a href="${contextPath}/page/doctor/doctorInfo.jsp"> <i class="fa fa-user "></i>
										医生信息
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorYyManage.jsp"> <i class="fa fa-cog"></i>
										预约管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorJzManage.jsp"> <i
										class="fa fa-user-md"></i> 就诊管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/doctorFzManage.jsp"> <i
										class="fa fa-calendar"></i> 复诊管理
								</a></li>
								<li><a href="${contextPath}/page/doctor/departmentInfo.jsp"> <i class="fa fa-hospital-o"></i>
										科室成员
								</a></li>
							</ul>

							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完善个人信息</h5>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">科室</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="department">

                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-9">
                                    <input type="text"  class="form-control" id="tname">${age }
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="position">
                                        <option>住院医师</option>
                                        <option>主治医师</option>
                                        <option>副主任医师</option>
                                        <option>主任医师</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机号</label>

                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="phone">${phone }
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="sex">
                                        <option>男</option>
                                        <option>女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>

                                <div class="col-sm-9">
                                    <select class="form-control" id="age">

                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">邮箱</label>${email }

                                <div class="col-sm-9">
                                    <input type="email"  class="form-control" id="email">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit" style="margin-left: 300px;margin-top: 20px;" onclick="saveDoctorInfo()">保存内容</button>
                                </div>
                            </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${contextPath}/js/jquery.min.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
    <script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
    <script src="${contextPath}/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${contextPath}/js/demo/layer-demo.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

    <script>

    var contextPath="<%=contextPath%>";

    window.onload =function(){
        $.ajax({
            url : contextPath + "/dept/list",
            type : "GET",
            dataType : "text",
            success : function(datas) {
                datas=JSON.parse(datas);
                for (var i = 0; i < datas.length; i++) {
                    $('#department').append(" <option>"+datas[i].deptName+"</option>")
                }
            }
        });




        $.ajax({
				url : contextPath + "/Doctor/queryDoctorInfo",
				type : "POST",
				dataType : "text",
				success : function(datas) {
					console.log(JSON.parse(datas));
					/* 将json字符串转为json对象 */
					datas=JSON.parse(datas);
					for(var i in datas){
						$("#department").val(datas.department);
						$("#position").val(datas.position);
                        $("#tname").val(datas.tname);
                        $("#sex").val(datas.sex);
						$("#phone").val(datas.phone);
						$("#age").val(datas.age);
						$("#email").val(datas.email);
                        for (var i = 18; i < 100; i++) {
                            $('#age').append(" <option>"+i+"</option>")
                        }
					}
				}
			});

    }

    function saveDoctorInfo(){
    		var department=$("#department").val();
    		var position=$("#position").val();
    		var phone=$("#phone").val();
    		var age=$("#age").val();
    		var email=$("#email").val();
            var sex=$("#sex").val();
            var tname=$("#tname").val();
    		$.ajax({
    			url : contextPath + "/Doctor/saveDoctorInfo",
    			type : "POST",
    			data : {"department":department,"position":position,"phone":phone,"age":age,"email":email,"sex":sex,"tname":tname},
    			dataType : "text",
    			success : function(datas) {
    				if(datas>0){
    					parent.layer.msg('保存成功！');
    				}
    			}
    		});
    }

    </script>


</body>
</html>
