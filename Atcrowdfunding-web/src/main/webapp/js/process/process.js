$(function () {
    doQueryPage(0);

    $("#process-search-btn").click(doSearch);
    $("#process-upload-btn").click(doUploadFile);

    $("#process-upload-file").change(doAddProcess);

    $("#process_table_body").on("click", "#table-see", doLoadProcessPic);
    $("#process_table_body").on("click", "#table-remove", doDeleteProcess);
});

function doSearch() {
    doQueryPage(0);
}

function doUploadFile() {
    $("#process-upload-file").click();
}

function doAddProcess() {
    var options = {
        url: "/process/doAddProcess.do",
        success: function (jsonResult) {
            if (jsonResult.success) {
                $("#content_div").load("/process.htm?t=" + Math.random());
                alert("新增流程成功");
            } else {
                alert(jsonResult.message);
            }
        }
    };

    $("#process-upload-form").ajaxSubmit(options); //异步提交
    return false;
}

function doLoadProcessPic() {
    var id = $(this).parent().parent().attr("processId");
    $("#mymodal").modal('show');
    $("#process-pic-img").attr("src", "/process/doLoadProcessPic.do?id="+id);
}

function doDeleteProcess() {
    var flag = confirm("确定要删除该条数据么 [不可撤销]");
    if(!flag){
        return false;
    }

    var id = $(this).parent().parent().attr("processId");

    $.ajax({
        url: '/process/doDeleteProcess.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                doQueryPage(0);
                alert("删除流程成功h");
            } else {
                alert(jsonResult.message);
            }
        }
    })
}