var accttype = 0;
$(function () {
    $(".thumbnail").click(chooseType);
    $("#choose-accttype-btn").click(doUpdateAccttype);
});

function chooseType() {
    $('.seltype').remove();
    $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');

    accttype = $(this).attr("accttype");
}

function doUpdateAccttype() {
    $.ajax({
        url: '/member/doUpdateAccttype.do',
        data: {accttype: accttype},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                // content_div内容区域加载下一个步骤网页
                $("#content_div").load("/member/apply.do?t="+Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    });
}