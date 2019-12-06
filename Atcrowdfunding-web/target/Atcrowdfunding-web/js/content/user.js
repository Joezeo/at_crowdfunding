$(function () {
    doQueryPage();
});

function doQueryPage() {
    // 测试数据
    var pageNum = 1;
    var pageSize = 10;

    var params = {pageNum: pageNum, pageSize: pageSize};

    $.ajax({
        url: '/doPageQuery.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadDataInTable(jsonResult.data.datas);
            } else {

            }
        }
    });
}

function loadDataInTable(list) {
    var tbody = $("#table_body");
    var content = "";

    for (var i = 0; i < list.length; i++) {
        content += "<tr>";
        content += "<td>" + (i+1) + "</td>";
        content += "<td><input type='checkbox'></td>";
        content += "<td>" + list[i].loginacct + "</td>";
        content += "<td>" + list[i].username + "</td>";
        content += "<td>" + list[i].email + "</td>";
        content += "<td>操作</td>";
        content += "</tr>";
    }

    tbody.html(content);
}