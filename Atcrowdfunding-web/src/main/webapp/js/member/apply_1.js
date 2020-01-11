$(function () {
    $("#apply_1_back").click();
    $("#apply_1_forward").click(doSaveBasicInfo);
});

function doSaveBasicInfo() {
    var realname = $("#member-input-realname").val();
    var cardnum = $("#member-input-cardnum").val();
    var phone = $("#member-input-phone").val();

    if(realname == "" || cardnum == "" || phone==""){
        alert("数据不可为空，请检查后重试");
        return false;
    }

    var params = {realname:realname, cardnum:cardnum, phone:phone};

    $.ajax({
        url: '/member/doSaveBasicInfo.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                $("#content_div").load("/member/apply.do?t="+Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    })
    return false;
}