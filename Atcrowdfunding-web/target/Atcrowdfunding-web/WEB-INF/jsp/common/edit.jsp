<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/9
  Time: 18:25
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
    <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
    <div class="panel-body">
        <form role="form">

<%--            需要从数据库重新查询当前用户 避免脏数据--%>
            <input type="hidden" id="edit-id" />
            <div class="form-group">
                <label for="edit-loginacct">登陆账号</label>
                <input type="text" class="form-control" id="edit-loginacct" value="test">
            </div>
            <div class="form-group">
                <label for="edit-username">用户名称</label>
                <input type="text" class="form-control" id="edit-username" value="测试用户">
            </div>
            <div class="form-group">
                <label for="edit-email">邮箱地址</label>
                <input type="email" class="form-control" id="edit-email" value="xxxx@xxxx.com">
                <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p> </div>
            <button id="edit-modify-button" type="button" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
            <button id="edit-refresh-button" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
        </form>
    </div>
</div>
<script src="${APP_PATH}/js/common/edit.js"/>
</body>
</html>
