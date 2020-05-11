
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


    <title>医生管理</title>

    <link href="${contextPath}/css/bootstrap.min14ed.css" rel="stylesheet">
    <link href="${contextPath}/css/font-awesome.min93e3.css"
          rel="stylesheet">
    <link href="${contextPath}/css/plugins/iCheck/custom.css"
          rel="stylesheet">
    <link href="${contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.min862f.css" rel="stylesheet">
    <link href="${contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
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
        <div class="col-sm-9 animated fadeInRight">
            <div class="mail-box-header">

                <form class="pull-right mail-search">
                    <div class="input-group">
                        <input type="text" class="form-control input-sm" name="search" placeholder="根据科室准确查询" id="department">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-sm btn-primary" onclick="searchDoctor()">
                                搜索
                            </button>
                        </div>
                    </div>
                </form>
                <h2>
                    医生管理
                </h2>
                <div class="mail-tools tooltip-demo m-t-md">
                    <button class="btn-primary btn-lg" data-toggle="modal" data-target="#addDocModal">新增医生</button>
                    <div class="btn-group pull-right">
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i>
                        </button>
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i>
                        </button>

                    </div>
                </div>
            </div>
            <div id="doctorTable">
            </div>
        </div>
    </div>
    <div id="addDocModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="addDoc">医生信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="name">登录名</label>
                            <input type="text" class="form-control" value="" id="name">
                        </div>
                        <div class="form-group">
                            <label for="name">姓名</label>
                            <input type="text" class="form-control" value="" id="tname">
                        </div>
                        <div class="form-group">
                            <label for="pw">密码</label>
                            <input type="password" class="form-control" value="" id="pw">
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button onclick="addDoc()" type="button" class="btn btn-primary">确认新增</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">医生信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="keshi">科室</label>
                            <select class="form-control" id="keshi">

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="dname">姓名</label>
                            <input type="text" class="form-control" value="" id="dname">
                        </div>
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
                        <div class="form-group">
                            <label for="summary">描述</label>
                            <textarea class="form-control" rows="3" id="summary"></textarea>
<%--                            <input type="text" class="form-control" value="" id="summary">--%>
                        </div>


                        <div class="form-group">
                            <label for="pos">职位</label>
                            <select class="form-control" id="pos">
                                <option>住院医师</option>
                                <option>主治医师</option>
                                <option>副主任医师</option>
                                <option>主任医师</option>
                            </select>
                        </div>
                        <input type="hidden" value="" id="jnumber">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button onclick="updateDoctor()" type="button" class="btn btn-primary">确认修改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<script src="${contextPath}/js/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/content.min.js?v=1.0.0"></script>
<script src="${contextPath}/js/plugins/iCheck/icheck.min.js"></script>
<script src="${contextPath}/js/plugins/summernote/summernote.min.js"></script>
<script src="${contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        $(".i-checks").iCheck({--%>
<%--            checkboxClass : "icheckbox_square-green",--%>
<%--            radioClass : "iradio_square-green",--%>
<%--        })--%>
<%--    });--%>
<%--</script>--%>
<script type="text/javascript"
        src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script src="${contextPath}/js/plugins/layer/layer.min.js"></script>
<script>



    var contextPath="<%=contextPath%>";

    function showPage(pageNo){
        $.ajax({
            url : contextPath + "/Admin/showPage?pageNo="+pageNo,
            type : "get",
            dataType : "text",
            success : function(datas) {
                $("#doctorTable").html(datas);
                for (var i = 18; i < 100; i++) {
                    $('#age').append(" <option>"+i+"</option>")
                }
            }
        });
    }

    $(function(){
        showPage()
        $.ajax({
            url : contextPath + "/dept/list",
            type : "GET",
            dataType : "text",
            success : function(datas) {
                datas=JSON.parse(datas);
                for (var i = 0; i < datas.length; i++) {
                    $('#keshi').append(" <option>"+datas[i].deptName+"</option>")
                }
            }
        });
    });

    function addDoctor() {
        updateDoctor()
        console.log("addDoctor")
    }
    function updateDoctor() {
        $.ajax({
            url : contextPath + "/Admin/SaveDoctorInfo",
            type : "POST",
            data : {"jnumber":$("#jnumber").val(),
                    "department":$("#keshi").val(),
                    "name":$("#dname").val(),
                    "phone":$("#phone").val(),
                    "email":$("#email").val(),
                    "summary":$("#summary").val(),
                    "sex":$("#sex").val(),
                    "age":$("#age").val(),
                    "position":$("#pos").val()
            },
            dataType : "json",
            success : function(datas) {
                $('#myModal').modal('hide');
                location.reload()
            }
        });
    }
    function addDoc() {
        $.ajax({
            url : contextPath + "/Admin/addDoc",
            type : "POST",
            data : {"pw":$("#pw").val(),
                "name":$("#name").val(),
                "tname":$("#tname").val()

            },
            dataType : "json",
            success : function(datas) {
                $('#addDocModal').modal('hide');
                location.reload()
            }
        });
    }
    function searchDoctor(){

        var department=$("#department").val();

        $.ajax({
            url : contextPath + "/Admin/searchDoctor",
            type : "POST",
            data : {"department":department},
            dataType : "text",
            success : function(datas) {
                $("#doctorTable").html(datas);
            }
        });
    }

    function deleteDoctor(uid){
        layer.confirm('确认删除？', {
            btn: ['确认','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            $.ajax({
                url : contextPath + "/Admin/deleteDoctor",
                type : "POST",
                data : {"uid":uid},
                dataType : "json",
                success : function(datas) {
                    location.reload();
                }
            });
        }, function(){

        });




    }


    function echoMyModel(jnumber){
        $.ajax({
            url : contextPath + "/Admin/searchDoctorByJnumber",
            type : "POST",
            data : {"jnumber":jnumber},
            dataType : "json",
            success : function(datas) {
                $("#jnumber").val(datas.jnumber);
                $("#keshi").val(datas.department);
                $("#dname").val(datas.tname);
                $("#sex").val(datas.sex);
                $("#phone").val(datas.phone);
                $("#email").val(datas.email);
                $("#summary").val(datas.summary);
                $("#pos").val(datas.position);
                $("#age").val(datas.age);
            }
        });
    }

</script>
</body>

</html>
