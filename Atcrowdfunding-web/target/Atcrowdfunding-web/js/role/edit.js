var roleId;
var name;
$(function () {
    roleId = $("#content_div").data("roleId"); // 获取当前要修改的角色id

    doQueryRole();  // 从数据库查询当前角色的最新状态,防止该角色已被修改

    $("#role-edit-refresh").click(refreshEditTable);

    $("#role-edit-submit").click(doModifyRole);
});

function doQueryRole() {
    $.ajax({
        url: '/role/doQueryRole.do',
        data: {id:roleId},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                name = jsonResult.data.name;
                $("#role-name").val(name);
            } else {
                alert("获取角色信息失败，请稍后重试");
                $("#content_div").data("pageNum",1);
                $("#content_div").load('/role.htm?t='+Math.random());
            }
        }
    })
}

function refreshEditTable() {
    $("#role-name").val(name);
}

function doModifyRole() {
    var newName = $("#role-name").val().trim();
    if(name == newName){
        alert("请不要提交相同的数据");
        return false;
    }

    $.ajax({
        url: '/role/doModifyRole.do',
        data: {id: roleId, name:newName},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                alert("修改角色信息成功");

                $("#content_div").data("pageNum",1);
                $("#content_div").load('/role.htm?t='+Math.random());
            } else {
                alert("修改角色信息失败，请稍后重试");
            }
        }
    })
}