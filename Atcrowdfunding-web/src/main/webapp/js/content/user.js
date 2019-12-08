$(function () {
    $("#search-button").click(doSearch);
});

/**
 * 点击查询按钮
 */
function doSearch() {
    //去掉搜索条件的空格
    var loginAcct = $("#search-condition").val().trim();

    // 如果没有输入任何值或者输入了空格 将输入框重置
    if(loginAcct == "") {
        $("#search-condition").val("");
    }

    doQueryPage();
}
