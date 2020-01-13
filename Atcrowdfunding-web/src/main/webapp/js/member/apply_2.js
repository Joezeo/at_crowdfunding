$(function () {
    // 图片预览功能
    $(":file").change(function(event){
        var files = event.target.files;
        var file;

        if (files && files.length > 0) {
            file = files[0];
            var URL = window.URL || window.webkitURL;
            // 本地图片路径
            var imgURL = URL.createObjectURL(file);
            var imgObj = $(this).next().next(); //获取同辈紧邻的下一个元素
            //console.log(imgObj);
            imgObj.attr("src", imgURL);
            imgObj.show();
        }
    });

    $("#apply_2_back").click();
    $("#apply_2_forward").click(doSaveCert);
});

function doSaveCert() {
    var options = {
        url: "/member/doSaveCert.do",
        success: function (jsonResult) {
            if (jsonResult.success) {
                $("#content_div").load("/member/apply.do?t=" + Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    };

    $("#certfile-form").ajaxSubmit(options); //异步提交
    return false;
}
