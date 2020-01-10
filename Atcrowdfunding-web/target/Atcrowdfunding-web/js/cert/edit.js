var cert;

$(function () {
    loadCertInfo();

    $("#cert_edit_refresh").click(refreshEditTable);
});

function loadCertInfo() {
    var id = $("#content_div").data("certId");

    $.ajax({
        url: '/cert/doQueryCert.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                cert = jsonResult.data;
                fillCertInfo();
            } else {
                alert(jsonResult.message);
            }
        }
    });
}

function fillCertInfo() {
    $("#cert_name").val(cert.name);
}

function refreshEditTable() {
    $("#cert_name").val(cert.name);
}