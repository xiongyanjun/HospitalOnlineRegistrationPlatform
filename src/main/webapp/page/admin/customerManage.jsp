
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

    <title>患者信息管理</title>

    <link href="${contextPath}/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min93e3.css"
          rel="stylesheet">
    <link href="${contextPath}/css/plugins/datapicker/datepicker3.css"
          rel="stylesheet">
    <link href="${contextPath}/css/plugins/clockpicker/clockpicker.css"
          rel="stylesheet">
    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min862f.css" rel="stylesheet">
    <link href="${contextPath}/css/plugins/iCheck/custom.css"
          rel="stylesheet">


</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row">
        <div class="col-sm-2">
            <div class="ibox float-e-margins">
                <div class="ibox-content mailbox-content">
                    <div class="file-manager">
                        <a class="btn btn-block btn-info compose-mail"
                           href="${contextPath}/page/admin/adminMain.jsp">管理员守则 </a>
                        <div class="space-25"></div>
                        <ul class="folder-list m-b-md" style="padding: 0">
                            <li><a href="${contextPath}/page/admin/hospitalInfo.jsp">
                                <i class="fa fa-hospital-o"></i> 医院信息
                            </a></li>
                            <li><a href="${contextPath}/page/admin/news.jsp"> <i
                                    class="fa fa-paper-plane"></i> 新闻发布
                            </a></li>
                            <li><a href="/Admin/dept"> <i
                                    class="fa fa-paper-plane"></i> 科室管理
                            </a></li>
                            <li><a href="${contextPath}/page/admin/huanzheManage.jsp">
                                <i class="fa fa-user "></i> 预约记录
                            </a></li>
                            <li><a href="${contextPath}/page/admin/newsManage.jsp">
                                <i class="fa fa-newspaper-o "></i> 新闻管理
                            </a></li>
                            <li><a href="${contextPath}/page/admin/doctorManage.jsp">
                                <i class="glyphicon glyphicon-plus "></i> 医生管理
                            </a></li>
                            <li><a href="${contextPath}/page/admin/customerManage.jsp">
                                <i class="glyphicon glyphicon-user "></i> 患者管理
                            </a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="project-list">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <td class="project-completion">
                                    <div id="data_1">
                                        <div class="input-group date">
													<span class="input-group-addon"><i
                                                            class="fa fa-calendar"></i></span> <input type="text"
                                                                                                      class="form-control" value="" id="choosedate"
                                                                                                      placeholder="请选择日期">
                                        </div>
                                    </div>
                                </td>
                                <td class="project-completion">
                                    <div class="input-group">
                                        <button class="btn btn-primary" type="submit"
                                                style="margin-left: 0px; margin-top: 0px;"
                                                onclick="searchByDt()">查询</button>
                                    </div>
                                </td>
                            </tr>
<%--                            <tr>--%>
<%--                                <td class="project-completion">--%>
<%--                                    <div class="col-sm-4">--%>
<%--                                        <select class="form-control m-b" name="account"--%>
<%--                                                style="margin-left: -20px;;margin-top: 15px;" onchange="isokSelect()"--%>
<%--                                                id="isokSelect">--%>
<%--                                            <option value="" disabled selected>请选择状态</option>--%>
<%--                                            <option value="预约中">预约中</option>--%>
<%--                                            <option value="待确诊">待确诊</option>--%>
<%--                                            <option value="已就诊">已就诊</option>--%>
<%--                                            <option value="已就诊需复诊">已就诊需复诊</option>--%>
<%--                                            <option value="已复诊，确诊完毕">已复诊，确诊完毕</option>--%>
<%--                                        </select>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-sm-4">--%>
<%--                                        <select class="form-control m-b" name="account"--%>
<%--                                                style="margin-left: -20px;;margin-top: 15px;" onchange="departmentSelect()"--%>
<%--                                                id="departmentSelect">--%>
<%--                                            <option value="" disabled selected>请选择科室</option>--%>
<%--                                            <option value="儿科">儿科</option>--%>
<%--                                            <option value="骨科">骨科</option>--%>
<%--                                            <option value="外科">外科</option>--%>
<%--                                            <option value="内科">内科</option>--%>
<%--                                        </select>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <div id="customerTable"></div>
                </div>
            </div>
        </div>
    </div>
    <div id="userModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">修改患者信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="dname">姓名</label>
                            <input type="text" class="form-control" value="" id="dname">
                        </div>

                        <div class="form-group">
                            <label for="dname">登录名</label>
                            <input type="text" class="form-control" value="" id="name">
                        </div>

<%--                        <div class="form-group">--%>
<%--                            <label for="dname">用户类型</label>--%>
<%--                            <select id="roleId" class="form-control">--%>
<%--                                <option value="1">患者</option>--%>
<%--                                <option value="2">医生</option>--%>
<%--                                <option value="3">管理员</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>

                        <div class="form-group">
                            <label for="sex">性别</label>
                            <select class="form-control" id="sex">
                                <option>男</option>
                                <option>女</option>
                            </select>
                        </div>


                        <div class="form-group">
                            <label for="age">年龄</label>
                            <select class="form-control" id="age">

                            </select>
                        </div>

                        <div class="form-group">
                            <label for="phone">电话</label>
                            <input type="text" class="form-control" value="" id="phone">
                        </div>
                        <div class="form-group">
                            <label for="email">电子邮件</label>
                            <input type="text" class="form-control" value="" id="email">
                        </div>

                        <input type="hidden" value="" id="uid">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button onclick="updateUser()" type="button" class="btn btn-primary">确认修改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<script src="${contextPath}/js/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
<script
        src="${contextPath}/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/js/plugins/clockpicker/clockpicker.js"></script>
<script src="${contextPath}/js/plugins/iCheck/icheck.min.js"></script>
<script src="${contextPath}/js/demo/form-advanced-demo.min.js"></script>
<script type="text/javascript"
        src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
<script>

    var contextPath="<%=contextPath%>";

    function customerTable(pageNo){
        $.ajax({
            url : contextPath + "/Admin/customerTable?pageNo="+pageNo,
            type : "get",
            dataType : "text",
            success : function(datas) {
                $("#customerTable").html(datas);

            }
        });
    }

    $(function() {
        for (var i = 1; i < 100; i++) {
            $('#age').append(" <option>"+i+"</option>")
        }
        customerTable();
    });

    function searchByDt() {

        var date = $("#choosedate").val();
        var isok = $("#isokSelect").val();
        var department = $("#departmentSelect").val();

        $.ajax({
            url : contextPath + "/Admin/searchByDt",
            type : "POST",
            data : {
                "date" : date,
                "isok" : isok,
                "department" : department
            },
            dataType : "text",
            success : function(datas) {
                $("#customerTable").html(datas);

            }
        });

    }

    function echoUserModel(id){
        $.ajax({
            url : contextPath + "/Admin/searchUserById",
            type : "POST",
            data : {"id":id},
            dataType : "json",
            success : function(datas) {
                $("#uid").val(datas.id);
                $("#dname").val(datas.tname);
                $("#name").val(datas.name);
                $("#roleId").val(datas.roleId);
                $("#sex").val(datas.sex);
                $("#phone").val(datas.phone);
                $("#email").val(datas.email);
                $("#age").val(datas.age);
            }
        });
    }
    function deleteUser(id){
        layer.confirm('确认删除？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            $.ajax({
                url : contextPath + "/Admin/deleteUserById",
                type : "POST",
                data : {"id":id},
                dataType : "json",
                success : function(datas) {
                    location.reload()
                }
            });
        }, function(){

        });



    }


    function updateUser() {
        $.ajax({
            url : contextPath + "/Admin/SaveCustomerInfo",
            type : "POST",
            data : {"id":$("#uid").val(),
                "age":$("#age").val(),
                "tname":$("#dname").val(),
                "name":$("#name").val(),
                // "roleId":$("#roleId").val(),
                "phone":$("#phone").val(),
                "email":$("#email").val(),
                "sex":$("#sex").val()
            },
            dataType : "json",
            success : function(datas) {
                $('#myModal').modal('hide');
                location.reload()
            }
        });
    }

    function isokSelect() {
        //获取被选中的option标签
        var date = $("#choosedate").val();
        var isok = $("#isokSelect").val();
        var department = $("#departmentSelect").val();


        $.ajax({
            url : contextPath + "/Admin/isokSelect",
            type : "POST",
            data : {
                "date" : date,
                "isok" : isok,
                "department" : department
            },
            dataType : "text",
            success : function(datas) {
                $("#huanzheManageTable").html(datas);

            }
        });
    }

    function departmentSelect() {

        var date = $("#choosedate").val();
        var isok = $("#isokSelect").val();
        var department = $("#departmentSelect").val();


        $.ajax({
            url : contextPath + "/Admin/departmentSelect",
            type : "POST",
            data : {
                "date" : date,
                "isok" : isok,
                "department" : department
            },
            dataType : "text",
            success : function(datas) {
                $("#huanzheManageTable").html(datas);

            }
        });
    }
</script>

</body>

</html>
