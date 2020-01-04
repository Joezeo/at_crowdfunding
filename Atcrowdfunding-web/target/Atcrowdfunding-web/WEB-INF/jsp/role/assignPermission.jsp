<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/11
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css" type="text/css"/>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 权限分配列表</h3>
    </div>

    <div class="panel-body">
        <button id="assignPermissionBtn" class="btn btn-success">分配许可</button>
        <ul id="treeDemo" class="ztree" data-roleid="${param.roleid}"></ul>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${APP_PATH}/js/role/assignPermission.js"></script>
</body>
</html>
