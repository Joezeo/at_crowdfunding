<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/11
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row clearfix">
    <div class="col-sm-3 col-md-3 column">
        <div class="row">
            <div class="col-md-12">
                <div class="thumbnail" style="    border-radius: 0px;">
                    <img src="img/services-box1.jpg" class="img-thumbnail" alt="">
                    <div class="caption" style="text-align:center;">
                        <h3>
                            ${sessionScope.member.username}
                        </h3>

                        <c:choose>
                            <c:when test="${member.authstatus eq '1'}">
                                <span class="label label-warning" style="cursor:pointer;">实名认证申请中</span>
                            </c:when>
                            <c:when test="${member.authstatus eq '2'}">
                                <span class="label label-success" style="cursor:pointer;">已实名认证</span>
                            </c:when>
                            <c:otherwise>
                                <span id="member-verify-btn" class="label label-danger"
                                      style="cursor:pointer;">未实名认证</span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        <div class="list-group">
            <div class="list-group-item active">
                资产总览<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
            </div>
            <div class="list-group-item " style="cursor:pointer;"
                 onclick="window.location.href='minecrowdfunding.html'">
                我的众筹<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
            </div>
        </div>
    </div>
    <div class="col-sm-9 col-md-9 column">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <b>
                我的钱包
            </b>
        </blockquote>
        <div id="main" style="width: 600px;height:400px;"></div>
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <b>
                理财
            </b>
        </blockquote>
        <div id="main1" style="width: 600px;height:400px;"></div>
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <b>
                比例
            </b>
        </blockquote>
        <div id="main2" style="width: 600px;height:400px;"></div>
    </div>
</div>
<script src="${APP_PATH}/js/member/home.js"></script>
</body>
</html>
