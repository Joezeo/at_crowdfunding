var DEFAULT_COLOR;
$(function () {
    DEFAULT_COLOR = $("#li_user").css("color");
    loadPanelPage();

    $("#li_user").click(loadUserPage);

    $("#li_role").click(loadRolePage);
});

// 内容区域加载panel页面
function loadPanelPage() {
    $("#navbar_title").html("众筹平台 - 控制面板");
    $("#content_div").load("panel.htm?t="+Math.random());

    return false;
}

// 内容区域加载user页面
function loadUserPage() {
    $("#navbar_title").html("众筹平台 - 用户维护");
    $("#navbar_title").data("groupId", 1);
    $("#content_div").data("pageNum", 1);
    $("#content_div").load("user.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载role页面
function loadRolePage() {
    $("#navbar_title").html("众筹平台 - 角色维护");
    $("#navbar_title").data("groupId", 2);
    $("#content_div").data("pageNum", 1);
    $("#content_div").load("role.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}
