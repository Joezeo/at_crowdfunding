/**
 * 这个js是所有需要分页查询公共js
 */

function doQueryPage() {
    var pageNum = 1;
    if ($("#content_div").data("pageNum")) {
        pageNum = $("#content_div").data("pageNum");
    }
    var pageSize = 10;
    var params = {pageNum: pageNum, pageSize: pageSize};

    //获取搜搜条件 并去掉搜索条件的空格
    var loginAcct = $("#search-condition").val().trim();
    // 如果用户键入了搜索条件
    if (loginAcct != "") {
        params.loginAcct = loginAcct;
    }

    var url = '';
    switch ($("#navbar_title").data("groupId")) {
        case 1: // 点击用户维护
            url = '/user/doPageQuery.do?t='+Math.random();
            break;
    }

    $.ajax({
        url: url,
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                //将当前页码绑定在content_div上
                $("#content_div").data("pageNum", jsonResult.data.pageNum);

                //将数据填入表格
                loadDataInTable(jsonResult.data.datas);

                //设置pagination分页导航条
                setPagination(jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    });
    return false;
}

function loadDataInTable(list) {
    var tbody = $("#table_body");
    var content = "";

    for (var i = 0; i < list.length; i++) {
        content += "<tr userId='" + list[i].id + "'>";
        content += "<td>" + (i + 1) + "</td>";
        content += "<td><input type='checkbox'></td>";
        content += "<td>" + list[i].loginacct + "</td>";
        content += "<td>" + list[i].username + "</td>";
        content += "<td>" + list[i].email + "</td>";
        //为了方便 我们把用户的id绑定在当前按钮的td上
        content += "<td >\n" +
            "\t\t\t\t      <button id='table-check' type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
            "\t\t\t\t      <button id='table-modify' type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
            "\t\t\t\t\t  <button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
            "\t\t\t\t  </td>";
        content += "</tr>";
    }

    tbody.html(content);
}

function reloadUserPage(pageNum) {
    // 将pageNum绑定在content_div上
    $("#content_div").data("pageNum", pageNum);

    $("#content_div").load("user.htm?t=" + Math.random());
}

/**
 * 设置分页导航条
 * @param pageInfo 从后台查询到的分页信息
 */
function setPagination(pageInfo) {
    var target = pageInfo.pageNum;
    var content = '';
    if (target == 1) {
        content += "<li class='disabled'><a href='" + target + "'>上一页</a></li>";
    } else {
        content += "<li><a onclick='reloadUserPage(" + (target - 1) + ")'>上一页</a></li>";
    }

    for (var i = 0; i < pageInfo.pageTotal; i++) {
        if (pageInfo.pageNum == i + 1) {
            content += "<li class='active'><a onclick='reloadUserPage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>"
        } else {
            content += "<li><a onclick='reloadUserPage(" + (i + 1) + ")'>" + (i + 1) + "</a></li>"
        }

    }
    target = pageInfo.pageNum;
    if (target == pageInfo.pageTotal) {
        content += "<li class='disabled'><a href='#'>下一页</a></li>";
    } else {
        content += "<li><a onclick='reloadUserPage(" + (target + 1) + ")'>下一页</a></li>";
    }
    $("#pagination").html(content);
}