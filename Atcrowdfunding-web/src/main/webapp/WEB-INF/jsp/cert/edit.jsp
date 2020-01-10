<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/9
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据列表</a></li>
    <li class="active">修改</li>
</ol>
<div class="panel panel-default">
    <div class="panel-heading">资质修改
        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                class="glyphicon glyphicon-question-sign"></i></div>
    </div>
    <div class="panel-body">
        <form role="form">
            <div class="form-group">
                <label for="cert_name">资质名称</label>
                <input type="text" class="form-control" id="cert_name" placeholder="请输入资质名称">
            </div>
            <button id="cert_edit_submit" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
            <button id="cert_edit_refresh" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
        </form>
    </div>
</div>
<script src="${APP_PATH}/js/cert/edit.js"></script>
</body>
</html>
