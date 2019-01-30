httpurl="http://localhost:4001/ad";
httpurl2="http://localhost:4001/sp";
$(function(){
    //加载js函数
    lodeData();
});

function lodeData() {
    var token=document.cookie.split(";")[0].split("=")[1];
    var url = location.search.toString();
    var  urlToken=url.substring(3).toString();
    if ( urlToken != token){
        alert("请登录");
        window.location.href='login.html';
    }else {jz()}
}

function jz(){
    $.ajax({
        type : "POST",
        url : httpurl2+"/order/showOrAl",
        success : function(data) {
            var lasta = JSON.stringify(data);

            if (data.status=="success") {
               var html='';

                html+='<span class="nav-label">待处理的订单</span>';
                html+='<span class="label label-warning pull-right">'+data.num+'</span>';

                $("#exampl2").append(html);
            }
        }
    });
}