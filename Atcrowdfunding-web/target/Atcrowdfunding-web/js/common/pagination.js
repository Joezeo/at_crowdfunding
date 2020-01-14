/**
 * 这个js是所有需要分页查询公共js
 */

// 记录用户点击了哪个连接，从navbar_title获取
var groupId;

function doQueryPage(pageIndex) {
    groupId = $("#navbar_title").data("groupId");

    var pageNum = pageIndex + 1;
    var pageSize = 10;
    var params = {pageNum: pageNum, pageSize: pageSize};


    var url = '';
    switch (groupId) {
        case 1: // 点击用户维护
            url = '/user/doPageQuery.do?t=' + Math.random();

            //获取搜搜条件 并去掉搜索条件的空格
            var loginAcct = $("#search-condition").val();
            if (loginAcct) {
                loginAcct.trim();
            }
            // 如果用户键入了搜索条件
            if (loginAcct != "") {
                params.loginAcct = loginAcct;
            }
            break;

        case 2: // 点击角色维护
            url = "/role/doPageQuery.do";

            // 获取搜索条件
            var name = $("#role-search-condition").val();
            if (name) {
                name.trim();
            }
            if (name != "") {
                params.name = name;
            }
            break;

        case 4:  // 点击广告管理
            url = "/advert/doPageQuery.do";

            // 获取搜索条件
            var name = $("#advert-search-condition").val();
            if (name) {
                name.trim();
            }
            if (name != "") {
                params.name = name;
            }
            break;

        case 5: // 点击资质管理
            url = "/cert/doQueryPage.do";

            // 获取搜索条件
            var name = $("#cert-search-condition").val();
            if (name) {
                name.trim();
            }
            if (name != "") {
                params.name = name;
            }
            break;

        case 6: // 点击流程管理
            url = "/process/doQueryPage.do";

            // 获取搜索条件
            var name = $("#process-search-condition").val();
            if (name) {
                name.trim();
            }
            if (name != "") {
                params.name = name;
            }
            break;

        case 8: // 点击实名认证审核
            url = "/authcert/doQueryPage.do";
            break;
    }

    $.ajax({
        url: url,
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
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
    var tbody;

    var content = "";
    switch (groupId) {
        case 1: // 用户
            tbody = $("#user_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr userId='" + list[i].id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td><input type='checkbox'></td>";
                content += "<td>" + list[i].loginacct + "</td>";
                content += "<td>" + list[i].username + "</td>";
                content += "<td>" + list[i].email + "</td>";
                content += "<td >\n" +
                    "\t\t\t\t      <button id='table-assignRole' type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
                    "\t\t\t\t      <button id='table-modify' type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
                    "\t\t\t\t\t  <button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
                    "\t\t\t\t  </td>";
                content += "</tr>";
            }
            break;

        case 2: // 角色
            tbody = $("#role_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr roleId='" + list[i].id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td><input type='checkbox'></td>";
                content += "<td>" + list[i].name + "</td>";
                content += "<td >\n" +
                    "\t\t\t\t      <button id='table-assignPermission' type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
                    "\t\t\t\t      <button id='table-modify' type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
                    "\t\t\t\t\t  <button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
                    "\t\t\t\t  </td>";
                content += "<tr>";
            }
            break;

        case 4: // 广告
            tbody = $("#advert_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr advertId='" + list[i].id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td>" + list[i].name + "</td>";
                var sta = "";
                switch (list[i].status) {
                    case "0":
                        sta = "草稿";
                        break;
                    case "1":
                        sta = "未审核";
                        break;
                    case "2":
                        sta = "审核完成";
                        break;
                    case "3":
                        sta = "发布";
                        break;
                }
                content += "<td>" + sta + "</td>";
                content += "<td >\n" +
                    "\t\t\t\t      <button id='table-assignPermission' type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
                    "\t\t\t\t      <button id='table-modify' type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
                    "\t\t\t\t\t  <button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
                    "\t\t\t\t  </td>";
                content += "<tr>";
            }
            break;

        case 5: // 资质管理
            tbody = $("#cert_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr certId='" + list[i].id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td><input type='checkbox'></td>";
                content += "<td>" + list[i].name + "</td>";
                content += "<td >\n" +
                    "\t\t\t\t      <button id='table-modify' type=\"button\" class=\"btn btn-primary btn-xs\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
                    "\t\t\t\t\t  <button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
                    "\t\t\t\t  </td>";
                content += "<tr>";
            }
            break;

        case 6: // 流程管理
            tbody = $("#process_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr processId='" + list[i].id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td>" + list[i].name + "</td>";
                content += "<td>" + list[i].version + "</td>";
                content += "<td>" + list[i].key + "</td>";
                content += "<td >" + "<button id='table-see' type=\"button\" class=\"btn btn-success btn-xs\"><i\n" +
                    "class=\" glyphicon glyphicon-eye-open\"></i></button>\n" +
                    "<button id='table-remove' type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\" glyphicon glyphicon-remove\"></i>\n" +
                    "</button>\n" + "</td>";
                content += "<tr>";
            }
            break;

        case 8: // 实名认证审核
            tbody = $("#authcert_table_body");
            for (var i = 0; i < list.length; i++) {
                content += "<tr taskId='" + list[i].taskId + "' memberId='" + list[i].member.id + "'>";
                content += "<td>" + (i + 1) + "</td>";
                content += "<td>" + list[i].prcDefName + "</td>";
                content += "<td>" + list[i].prcDefVersion + "</td>";
                content += "<td>" + list[i].taskName + "</td>";
                content += "<td>" + list[i].member.username + "</td>";
                content += "<td>" + "<button id='table-verify' type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>" + "</td>";
                content += "<tr>";
            }
            break;
    }

    tbody.html(content);
}

/**
 * 设置分页导航条
 * @param pageInfo 从后台查询到的分页信息
 */
function setPagination(pageInfo) {
    $("#Pagination").pagination(pageInfo.pageTotal, {
        num_edge_entries: 1, //边缘页数
        num_display_entries: 4, //主体页数
        current_page: pageInfo.pageNum - 1, //当前页数
        callback: doQueryPage,
        //每页显示1项
        //这里由于传的是总页数而不是总的数据条数，这里的1相当于每次展示1页，而一页有多少数据由上面的pageSize控制
        items_per_page: 1,
        prev_text: "上一页",
        next_text: "下一页"
    });
}