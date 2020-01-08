$(function () {
    doQueryPage(0);

    $("#cert-search-btn").click(queryCert);
    $("#cert-add-btn").click(loadCertAddPage);
});

function queryCert() {
    doQueryPage(0);
}

function loadCertAddPage() {
    $("#content_div").load("/cert/add.htm?t="+Math.random());
}
