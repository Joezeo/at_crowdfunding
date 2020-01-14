$(function () {
    $("#verify-pass").click(passVerify);
    $("#verify-resuse").click(refuseVerify);
});

function passVerify(){
    verify(true);
}

function refuseVerify() {
    verify(false);
}

function verify(flag) {
    var memberid = $("#content_div").data("memberId");
    var taskid = $("#content_div").data("taskId");

    $.ajax({
        url: '/authcert/doVerify.do',
        data: {flag: flag, memberid: memberid, taskid: taskid},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                $("#content_div").load("/authcert.htm?t="+Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    })
}