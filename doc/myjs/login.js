//管理员登录
httpurl="http://localhost:4444/adminlogin/adminlogin";
function toLogin() {
    var us=$("#us").val();
    var pw=$("#pw").val();
    $.ajax({
        xhrFields:{
            withCredentials:true
        },
        type : "POST",
        url : httpurl,
        traditional:true,
        data:{
           // 'token':'admin',
            'username':us,
            'password':pw
        },
        success : function(data) {
			console.log(data)
            if (data.result.status=="success") {
                //layer.msg(data.text);
                //confirm(data.text);
               window.location.href = 'index.html';
            }else {
               
             }
        }
    });
}

