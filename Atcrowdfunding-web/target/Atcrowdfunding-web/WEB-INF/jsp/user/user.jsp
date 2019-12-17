<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/6
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">

</head>

<body style="height: 100%;">

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户列表</h3>
    </div>
    <div class="panel-body">
        <form class="form-inline" role="form" style="float:left;">
            <div class="form-group has-feedback">
                <div class="input-group">
                    <div class="input-group-addon">查询条件</div>
                    <input id="search-condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
                </div>
            </div>
            <button id="search-button" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
        </form>
        <button id="user-delete-button" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                class=" glyphicon glyphicon-remove"></i> 删除
        </button>
        <button type="button" class="btn btn-primary" style="float:right;"
                id="add-button"><i class="glyphicon glyphicon-plus"></i> 新增
        </button>
        <br>
        <hr style="clear:both;">
        <div class="table-responsive">
            <table class="table  table-bordered" id="main-table">
                <thead>
                <tr>
                    <th width="30"></th>
                    <th width="30"><input id="select-all" type="checkbox"></th>
                    <th>账号</th>
                    <th>名称</th>
                    <th>邮箱地址</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody id="user_table_body">

                </tbody>
                <tfoot>

                <%@include file="../common/pagination.jsp"%>
<%--                    <jsp:include page="pagination.jsp"></jsp:include>--%>
                </tfoot>
            </table>
        </div>
    </div>
</div>

<script src="${APP_PATH }/js/user/user.js"></script>
<script src="${APP_PATH }/js/user/assignRole.js"></script>
</body>
</html>

