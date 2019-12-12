<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/11
  Time: 19:54
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
    <li class="active">分配角色</li>
</ol>
<div class="panel panel-default">
    <div class="panel-body">
        <form role="form" class="form-inline">
            <div class="form-group">
                <label for="leftRoleList">未分配角色列表</label><br>
                <select id="leftRoleList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                   <%-- content --%>
                </select>
            </div>
            <div class="form-group">
                <ul>
                    <li id="left-right-btn" class="btn btn-default glyphicon glyphicon-chevron-right"> 添加</li>
                    <br>
                    <li id="right-left-btn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"> 删除</li>

                    <br>
                    <li id="left-right-all-btn" class="btn btn-default glyphicon glyphicon-forward" style="margin-top:20px;"> 添加所有</li>

                    <br>
                    <li id="right-left-all-btn" class="btn btn-default glyphicon glyphicon-backward" style="margin-top:20px;"> 删除所有</li>
                </ul>
            </div>
            <div class="form-group" style="margin-left:40px;">
                <label for="rightRoleList">已分配角色列表</label><br>
                <select id="rightRoleList" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">

                </select>
            </div>
        </form>
    </div>
</div>
<script src="${APP_PATH }/js/user/assignRole.js"></script>
</body>
</html>
