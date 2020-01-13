$(function () {
    $("#apply_3_back").click();
    $("#apply_3_forward").click(doStartProcess);
});

function doStartProcess() {
    var email = $("#memberEmail").val();
    if (email == "") {
        alert("邮箱地址不可为空");
        return false;
    }

    $.ajax({
        url: '/member/doStartProcess.do',
        data: {email: email},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                $("#content_div").load("/member/apply.do?t=" + Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    })
}