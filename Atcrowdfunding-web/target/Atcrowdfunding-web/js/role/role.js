$(function () {
    doQueryPage(0);

    $("#role-search-btn").click(doSearch);

    $("#role-add-btn").click(loadAddRolePage);

    // 给checkBox绑定联动效果
    $("#role-top-checkbox").click(selectAll);

    $("#role-remove-btn").click(doRemoveRoles);

    // 动态绑定每行的操作事件
    $("#role_table_body").on("click", "#table-modify", doModifyRole);
    $("#role_table_body").on("click", "#table-remove", doRemoveRole);
    $("#role_table_body").on("click", "#table-assignPermission", doLoadAssignPermission);
});

function doSearch() {

    //去掉搜索条件的空格
    var name = $("#role-search-condition").val().trim();

    // 如果没有输入任何值或者输入了空格 将输入框重置
    $("#role-search-condition").val(name);

    doQueryPage();
}

function loadAddRolePage() {
    $("#content_div").load('/role/add.htm?t=' + Math.random());
}

function doModifyRole() {
    // 获取角色id
    // this是按钮 id绑定在父元素td 的父元素tr上
    var id = $(this).parent().parent().attr("roleId");
    $("#content_div").data("roleId", id);

    setTimeout(function () {
        $("#content_div").load('/role/edit.htm?t=' + Math.random());
    }, 500);
}

function selectAll() {
    var status = $("#role-top-checkbox").prop("checked");

    $("#role_table_body :checkbox").prop("checked", status);
}

function doRemoveRole() {
    var flg = confirm("确定要删除该条数据么,不可撤销");
    var id = $(this).parent().parent().attr("roleId");

    if (!flg) {
        return false;
    }

    var ids = [id];
    $.ajax({
        url: '/role/doRemoveRoles.do',
        data: {ids: ids},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功~");

                doQueryPage(0);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function doRemoveRoles() {
    var selected = $("#role_table_body :checkbox:checked");
    var ids = [];

    if (selected.length == 0) {
        alert("请至少选择一条数据");
        return false;
    }

    var flg = confirm("确定要删除这些数据么,不可撤销");
    if (!flg) {
        return false;
    }

    selected.each(function (i, n) {
        ids[i] = $(this).parent().parent().attr("roleId");
    });

    $.ajax({
        url: '/role/doRemoveRoles.do',
        data: {ids: ids},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功~");

                $("#role-top-checkbox").prop("checked", false);
                doQueryPage(0);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function doLoadAssignPermission() {
    var id = $(this).parent().parent().attr("roleId");
    $("#content_div").load("/role/assignPermission.htm?roleid=" + id);
}
