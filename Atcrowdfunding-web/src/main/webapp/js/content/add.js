$(function () {
   $("#refresh").click(cleanTable);
   $("#add-submit").click(doAddUser);
});

// 按重置按钮清空文本框内容
function cleanTable() {
   $("#f_loginAcct").val("");

   $("#f_username").val("");

   $("#f_email").val("");
}

// 点击新增按钮执行添加用户操作
function doAddUser() {
   var loginAcct = $("#f_loginAcct").val();
   var username = $("#f_username").val();
   var email = $("#f_email").val();

   var params = {loginacct:loginAcct, username:username, email:email};

   validData();
   $.ajax({
      url:'/user/doAddUser.do',
      data: params,
      dataType: 'json',
      type: 'post',
      success: function (jsonResult) {
        if(jsonResult.success) {
           alert("success");
           $("#content_div").load("user.htm?t="+Math.random());
        } else {
           alert(jsonResult.message);
        }
      }
   })
}

// 检查文本框内的内容是否为空
function validData() {
   if($("#f_loginAcct").val() == "" || $("#f_username").val() == "" || $("#f_email").val() == ""){
      alert("以上内容不允许有空白，请检查后再次添加！")
   }
}
