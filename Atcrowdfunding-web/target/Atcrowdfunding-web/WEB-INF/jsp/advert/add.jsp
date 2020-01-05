<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/5
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 新增广告</h3>
    </div>
    <div class="panel-body">
        <div class="panel-heading">广告数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
        <div class="panel-body">
            <form id="advertForm" method="post" action="" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">广告名称</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入广告名称">
                </div>
                <div class="form-group">
                    <label for="url">广告地址</label>
                    <input type="text" class="form-control" id="url" name="url" placeholder="请输入广告地址">
                </div>
                <div class="form-group">
                    <label for="advpic">广告图片</label>
                    <input type="file" class="form-control" id="advpic" name="advpic" placeholder="请输入广告图片">
                </div>
                <button id="advert-add-submit" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                <button id="advert-add-refresh" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
            </form>
        </div>
    </div>
</div>
<script src="${APP_PATH}/js/advert/add.js"></script>
<script src="${APP_PATH}/jquery/jquery-form/jquery-form.min.js"></script>
</body>
</html>
