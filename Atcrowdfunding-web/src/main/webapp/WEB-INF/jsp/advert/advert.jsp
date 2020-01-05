<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/5
  Time: 13:32
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
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 广告列表</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline" role="form" style="float:left;">
            <div class="form-group has-feedback">
                <div class="input-group">
                    <div class="input-group-addon">查询条件</div>
                    <input id="advert-search-condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
                </div>
            </div>
            <button id="advert-search-btn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
        </form>
        <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                class=" glyphicon glyphicon-remove"></i> 删除
        </button>
        <button id="advert-add-btn" type="button" class="btn btn-primary" style="float:right;"><i
                class="glyphicon glyphicon-plus"></i> 新增
        </button>
        <br>
        <hr style="clear:both;">
        <div class="table-responsive">
            <table class="table  table-bordered">
                <thead>
                <tr>
                    <th width="30">#</th>
                    <th>广告描述</th>
                    <th>状态</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody id="advert_table_body">
                </tbody>
                <tfoot>
                <%@include file="../common/pagination.jsp" %>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<script src="${APP_PATh}/js/advert/advert.js"></script>
</body>
</html>
