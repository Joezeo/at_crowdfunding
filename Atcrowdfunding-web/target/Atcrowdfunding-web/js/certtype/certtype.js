$(function () {
    loadCerttype();

    $(":checkbox").click(chooseCerttype);
});

function loadCerttype() {
    $.ajax({
        url: '/certtype/doLoadData.do',
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                fillDataInTable(jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function fillDataInTable(list) {
    $.each(list, function (i,n) {
        $(":checkbox[accttype='"+n.accttype+"'][certid='"+n.certid+"']").prop("checked",true);
    })

    // for (var i = 0; i < list.length; i++) {
    //     $(":checkbox[accttype='" + list[i].accttype + "'][certid='" + list[i].certid + "']")[0].checked=true;
    // }
}

function chooseCerttype() {
    var flag = $(this).prop("checked");
    var accttype = $(this).attr("accttype");
    var certid = $(this).attr("certid");
    var params = {accttype:accttype, certid:certid};
    if(flag){
        $.ajax({
            url: '/certtype/doAddCerttype.do',
            data: params,
            dataType: "json",
            type: 'post',
            success: function (jsonResult) {
                if(!jsonResult.success){
                    alert(jsonResult.message);
                    $(this).prop("checked", false);
                }
            }
        })
    } else {
        $.ajax({
            url: '/certtype/doRomoveCerttype.do',
            data: params,
            dataType: "json",
            type: 'post',
            success: function (jsonResult) {
                if(!jsonResult.success){
                    alert(jsonResult.message);
                    $(this).prop("checked", true);
                }
            }
        })
    }
}