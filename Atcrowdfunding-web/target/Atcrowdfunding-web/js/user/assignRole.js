$(function () {
});

function loadUserRoles(userId) {
    if(userId <= 0){
        return false;
    }

    $.ajax({
        url: '/user/doGetUserRoles.do',
        data: {userId:userId},
        dataType: 'json',
        type: 'post',
        success: function (jsonResult) {
           if(jsonResult.success) {
              fillDataInSelectBoxs(jsonResult.data);
           } else {
               alert(jsonResult.message);
           }
        }
    })
}

function fillDataInSelectBoxs(map) {
   var leftRoleListData = map.leftRoleList;
   var rightRoleListData = map.rightRoleList;

   var leftRoleList = $("#leftRoleList");
   var rightRoleList = $("#rightRoleList");

   var content = "";
   for(var i=0; i<leftRoleListData.length; i++){
      content += "<option value='"+leftRoleListData[i].id+"'>"+leftRoleListData[i].name+"</option>"
   }
   leftRoleList.html(content);

   content = "";
    for(var i=0; i<rightRoleListData.length; i++){
        content += "<option value='"+rightRoleListData[i].id+"'>"+rightRoleListData[i].name+"</option>"
    }
    rightRoleList.html(content);
}