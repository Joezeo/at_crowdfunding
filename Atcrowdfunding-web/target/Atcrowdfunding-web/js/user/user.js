$(function () {
    // 执行分页查询应该放到user.js中，然后在分页导航条点击时应当重新loaduser页面
    // 否则之后页码的修改按钮点击事件无法被绑定
    doQueryPage();
    // 点击查询按钮
    $("#search-button").click(doSearch);

    // 点击新增按钮
    $("#add-button").click(loadAddUserPage);

    // 点击表格中的修改按钮
    $("#main-table").on("click", "#table-modify", loadEditPage);
});

/**
 * 点击查询按钮
 */
function doSearch() {
    //去掉搜索条件的空格
    var loginAcct = $("#search-condition").val().trim();

    // 如果没有输入任何值或者输入了空格 将输入框重置
    if (loginAcct == "") {
        $("#search-condition").val("");
    }

    doQueryPage();
}

/**
 * 加载角色添加页面
 */
function loadAddUserPage() {
    $("#content_div").load("add.htm?t=" + Math.random());
}

/**
 * 记载角色编辑页面 从后台获取当前id的用户信息填入edit界面中
 */
function loadEditPage() {
    $("#content_div").load("edit.htm?t=" + Math.random());

    var id = $(this).parent().attr("userId");
    // 把id绑定当content_div上
    $("#content_div").data("id",id);

    $.ajax({
        url: '/user/doQueryUser.do?t=' + Math.random(),
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                // 将用户信息填充到edit界面中
                fillDataInEdit(jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

/**
 * 往edit页面中填值
 * @param user
 */
function fillDataInEdit(user) {
    $("#edit-loginacct").val(user.loginacct);
    $("#edit-email").val(user.email);
    $("#edit-username").val(user.username);
}
