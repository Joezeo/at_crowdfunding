<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/1/11
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .seltype {
            position: absolute;
            margin-top: 70px;
            margin-left: 10px;
            color: red;
        }
    </style>
</head>
<body>
<div class="container theme-showcase" role="main">

    <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
    </div>
    <div style="padding-top:10px;">
        <div class="row">
            <div class="col-xs-6 col-md-3">

                <h2>商业公司</h2>
                <a href="#" class="thumbnail" accttype="0">

                    <img alt="100%x180" src="img/services-box1.jpg" data-holder-rendered="true"
                         style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>个体工商户</h2>
                <a href="#" class="thumbnail" accttype="1">
                    <img alt="100%x180" src="img/services-box2.jpg" data-holder-rendered="true"
                         style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>个人经营</h2>
                <a href="#" class="thumbnail" accttype="2">
                    <img alt="100%x180" src="img/services-box3.jpg" data-holder-rendered="true"
                         style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <h2>政府及非营利组织</h2>
                <a href="#" class="thumbnail" accttype="3">
                    <img alt="100%x180" src="img/services-box4.jpg" data-holder-rendered="true"
                         style="height: 180px; width: 100%; display: block;">
                </a>
            </div>
        </div>
        <button id="choose-accttype-btn" type="button" class="btn btn-danger btn-lg btn-block">认证申请
        </button>
    </div> <!-- /container -->
    <!-- /END THE FEATURETTES -->
</div>
<script src="${APP_PATH}/js/member/accttype.js"></script>
</body>
</html>
