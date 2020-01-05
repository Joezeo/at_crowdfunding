$(function () {
    // 执行分页查询应该放到user.js中，然后在分页导航条点击时应当重新loaduser页面
    // 否则之后页码的修改按钮点击事件无法被绑定
    doQueryPage(0);
    // 点击查询按钮
    $("#search-button").click(doSearch);

    // 点击新增按钮
    $("#add-button").click(loadAddUserPage);

    // 点击全选selectBox
    $("#select-all").click(selectAll);

    // 点击删除按钮
    $("#user-delete-button").click(doDeleteUserBatch);

    // 点击表格中的修改按钮
    $("#main-table").on("click", "#table-modify", loadEditPage);

    // 点击表格中的删除按钮
    $("#main-table").on("click", "#table-remove", doDeleteUser);

    // 点击表格中的分配角色按钮
    $("#main-table").on("click", "#table-assignRole", loadAssignPage);
});

/**
 * 点击查询按钮
 */
function doSearch() {
    //去掉搜索条件的空格
    var loginAcct = $("#search-condition").val().trim();

    // 如果没有输入任何值或者输入了空格 将输入框重置
    $("#search-condition").val(loginAcct);

    doQueryPage();
}

/**
 * 加载角色添加页面
 */
function loadAddUserPage() {
    $("#content_div").load("/user/add.htm?t=" + Math.random());
}

/**
 * 记载角色编辑页面 从后台获取当前id的用户信息填入edit界面中
 */
function loadEditPage() {
    $("#content_div").load("/user/edit.htm?t=" + Math.random());

    var id = $(this).parent().parent().attr("userId");

    // 加上了 alert 就执行成功，不加就失败
    // 这种情况一般出现在 alert() 之后的某个代码需要页面元素进入一定的状态才能使用，
    // 加上 alert() 之后，相当于页面元素有足够的时间进入一定的状态了，如果确定你的代码没有问题，
    // 你可以把 alert() 之后的代码放到一个 setTimeout 的函数中，也就是停一会再运行下面的代码，
    setTimeout(doAjax, 300, id);
}

function doAjax(id) {
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
    $("#edit-id").val(user.id);
}

/**
 * 执行删除单行用户
 */
function doDeleteUser() {
    var res = confirm("您确定要删除该行数据么？");
    if (!res) {
        return false;
    }
    var id = $(this).parent().parent().attr("userId");

    $.ajax({
        url: '/user/doDeleteUser.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功~");

                //重新执行分页查询刷新数据
                $("#content_div").data("pageNum", 1); // 把pageNum设置为1
                doQueryPage();
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function selectAll() {
    var checkedStatus = $("#select-all").prop("checked");
    $("#table_body :input[type='checkbox']").prop("checked", checkedStatus);
}

function doDeleteUserBatch() {
    // 获取所有选中的单选框， id是绑定在tr上的，即绑定在单选框的parent()上
    var boxs = $("#table_body :input:checked");
    var ids = "";
    boxs.each(function () {
        var id = $(this).parent().parent().attr("userId");
        if (ids == "") {
            ids += "id=" + id;
        } else {
            ids += "&id=" + id;
        }
    });

    if (ids == "") {
        alert("请至少选择一条数据");
        return false;
    }

    var res = confirm("您确定要删除这些数据么？");
    if (!res) {
        return false;
    }

    $.ajax({
        url: '/user/doDeleteUserBatch.do',
        data: {ids: ids},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功");
                doQueryPage();
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function loadAssignPage() {
    var id = $(this).parent().parent().attr("userId");
    $("#content_div").load("/user/assignRole.htm?t="+Math.random());

    // 似乎每次在获取了attr属性都需要setTimeOut?
    setTimeout(function (id) {
        loadUserRoles(id);
    },500, id);
}
