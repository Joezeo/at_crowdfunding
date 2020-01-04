$(function () {
    $("#permission-icon-ul a").click(chooseIcon);
    $("#permission-add-refresh").click(resetTable);
    $("#permission-add-submit").click(addPermission);
});

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

function addPermission() {
    var pid = $("#content_div").data("permissionId");
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
        url: '/permission/doAddPermission.do',
        data: {
            pid: pid,
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