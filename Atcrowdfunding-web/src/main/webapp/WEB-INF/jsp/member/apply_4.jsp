<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/13
  Time: 16:52
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
        <li role="presentation"><a href="#"><span class="badge">1</span> 基本信息</a></li>
        <li role="presentation"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
        <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
        <li role="presentation" class="active"><a href="#"><span class="badge">4</span> 申请确认</a></li>
    </ul>

    <form role="form" style="margin-top:20px;">
        <div class="form-group">
            <label for="authcode">验证码</label>
            <input type="text" class="form-control" id="authcode" placeholder="请输入你邮箱中接收到的验证码">
        </div>
        <button type="button" onclick="javascript:;" class="btn btn-primary">重新发送验证码</button>
        <button type="button" id="apply_4_finish" class="btn btn-success">申请完成</button>
    </form>
    <hr>
</div> <!-- /container -->
</body>
<script src="${APP_PATH}/js/member/apply_4.js"></script>
</html>
