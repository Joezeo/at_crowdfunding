/**
 * 这个js是所有需要分页查询公共js
 */
$(function () {
    doQueryPage();
});

function doQueryPage(pageNumFront) {
    var pageNum = 1;
    if(pageNumFront)
      pageNum = pageNumFront;

    var pageSize = 10;
    var params = {pageNum: pageNum, pageSize: pageSize};

    var url = '';
    switch ($("#navbar_title").data("groupId")) {
        case 1: // 点击用户维护
            url = '/user/doPageQuery.do';
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
                layer.msg(jsonResult.message, {time:1000, icon:5, shift:1}, function () {

                })
            }
        }
    });
    return false;
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

/**
 * 设置分页导航条
 * @param pageInfo 从后台查询到的分页信息
 */
function setPagination(pageInfo) {
    var target = pageInfo.pageNum;
    var content = '';
    if(target == 1){
        content += "<li class='disabled'><a href='"+target+"'>上一页</a></li>";
    } else {
        content += "<li><a href='#' onclick='doQueryPage()'>上一页</a></li>";
    }

    for(var i=0; i<pageInfo.pageTotal; i++){
        content += "<li><a onclick='doQueryPage("+(i+1)+")'>"+(i+1)+"</a></li>"
    }
    target = pageInfo.pageNum;
    if(target == pageInfo.pageTotal){
        content += "<li class='disabled'><a href='#'>下一页</a></li>";
    } else {
        content += "<li><a href='#' onclick='doQueryPage()'>上一页</a></li>";
    }
    $("#pagination").html(content);
}