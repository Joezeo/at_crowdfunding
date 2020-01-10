$(function () {
    $("#cert_add_refresh").click(doRefreshData);
    $("#cert_add_submit").click(doAddCert);
});

function doRefreshData() {
    $("#cert_name").val("");
}

function doAddCert() {
    var name = $("#cert_name").val();

    $.ajax({
        url: '/cert/doAddCert.do',
        data: {name: name},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                $("#content_div").load("/cert.htm?t="+Math.random());
                alert("添加资质成功");
            } else {
                alert(jsonResult.message);
            }
        }
    })
}