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
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 许可树</h3>
    </div>
    <div class="panel-body">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${APP_PATH}/js/permission/permission.js"></script>
</body>
</html>
