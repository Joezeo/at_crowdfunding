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
    <li class="active">新增</li>
</ol>
<div class="panel panel-default">
    <div class="panel-heading">表单数据
        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                class="glyphicon glyphicon-question-sign"></i></div>
    </div>
    <div class="panel-body">
        <form role="form">
            <div class="form-group">
                <label for="f_loginAcct">登陆账号</label>
                <input type="text" class="form-control" id="f_loginAcct" placeholder="请输入登陆账号">
            </div>
            <div class="form-group">
                <label for="f_username">用户名称</label>
                <input type="text" class="form-control" id="f_username" placeholder="请输入用户名称">
            </div>
            <div class="form-group">
                <label for="f_email">邮箱地址</label>
                <input type="email" class="form-control" id="f_email" placeholder="请输入邮箱地址">
                <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
            </div>
            <button id="add-submit" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
            <button id="refresh" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
        </form>
    </div>
</div>
<script src="${APP_PATH}/js/common/add.js"/>
</body>
</html>
