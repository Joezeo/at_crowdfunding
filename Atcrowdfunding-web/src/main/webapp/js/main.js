$(function () {
    loadPanelPage();

    $("#li_user").click(loadUserPage);
});

// 内容区域加载panel页面
function loadPanelPage() {
    $("#navbar_title").html("众筹平台 - 控制面板");
    $("#content_div").load("panel.htm?"+Math.random());

    return false;
}

// 内容区域加载user页面
function loadUserPage() {
    $("#navbar_title").html("众筹平台 - 用户维护");
    $("#content_div").load("user.htm?"+Math.random());

    return false;
}