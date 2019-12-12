var userid;
$(function () {
    $("#left-right-btn").click(addUserRoleMappr);

    $("#right-left-btn").click(removeUserRoleMappr);

    $("#left-right-all-btn").click(addAllRoles);

    $("#right-left-all-btn").click(removeAllRoles);

    // 绑定双击事件
    $("#leftRoleList").on("dblclick", ".left-dbclick", addOneRole);
    $("#rightRoleList").on("dblclick", ".right-dbclick", removeOneRole);
});

/**
 * 加载用户-角色映射信息
 *
 * @param userId
 * @returns {boolean}
 */
function loadUserRoles(userId) {
    if (userId <= 0) {
        return false;
    }

    userid = userId;

    $.ajax({
        url: '/user/doGetUserRoles.do',
        data: {userId: userId},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                fillDataInSelectBoxs(jsonResult.data);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

/**
 * 在selectBox中填充数据
 *
 * @param map
 */
function fillDataInSelectBoxs(map) {
    var leftRoleListData = map.leftRoleList;
    var rightRoleListData = map.rightRoleList;

    var leftRoleList = $("#leftRoleList");
    var rightRoleList = $("#rightRoleList");

    var content = "";
    for (var i = 0; i < leftRoleListData.length; i++) {
        content += "<option value='" + leftRoleListData[i].id + "' class='left-dbclick'>" + leftRoleListData[i].name + "</option>"
    }
    leftRoleList.html(content);

    content = "";
    for (var i = 0; i < rightRoleListData.length; i++) {
        // 这里放的是Role的id和name
        content += "<option value='" + rightRoleListData[i].id + "' class='right-dbclick'>" + rightRoleListData[i].name + "</option>"
    }
    rightRoleList.html(content);
}

/**
 * 添加用户-角色映射
 */
function addUserRoleMappr() {
    // 获取已被选择的角色option对象
    var roles = $("#leftRoleList option:selected");

    if(roles.length == 0){return false;}

    var params = {userid: userid};
    var idArr = [];
    roles.each(function (i, n) {
        idArr.push($(this).val());
    });
    params.idArr = idArr;

    $.ajax({
        url: '/user/doAddUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

/**
 * 删除用户-角色映射
 * @returns {boolean}
 */
function removeUserRoleMappr() {
    // 获取已被选择的角色option对象
    var roles = $("#rightRoleList option:selected");

    if(roles.length == 0){return false;}

    var params = {userid: userid};
    var idArr = [];
    roles.each(function (i, n) {
        idArr.push($(this).val());
    });
    params.idArr = idArr;

    $.ajax({
        url: '/user/doRemoveUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

/**
 * 添加所有用户角色
 */
function addAllRoles() {

    var roles = $("#leftRoleList option");

    if(roles.length == 0){return false;}

    var params = {userid: userid};
    var idArr = [];
    roles.each(function (i, n) {
        idArr.push($(this).val());
    });
    params.idArr = idArr;

    $.ajax({
        url: '/user/doAddUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

/**
 * 删除所有用户-角色映射
 * @returns {boolean}
 */
function removeAllRoles() {

    var roles = $("#rightRoleList option");

    if(roles.length == 0){return false;}

    var params = {userid: userid};
    var idArr = [];
    roles.each(function (i, n) {
        idArr.push($(this).val());
    });
    params.idArr = idArr;

    $.ajax({
        url: '/user/doRemoveUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function addOneRole() {
    var idArr = [$(this).val()];
    var params = {userid:userid,idArr:idArr};

    $.ajax({
        url: '/user/doAddUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}

function removeOneRole() {
    var idArr = [$(this).val()];
    var params = {userid:userid,idArr:idArr};

    $.ajax({
        url: '/user/doRemoveUserRoleMapper.do',
        data: params,
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
            if (jsonResult.success) {
                loadUserRoles(userid);
            } else {
                alert(jsonResult.message);
            }
        }
    })
}