<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/11
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
        <li role="presentation" class="active"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
        <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
        <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
    </ul>

    <form id="certfile-form" role="form" style="margin-top:20px;" method="post" enctype="multipart/form-data">
        <c:forEach items="${certs}" var="cert" varStatus="status">
            <div class="form-group">
                <label for="cardpic">${cert.name}</label>
                <input type="hidden" name="certimgs[${status.index}].certid" value="${cert.id}">
                <input id="cardpic" name="certimgs[${status.index}].imgFile" type="file" class="form-control" >
                <br>
                <img src="" style="display: none">
            </div>
        </c:forEach>
        <button id="apply_2_back" type="button" class="btn btn-default">上一步</button>
        <button id="apply_2_forward" type="button" class="btn btn-success">下一步</button>
    </form>
    <hr>
</div> <!-- /container -->
<script src="${APP_PATH}/js/member/apply_2.js"></script>
<script src="${APP_PATH}/jquery/jquery-form/jquery-form.min.js"></script>
</body>
</html>
