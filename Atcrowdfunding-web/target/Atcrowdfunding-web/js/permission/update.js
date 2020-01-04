$(function () {
    // 需要修改的权限信息回显
    echoPermission();

    // 绑定点击事件
    $("#permission-icon-ul a").click(chooseIcon);
    $("#permission-update-refresh").click(resetTable);
    $("#permission-update-submit").click(updatePermission);
});

function echoPermission() {
    var id = $("#content_div").data("permissionId");

    $.ajax({
        url: 'permission/doGetOne.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                tableLoadData(jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function tableLoadData(data) {
    $("#permission-name").val(data.name);
    $("#permission-url").val(data.url);
    $("#permission-frontId").val(data.frontId);
    $("#title-span").html(data.icon);
}

function chooseIcon() {
    var iconName = $(this).parent().attr("icon");
    $("#title-span").html(iconName);
}

function resetTable() {
    $("#permission-name").val("");
    $("#permission-url").val("");
    $("#permission-frontId").val("");
    $("#title-span").html("请选择权限图标");
}

function updatePermission() {
    var id = $("#content_div").data("permissionId");
    var name = $("#permission-name").val();
    var url = $("#permission-url").val();
    var frontId = $("#permission-frontId").val();
    var icon = "";
    icon = $("#title-span").html();

    if(name == "" || name==null || url == "" || url==null || icon=="请选择权限图标"){
        alert("以上数据栏目不可为空，请检查后重试");
        return false;
    }

    $.ajax({
        url: '/permission/doUpdate.do',
        data: {
            id: id,
            name: name,
            url: url,
            icon: icon,
            frontId: frontId
        },
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                $("#content_div").load("/permission.htm?t=" + Math.random());
            } else {
                alert(jsonResult.message);
            }
        }
    })
}
