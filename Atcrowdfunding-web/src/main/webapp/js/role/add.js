$(function () {
    $("#role-add-refresh").click(refreshTable);

    $("#role-add-submit").click(doAddRole);
});

function refreshTable() {
    $("#role-name").val("");
}

function doAddRole() {
    var name  = $("#role-name").val().trim();
    if(name == ""){
        alert("请不要提交空的表单");
        return false;
    }

    $.ajax({
        url: '/role/doAddRole.do',
        data: {name:name},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                alert(jsonResult.message);
                $("#content_div").data("pageNum", 1);
                $("#content_div").load("role.htm?t="+Math.random());
            }
            else {
                alert(jsonResult.message);
            }
        }
    })
}