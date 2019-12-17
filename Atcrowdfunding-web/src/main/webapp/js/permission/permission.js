var setting = {};
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