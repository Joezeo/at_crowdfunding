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
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 流程列表</h3>
    </div>
    <div class="panel-body">

        <form class="form-inline" role="form" style="float:left;">
            <div class="form-group has-feedback">
                <div class="input-group">
                    <div class="input-group-addon">查询条件</div>
                    <input id="process-search-condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
                </div>
            </div>
            <button id="process-search-btn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
        </form>

        <button id="process-upload-btn" type="button" class="btn btn-primary" style="float:right;"><i
                class="glyphicon glyphicon-upload"></i> 上传流程定义文件
        </button>
        <form id="process-upload-form" method="post" action="" enctype="multipart/form-data">
            <input type="file" class="form-control" id="process-upload-file" name="processpic" style="display: none;">
        </form>
        <br>
        <hr style="clear:both;">
        <div id="mymodal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">流程定义图片</h4>
                    </div>

                    <div class="modal-body" style="text-align: center">
                        <img id="process-pic-img" alt="流程定义图片" src="${APP_PATH}/jquery/layer/theme/default/loading-1.gif">
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div class="table-responsive">
            <table class="table  table-bordered">
                <thead>
                <tr>
                    <th width="30">#</th>
                    <th>流程名称</th>
                    <th>流程版本</th>
                    <th>流程KEY</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody id="process_table_body">
                </tbody>
                <tfoot>
                <%@include file="../common/pagination.jsp" %>
                </tfoot>
            </table>
        </div>
    </div>
    <script src="${APP_PATH}/js/process/process.js"></script>
    <script src="${APP_PATH}/jquery/jquery-form/jquery-form.min.js"></script>
</body>
</html>
