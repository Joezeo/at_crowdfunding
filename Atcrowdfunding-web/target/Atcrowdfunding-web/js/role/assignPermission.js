var setting = {
    view : {

        addDiyDom: function(treeId, treeNode){

            var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")

            if ( treeNode.icon ) {

                icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");

            } else {
                icoObj.addClass(treeNode.icon).css("background","");

            }

        }
    },
    check: {
        enable: true
    }

};

var id = $("#treeDemo").data("roleid");

$(function () {

    doGetPermissions();
    // 给分配权限按钮分配点击事件
    $("#assignPermissionBtn").click(doAssignPermission);

});
function doGetPermissions() {
    $.ajax({
        url: '/role/doGetPermissions.do?t='+Math.random(),
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                // 初始化zTree
                $.fn.zTree.init($("#treeDemo"), setting, jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function doAssignPermission() {
    // 获取zTree对象
    var ztreeObj = $.fn.zTree.getZTreeObj("treeDemo");
    // 获取已选择的节点
    var checked = ztreeObj.getCheckedNodes();

    if(checked.length == 0){
        alert("请至少选择一个权限：控制面板");
        return false;
    }

    var ids=[];
    var flag = false; // 判断控制面板是否被选中的标识
    $.each(checked, function (i,n) {
        ids[i] = n.id;

        if(n.id == 2){
            flag = true;
        }
    });

    if(!flag){
        alert("控制面板必须被分配！！");
        return false;
    }

    $.ajax({
        url: '/role/doAssignPermission.do',
        data: {id:id, ids:ids},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if(jsonResult.success){
                doGetPermissions();
                alert("权限分配成功");
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

