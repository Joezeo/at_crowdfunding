<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/11
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1>实名认证 - 申请</h1>
    </div>

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#"><span class="badge">1</span> 基本信息</a></li>
        <li role="presentation"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
        <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
        <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
    </ul>

    <form role="form" style="margin-top:20px;">
        <div class="form-group">
            <label for="member-input-realname">真实名称</label>
            <input type="text" class="form-control" id="member-input-realname" placeholder="请输入真实名称">
        </div>
        <div class="form-group">
            <label for="member-input-cardnum">身份证号码</label>
            <input type="text" class="form-control" id="member-input-cardnum" placeholder="请输入身份证号码">
        </div>
        <div class="form-group">
            <label for="member-input-phone">电话号码</label>
            <input type="text" class="form-control" id="member-input-phone" placeholder="请输入电话号码">
        </div>
        <button id="apply_1_back" type="button" class="btn btn-default">上一步</button>
        <button id="apply_1_forward" type="button" class="btn btn-success">下一步</button>
    </form>
    <hr>
</div> <!-- /container -->
<script src="${APP_PATH}/js/member/apply_1.js"></script>
</body>
</html>
