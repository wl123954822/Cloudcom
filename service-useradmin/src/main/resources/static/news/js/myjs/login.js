//管理员登录
httpurl="http://localhost:4444/api-a/login";
function adasd() {
    var us=$("#us").val();
    var pw=$("#pw").val();
    $.ajax({
        type : "POST",
        url : httpurl,
        data:{
            'username':us,
            'password':pw
        },
        success : function(data) {
			console.log(data)
            /*if (data.status=="success") {
                var last = JSON.stringify(data);

                //layer.msg(data.text);
                //confirm(data.text);
               alert(data.text);
                document.cookie = "token=" + data.token;
                window.location.href = 'index.html?t=' + data.token + '';
            }else {
                alert(data.text);
            }*/
        }
    });
}

