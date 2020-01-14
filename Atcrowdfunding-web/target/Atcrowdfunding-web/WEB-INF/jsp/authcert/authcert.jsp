<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/14
  Time: 14:56
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
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 角色列表</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline" role="form" style="float:left;">
            <div class="form-group has-feedback">
                <div class="input-group">
                    <div class="input-group-addon">查询条件</div>
                    <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                </div>
            </div>
            <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
        </form>
        <br>
        <hr style="clear:both;">
        <div class="table-responsive">
            <table class="table  table-bordered">
                <thead>
                <tr>
                    <th width="30">#</th>
                    <th>流程名称</th>
                    <th>流程版本</th>
                    <th>任务名称</th>
                    <th>申请会员</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody id="authcert_table_body">
                </tbody>
                <tfoot>
                <%@include file="../common/pagination.jsp" %>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<script src="${APP_PATH}/js/authcert/authcert.js"></script>
</body>
</html>
