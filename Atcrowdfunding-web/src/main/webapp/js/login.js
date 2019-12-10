$(function () {
    $("#form_login_button").click(doLogin);
    $("#login_form").on("blur","#f_loginacct, #f_userpswd", valid);
});

function doLogin() {
   if(!valid()){
       return false;
   }

    var loginacct = $('#f_loginacct').val();
    var userpswd = $('#f_userpswd').val();
    var type = $('#f_type').val();

    var params = {loginacct:loginacct, userpswd:userpswd, type:type};
    $.ajax({
        url:'/doLogin.do',
        type:'post',
        dataType:'json',
        data:params,
        success:function (result) {
            if(result.success){
                location.href = "/main.htm";
            } else {
                $("#acct_feedback").html("");
                $("#pwd_feedback").html("");
                // layer.msg(result.message, {time:1000, icon:5, shift:6}, null);

                layer.msg(result.message, {time:1000, icon:5, shift:1}, function () {
                    $('#f_loginacct').val("");
                    $('#f_userpswd').val("");
                });
            }
        }
    });
}

function valid() {
    var tag = true;

    if($.trim($('#f_loginacct').val()) == ""){
        $("#acct_feedback").html("账号不可为空,请输入登录账号");
        // layer.msg("账号不可为空,请输入登录账号", {time:1000, icon:5, shift:1}, function () {
        //     $('#f_loginacct').val("");
        // });
        $('#f_loginacct').val("");
        tag = false;
    } else {
        $("#acct_feedback").html("");
    }

    if($('#f_userpswd').val() == ""){
        $("#pwd_feedback").html("密码不可为空，请输入密码");
        // layer.msg("密码不可为空，请输入密码", {time:1000, icon:5, shift:1}, function () {
        //     $('#f_loginacct').val("");
        // });
        tag =  false;
    } else {
        $("#pwd_feedback").html("")
    }

    return tag;
}
