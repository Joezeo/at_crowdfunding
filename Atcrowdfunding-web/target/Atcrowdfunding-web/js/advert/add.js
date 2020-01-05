$(function () {
    $("#advert-add-refresh").click(resetTableData);
    $("#advert-add-submit").click(doAddAdvert);
    $("#advpic").change(changepic);
});

function resetTableData() {
    $("#name").val("");
    $("#url").val("");
    $("#advpic").val("");
    $("#show").attr("src", "");
}

function doAddAdvert() {
    var name = $("#name").val();
    var url = $("#url").val();
    var advpic = $("#advpic").val();

    if(name == null || name== "" || url==null||url==""||advpic==null||advpic==""){
        alert("数据不可为空,请完成所有数据的填写");
        return false;
    }

    var options = {
        url: "/advert/doAddAdvert.do",
        success: function (jsonResult) {
            if (jsonResult.success) {
                $("#content_div").load("/advert.htm?t=" + Math.random());
                alert("新增广告数据成功");
            } else {
                alert(jsonResult.message);
            }
        }
    };

    $("#advertForm").ajaxSubmit(options); //异步提交
    return false;
}

function changepic() {
    var f = document.getElementById('advpic').files[0];

    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(f);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(f);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(f);
    }

    $("#show").attr("src", url);
}