var setting = {
    view : {

        addDiyDom: function(treeId, treeNode){

            var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")

            if ( treeNode.icon ) {

                icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");

            } else {
                icoObj.addClass(treeNode.icon).css("background","");

            }

        },
        addHoverDom: function(treeId, treeNode){
            var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
            aObj.attr("href", "javascript:void(0);");
            if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
            var s = '<span id="btnGroup'+treeNode.tId+'">';
            if ( treeNode.level == 0 ) { // 根节点
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="doAddPermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
            } else if ( treeNode.level == 1 ) { // 分支节点
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" title="修改权限信息" onclick="doModifyPermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                if (treeNode.children.length == 0) {
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="doDeletePermission('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                }
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="doAddPermission('+treeNode.id+')">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
            } else if ( treeNode.level == 2 ) { // 叶子节点
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" title="修改权限信息" onclick="doModifyPermission('+treeNode.id+')" >&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="doDeletePermission('+treeNode.id+',\''+treeNode.name.toString()+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
            }

            s += '</span>';
            aObj.after(s);
        },
        removeHoverDom: function(treeId, treeNode){
            $("#btnGroup"+treeNode.tId).remove();
        }
    }

};
$(function () {
    doGetPermissions();
});

function doGetPermissions() {
    $.ajax({
        url: '/permission/doGetAll.do?t='+Math.random(),
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

function doAddPermission(id) {
    $("#content_div").load("/permission/add.htm?t="+Math.random());
    $("#content_div").data("permissionId", id);
    return false;
}

function doDeletePermission(id, name) {
    var flag = confirm("确定要删除["+name+"]该条权限么");
    if(!flag){
        return false;
    }

    $.ajax({
        url: '/permission/doDeletePermission.do',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success:function (jsonResult) {
            if(jsonResult.success){
                doGetPermissions();
            } else {
                alert(jsonResult.message);
            }
        }
    });

    return false;
}

function doModifyPermission(id) {
    // 首先将id绑定在content_div上，然后跳转到修改页面数据回显
    $("#content_div").data("permissionId", id);
    $("#content_div").load("/permission/update.htm?t="+Math.random());
    return false;
}