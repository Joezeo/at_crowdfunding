$(function () {
    $("#advert-add-refresh").click(resetTableData);
    $("#advert-add-submit").click(doAddAdvert);
});

function resetTableData() {
    $("#name").val("");
    $("#url").val("");
    $("#advpic").val("");
}

function doAddAdvert() {
    var options = {
        url:"/advert/doAddAdvert.do",
        success : function(jsonResult){
            if(jsonResult.success){
                $("#content_div").load("/advert.htm?t="+Math.random());
                alert("新增广告数据成功");
            }else{
                alert(jsonResult.message);
            }
        }
    };

    $("#advertForm").ajaxSubmit(options); //异步提交
    return ;
}