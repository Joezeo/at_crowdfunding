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
    <div class="panel-heading">表单数据
        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                class="glyphicon glyphicon-question-sign"></i></div>
    </div>
    <div class="panel-body">
        <form role="form">
            <div class="form-group">
                <label for="permission-name">权限名称</label>
                <input type="text" class="form-control" id="permission-name" placeholder="请输入权限名称">
            </div>

            <div class="form-group">
                <label for="permission-url">权限url地址</label>
                <input type="text" class="form-control" id="permission-url" placeholder="请输入权限url地址">
            </div>

            <div class="form-group">
                <label for="permission-url">权限前端id值</label>
                <input type="text" class="form-control" id="permission-frontId" placeholder="请输入权限url地址">
            </div>

            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <span id="title-span">请选择权限图标</span>
                    <span class="caret"></span>
                </button>
                <ul id="permission-icon-ul" class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li icon="glyphicon glyphicon-th-list"><a><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> glyphicon glyphicon-th-list</a></li>
                    <li icon="glyphicon glyphicon-dashboard"><a><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> glyphicon glyphicon-dashboard</a></li>
                    <li icon="glyphicon glyphicon glyphicon-tasks"><a><span class="glyphicon glyphicon glyphicon-tasks" aria-hidden="true"></span> glyphicon glyphicon glyphicon-tasks</a></li>
                    <li icon="glyphicon glyphicon-user"><a><span class="glyphicon glyphicon-user" aria-hidden="true"></span> glyphicon glyphicon-user</a></li>
                    <li icon="glyphicon glyphicon-king"><a><span class="glyphicon glyphicon-king" aria-hidden="true"></span> glyphicon glyphicon-king</a></li>
                    <li icon="glyphicon glyphicon-lock"><a><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> glyphicon glyphicon-lock</a></li>
                    <li icon="glyphicon glyphicon-ok"><a><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> glyphicon glyphicon-ok</a></li>
                    <li icon="glyphicon glyphicon-check"><a><span class="glyphicon glyphicon-check" aria-hidden="true"></span> glyphicon glyphicon-check</a></li>
                    <li icon="glyphicon glyphicon-th-large"><a><span class="glyphicon glyphicon-th-large" aria-hidden="true"></span> glyphicon glyphicon-th-large</a></li>
                    <li icon="glyphicon glyphicon-picture"><a><span class="glyphicon glyphicon-picture" aria-hidden="true"></span> glyphicon glyphicon-picture</a></li>
                    <li icon="glyphicon glyphicon-equalizer"><a><span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span> glyphicon glyphicon-equalizer</a></li>
                    <li icon="glyphicon glyphicon-random"><a><span class="glyphicon glyphicon-random" aria-hidden="true"></span> glyphicon glyphicon-random</a></li>
                    <li icon="glyphicon glyphicon-hdd"><a><span class="glyphicon glyphicon-hdd" aria-hidden="true"></span> glyphicon glyphicon-hdd</a></li>
                    <li icon="glyphicon glyphicon-comment"><a><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> glyphicon glyphicon-comment</a></li>
                    <li icon="glyphicon glyphicon-list"><a><span class="glyphicon glyphicon-list" aria-hidden="true"></span> glyphicon glyphicon-list</a></li>
                    <li icon="glyphicon glyphicon-tags"><a><span class="glyphicon glyphicon-tags" aria-hidden="true"></span> glyphicon glyphicon-tags</a></li>
                    <li icon="glyphicon glyphicon-list-alt"><a><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> glyphicon glyphicon-list-alt</a></li>
                </ul>
            </div>

            <div style="margin-top: 10px;">
            <button id="permission-update-submit" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
            <button id="permission-update-refresh" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
            </div>
        </form>
    </div>
</div>
<script src="${APP_PATH}/js/permission/update.js"></script>
</body>
</html>
