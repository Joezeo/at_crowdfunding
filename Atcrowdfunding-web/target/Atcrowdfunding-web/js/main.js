var DEFAULT_COLOR;
$(function () {
    DEFAULT_COLOR = $("#li_user").css("color");
    loadPanelPage();

    $("#main-region").on("click", "#li_panel", loadPanelPage);
    $("#main-region").on("click", "#li_user", loadUserPage);
    $("#main-region").on("click", "#li_role", loadRolePage);
    $("#main-region").on("click", "#li_permission", loadPermissionPage);
    $("#main-region").on("click", "#li_advert", loadAdvertPage);
    $("#main-region").on("click", "#li_cert", loadCertPage);
    $("#main-region").on("click", "#li_process", loadProcessPage);

    controlMenu();
    // $("#li_user").click(loadUserPage);
    // $("#li_role").click(loadRolePage);
    // $("#li_permission").click(loadPermissionPage);
});

function controlMenu(){
    $("#main-region").on("click", ".list-group-item", function(){
        if ( $(this).find("ul") ) {
            $(this).toggleClass("tree-closed");
            if ( $(this).hasClass("tree-closed") ) {
                $("ul", this).hide("fast");
            } else {
                $("ul", this).show("fast");
            }
        }
    });
}

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
    $("#content_div").load("user.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载role页面
function loadRolePage() {
    $("#navbar_title").html("众筹平台 - 角色维护");
    $("#navbar_title").data("groupId", 2);
    $("#content_div").load("role.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载permission页面
function loadPermissionPage() {
    $("#navbar_title").html("众筹平台 - 许可维护");
    $("#navbar_title").data("groupId",3);
    $("#content_div").load("permission.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载广告管理页面
function loadAdvertPage(){
    $("#navbar_title").html("众筹平台 - 广告管理");
    $("#navbar_title").data("groupId",4);
    $("#content_div").load("advert.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载资质管理页面
function loadCertPage() {
    $("#navbar_title").html("众筹平台 - 资质管理");
    $("#navbar_title").data("groupId",5);
    $("#content_div").load("cert.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}

// 内容区域加载流程管理页面
function loadProcessPage() {
    $("#navbar_title").html("众筹平台 - 流程管理");
    $("#navbar_title").data("groupId",6);
    $("#content_div").load("process.htm?t="+Math.random());

    $(".list-group a").css("color", DEFAULT_COLOR);
    $(this).css("color","red");

    return false;
}
