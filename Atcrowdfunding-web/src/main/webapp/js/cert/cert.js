$(function () {
    doQueryPage(0);

    $("#cert-search-btn").click(queryCert);
    $("#cert-add-btn").click(loadCertAddPage);
    $("#cert-delete-btn").click(doDeleteBatch);

    // $(":input:checkbox").click(checkboxLinkage);
    $("#content_div").on("click", ":input:checkbox", checkboxLinkage);

    $("#cert_table_body").on("click", "#table-remove", doDeleteCert);
    $("#cert_table_body").on("click", "#table-modify", loadEditPage);
});

function queryCert() {
    doQueryPage(0);
}

function loadCertAddPage() {
    $("#content_div").load("/cert/add.htm?t=" + Math.random());
}

// 选择框联动
function checkboxLinkage() {
    if($(this).attr("id")=="checkbox_all"){
        var checkboxs = $("#cert_table_body :checkbox");
        var status = $("#checkbox_all").prop("checked");

        checkboxs.prop("checked", status);
    }

    else {
        if($("#cert_table_body :checkbox:checked").length!=0){
            $("#checkbox_all").prop("checked", "checked");
        } else {
            $("#checkbox_all").prop("checked", "");
        }
    }
}

function doDeleteBatch() {
    // 获取所有已选择的选择框'
    var checkboxs = $("#cert_table_body :checkbox:checked");
    if(checkboxs.length == 0){
        alert("请至少选择一项资质");
        return false;
    }

    var flag = confirm("确认要删除这些资质么 [不可撤销]");
    if(!flag){
        return false;
    }

    var ids = [];

    checkboxs.each(function (i, n) {
        ids[i] = $(this).parent().parent().attr("certId");
    });

    $.ajax({
        url: '/cert/doDeleteBatch.do',
        data: {ids: ids},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功~");

                $("#role-top-checkbox").prop("checked", false);
                doQueryPage(0);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function doDeleteCert() {
    var id = $(this).parent().parent().attr("certId");

    var flag = confirm("确认要删除此条资质么 [不可撤销]");
    if(!flag){
        return false;
    }

    $.ajax({
        url: '/cert/doDeleteCert.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                alert("删除成功~");

                $("#role-top-checkbox").prop("checked", false);
                doQueryPage(0);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function loadEditPage() {
    var id = $(this).parent().parent().attr("certId");
    $("#content_div").data("certId", id);

    setTimeout(function () {
        $("#content_div").load("/cert/edit.htm?t="+Math.random());
    }, 500);
}
