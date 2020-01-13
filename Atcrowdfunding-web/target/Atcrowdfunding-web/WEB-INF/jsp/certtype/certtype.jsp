<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <style>
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}

        input[type=checkbox] {
            width:18px;
            height:18px;
        }
    </style>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 分类数据矩阵</h3>
    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table  table-bordered">
                <thead>
                <tr >
                    <th>名称</th>
                    <th >商业公司</th>
                    <th >个体工商户</th>
                    <th >个人经营</th>
                    <th >政府及非营利组织</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.certs}" var="cert">
                    <tr>
                        <td>${cert.name}</td>
                        <td><input type="checkbox" accttype="0" certid="${cert.id}"></td>
                        <td><input type="checkbox" accttype="1" certid="${cert.id}"></td>
                        <td><input type="checkbox" accttype="2" certid="${cert.id}"></td>
                        <td><input type="checkbox" accttype="3" certid="${cert.id}"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="${APP_PATH}/js/certtype/certtype.js"></script>
</body>
</html>
