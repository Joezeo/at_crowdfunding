<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/7
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${APP_PATH}/css/pagination.css" />
</head>
<body>
<tr>
    <td colspan="6" align="center">
<%--        <ul class="pagination" id="pagination">--%>

<%--&lt;%&ndash;            <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <li><a href="#">2</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <li><a href="#">3</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <li><a href="#">4</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <li><a href="#">5</a></li>&ndash;%&gt;--%>

<%--        </ul>--%>
    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
    </td>
</tr>

<script src="${APP_PATH}/jquery/pagination/jquery.pagination.js"></script>
<script src="${APP_PATH }/js/common/pagination.js"></script>
</body>
</html>
