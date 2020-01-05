$(function () {
    doQueryPage();

    $("#advert-search-btn").click(function () {
        doQueryPage();
    });

    $("#advert-add-btn").click(doLoadAddPage);
});

function doLoadAddPage() {
    $("#content_div").load("/advert/add.htm?t="+Math.random());
}