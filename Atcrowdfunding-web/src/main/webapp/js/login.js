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
                alert(result.message);
            }
        }
    });
}

function valid() {
    var tag = true;

    if($.trim($('#f_loginacct').val()) == ""){
        $("#acct_feedback").html("账号不可为空,请输入登录账号");
        $('#f_loginacct').val("");
        tag = false;
    } else {
        $("#acct_feedback").html("");
    }

    if($('#f_userpswd').val() == ""){
        $("#pwd_feedback").html("密码不可为空，请输入密码");
        tag =  false;
    } else {
        $("#pwd_feedback").html("")
    }

    return tag;
}
