$(function () {
    doQueryPage(0);

    $("#authcert_table_body").on("click", "#table-verify", loadVerifyPage);
});

function loadVerifyPage() {
    var memberid = $(this).parent().parent().attr("memberId");
    var taskid = $(this).parent().parent().attr("taskId");

    $("#content_div").data("memberId", memberid);
    $("#content_div").data("taskId", taskid);

    $("#content_div").load("/authcert/verify.htm?memberid=" + memberid);
}