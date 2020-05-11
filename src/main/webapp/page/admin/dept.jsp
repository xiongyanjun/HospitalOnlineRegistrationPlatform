
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>科室信息管理</title>

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
                        <h2>
                            科室管理
                        </h2>
                        <button class="btn-primary btn-lg" data-toggle="modal" onclick="gen()" data-target="#deptModal">新增科室</button>
                    </div>
                    <div>
                        <table class="table-bordered table table-hover table-condensed text-center table-mail">
                            <tbody>
                            <tr class="unread">
                                <td>#</td>
                                <td>科室名称</td>
                                <td>简介</td>
                                <td>时间</td>
                                <td>楼层</td>
                                <td>修改</td>
                                <td>删除</td>
                            </tr>
                            <c:forEach items="${tabledata}" var="list">
                                <tr class="read">
                                    <td>${list['id'] }</td>
                                    <td>${list['deptName'] }</td>
                                    <td>${list['info'] }</td>
                                    <td>${list['date'] }个月</td>
                                    <td>${list['floor'] }楼</td>
                                    <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#deptModal" onclick="echodeptModel('${list['id'] }')">修改</button></td>
                                    <td><button type="button" class="btn btn-danger" onclick="deletedept(' ${list['id'] } ')">删除</button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div id="deptModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">修改科室信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="deptName">科室名称</label>
                            <input type="text" class="form-control" value="" id="deptName">
                        </div>
                        <input id="id" type="hidden" value="0">
                        <div class="form-group">
                            <label for="info">科室简介</label>
                            <textarea class="form-control" rows="3" id="info"></textarea>
<%--                            <input type="text" class="form-control" value="" id="name">--%>
                        </div>

                        <div class="form-group">
                            <label for="date">时间</label>
                            <select class="form-control" id="date">

                            </select>
<%--                            <input type="text" class="form-control" value="" id="date">--%>
                        </div>

                        <div class="form-group">
                            <label for="floor">楼层</label>
                            <select class="form-control" id="floor">

                            </select>
<%--                            <input type="text" class="form-control" value="" id="floor">--%>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button onclick="updateDept()" type="button" class="btn btn-primary">确认修改</button>
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
    var contextPath="<%=contextPath%>"
    function gen(){
        for (var i = 1; i < 100; i++) {
            $('#date').append(" <option>"+i+"</option>")
        }
        for (var i = 1; i < 30; i++) {
            $('#floor').append(" <option>"+i+"</option>")
        }
    }

function echodeptModel(id) {
    $.ajax({
        url : contextPath + "/Admin/getDeptById?id="+id,
        type : "GET",
        data : {},
        dataType : "json",
        success : function(datas) {
            gen()
            $("#id").val(datas.id);
            $("#deptName").val(datas.deptName);
            $("#info").val(datas.info);
            $("#date").val(datas.date);
            $("#floor").val(datas.floor);
        }
    });
}

function updateDept() {
    $.ajax({
        url : contextPath + "/Admin/updateDept",
        type : "POST",
        data : {"id":$("#id").val(),
                "deptName":$("#deptName").val(),
                "info":$("#info").val(),
                "date":$("#date").val(),
                "floor":$("#floor").val()
        },
        dataType : "json",
        success : function(datas) {
            location.reload();
        }
    });
}

function deletedept(id) {
    layer.confirm('确认删除？', {
        btn: ['确认','取消'], //按钮
        shade: false //不显示遮罩
    }, function(){
        $.ajax({
            url : contextPath + "/Admin/delDept?id="+id,
            type : "POST",
            data : {
            },
            dataType : "json",
            success : function(datas) {
                location.reload();
            }
        });
    }, function(){

    });


}

</script>

</body>

</html>
