httpurl="http://localhost:4001/ad";
httpurl2="http://localhost/sp";
httpNginx="http://localhost/";

httpScUrl="http://localhost:9099"
$(function(){
    //加载js函数
    lodeData();
    showfenlei();
});
//页面登陆进行判断
function lodeData() {
    var c_start = document.cookie.indexOf("token" + "=");
    var c_name="token";
    var ds="";
    if (c_start != -1) {
        c_start = c_start + c_name.length + 1;
        c_end = document.cookie.indexOf(";", c_start);
        ds = document.cookie.substring(c_start, c_end);
    }
    $.ajax({
        type : "POST",
        url : httpurl+"/admin/check",
        data:{
            token:ds
        },
        success : function(data) {
            if (data.status=="error") {
                window.location.href = 'login.html';
            }
        }
    });

}


function ajaxFileUpload() {
    $.ajaxFileUpload({
        url : httpurl2+"/item/upload", //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId : 'file1', //文件上传域的ID
        dataType : 'json', //返回值类型 一般设置为json
        success : function(data, status) //服务器成功响应处理函数
        {
            window.console.log(data)

            var last = JSON.stringify(data);

            $("#dataImg").attr("src",httpNginx+data.path);
            $("#itemPic").text(data.path);
            if (typeof (data.error) != 'undefined') {
                if (data.error != '') {

                } else {

                }
            }
        },
        error: function (data, status, e) {

           window.console.log(data);
           window.console.log(status);
           window.console.log(e);
            var last = JSON.stringify(status);
            alert(last)
            alert(e);
        }
    })
    return false;
}

function butts() {
    ajaxFileUpload();
}


//商品分类下拉选择
function showfenlei(){
    $.ajax({
        type:"POST",
        url:httpurl2+"/item/clists",
        dataType:"json",
        success:function (data) {
            for (var i=0;i<data.list.length;i++) {
                d = data.list[i];
                var html = "";
                 html+='<option value="'+d.id+'">'+d.name+'</option>';
                 $("#example2").append(html);
            }
        }
    })
}


function tttjjj() {

    var cid =  $("#example2").val();

}