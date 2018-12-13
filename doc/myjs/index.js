httpurl="http://localhost:4444/admin";
$(function(){
    //加载js函数
    showMenuList();
});

function showMenuList() {
    $.ajax({
        xhrFields:{
            withCredentials:true
        },
        type : "post",
        url :"http://localhost:4444/qa/menulist",
        traditional:true,
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 911) {
                window.location.href = 'login.html';
            }
        },
        success : function(data) {
          var resultList = data.result.data.user;
          console.log(resultList);
            $("#usernameXM").html(resultList.username);
            $("#userRoleXM").html(resultList.name);
            $("#usernameImgXM").attr("src",resultList.userface);
        },
       
    });
}


