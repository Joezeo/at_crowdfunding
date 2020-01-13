$(function () {
    $("#apply_4_finish").click(doVerifyAuthcode);
});

function doVerifyAuthcode() {
    var authcode = $("#authcode").val();
    if(authcode == ""){
        alert("验证码不可为空，请输入");
        return false;
    }

    $.ajax({
        url: '/member/doVerifyAuthcode.do',
        data: {authcode:authcode},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                // 重回member主页面
                $("#content_div").load("/member/home.htm?t="+Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    })
}
