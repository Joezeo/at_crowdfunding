$(function () {
    $("#form_login_button").click(doLogin);
    $("#login_form").on("blur", "#f_loginacct, #f_userpswd", valid);
});

function doLogin() {
    if (!valid()) {
        return false;
    }

    var loginacct = $('#f_loginacct').val();
    var userpswd = $('#f_userpswd').val();
    var type = $('#f_type').val();

    var flag = $("#rememberMe").prop("checked");
    var rememberMe = flag?"1":"0"; // 1表示记住两周 0表示不记住

    var params = {loginacct: loginacct, userpswd: userpswd, type: type, rememberMe: rememberMe};

    $.ajax({
        url: '/doLogin.do',
        type: 'post',
        dataType: 'json',
        data: params,
        success: function (result) {
            if (result.success) {
                if (type == "user") {
                    location.href = "/main.htm";
                } else if (type == "member") {
                    location.href = "/member.htm";
                }


            } else {
                $("#acct_feedback").html("");
                $("#pwd_feedback").html("");
                // layer.msg(result.message, {time:1000, icon:5, shift:6}, null);

                layer.msg(result.message, {time: 1000, icon: 5, shift: 1}, function () {
                    $('#f_loginacct').val("");
                    $('#f_userpswd').val("");
                });
            }
        }
    });
}

function valid() {
    var tag = true;

    if ($.trim($('#f_loginacct').val()) == "") {
        $("#acct_feedback").html("账号不可为空,请输入登录账号");
        // layer.msg("账号不可为空,请输入登录账号", {time:1000, icon:5, shift:1}, function () {
        //     $('#f_loginacct').val("");
        // });
        $('#f_loginacct').val("");
        tag = false;
    } else {
        $("#acct_feedback").html("");
    }

    if ($('#f_userpswd').val() == "") {
        $("#pwd_feedback").html("密码不可为空，请输入密码");
        // layer.msg("密码不可为空，请输入密码", {time:1000, icon:5, shift:1}, function () {
        //     $('#f_loginacct').val("");
        // });
        tag = false;
    } else {
        $("#pwd_feedback").html("")
    }

    return tag;
}
