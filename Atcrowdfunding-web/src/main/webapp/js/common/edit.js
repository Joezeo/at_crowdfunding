$(function () {
    $("#edit-modify-button").click(doModify);

    $("#edit-refresh-button").click(refreshTable);
});

function refreshTable() {
    $("#edit-loginacct").val("");
    $("#edit-email").val("");
    $("#edit-username").val("");
}

function doModify() {
    if (validEditData()) {
        return false;
    }

    var loginacct = $("#edit-loginacct").val();
    var email = $("#edit-email").val();
    var username = $("#edit-username").val();
    var id = $("#content_div").data("id");

    var params = {loginacct: loginacct, email: email, username: username, id:id};
    $.ajax({
        url: '/user/doModifyUser.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                backToUserPage();
            } else {
                alert(jsonResult.message)
            }
        }
    })
}

function validEditData() {
    if ($("#edit-loginacct").val() == "" || $("#edit-username").val() == "" || $("#edit-email").val() == "") {
        alert("以上内容不得为空，请检查后重新提交修改！")
        return true;
    }
}

function backToUserPage() {
    $("#content_div").load('user.htm?t=' + Math.random());
    alert("修改用户信息成功~");
}