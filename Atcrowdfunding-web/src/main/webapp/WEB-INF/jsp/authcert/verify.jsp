<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/9
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">会员实名认证</a></li>
    <li class="active">审核</li>
</ol>
<div class="panel panel-default">
    <div class="panel-heading">会员实名认证信息
        <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                class="glyphicon glyphicon-question-sign"></i></div>
    </div>
    <div class="panel-body">
        <div class="form-group">
            <label>会员名称：${memberInfo.realname}</label>
        </div>

        <div class="form-group">
            <label>身份证号：${memberInfo.cardnum}</label>
        </div>
        <div class="form-group">
            <label>电话号码：${memberInfo.phone}</label>
        </div>
        <div class="form-group">
            <label>会员类型：
                <c:choose>
                    <c:when test="${memberInfo.accttype eq '0'}">
                       企业账号
                    </c:when>
                    <c:when test="${memberInfo.accttype eq '1'}">
                        个体账号
                    </c:when>
                    <c:when test="${memberInfo.accttype eq '2'}">
                        个人账号
                    </c:when>
                    <c:when test="${memberInfo.accttype eq '3'}">
                        政府账号
                    </c:when>
                </c:choose>
            </label>
        </div>
        <hr>
        <c:forEach items="${certsInfo}" var="cert">
            <div class="form-group">
                <label>${cert.name}</label>
                <br>
                <img src="${APP_PATH}/pics/cert/${cert.iconpath}">
            </div>
            <br>
        </c:forEach>
        <button id="verify-pass" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 通过
        </button>
        <button id="verify-resuse" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i>
            拒绝
        </button>
    </div>
</div>
<script src="${APP_PATH}/js/authcert/verify.js"></script>
</body>
</html>
