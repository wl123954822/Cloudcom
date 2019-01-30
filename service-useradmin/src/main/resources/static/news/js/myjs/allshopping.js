httpurl="http://localhost:4001/ad";
httpurl2="http://localhost:4001/sp";
httpNginx="http://localhost";
$(function(){
    //加载js函数
    lodeData();

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
                }else if (data.status=="success"){
                    itemList();
                }
            }
        });

}

function itemList() {
    $.ajax({
        type : "POST",
        url : httpurl2+"/item/list",
        success : function(data) {
            for (var i = 0; i < data.list.length; i++) {
                var d = data.list[i];
                var html = '';
                html+='<div class="col-sm-4" >';
                html+='<div class="contact-box" >';
                html += '<a href="profile.html?itemId='+d.id+'">';
                html+='<div class="col-sm-4" id="pic">';
                html+='<div class="text-center">';
                html+='<img alt="image" class="img-circle m-t-xs img-responsive" src="'+httpNginx+'' + d.itemImage + '">';
                html+='<div class="m-t-xs font-bold">'+d.name+'</div>';
                html+='</div>';
                html+='</div>';
                html+='<div class="col-sm-8">';
                html+='<h3><strong>'+d.itemName+'</strong></h3>';
                html+='<p><i class="fa fa-map-marker"></i> 甘肃·兰州</p>';
                html+='<address>';
                html+='<strong>单价, '+d.itemPrice+'</strong><br>';
                html+='总数量:'+d.itemNum+'<br>';
                html+='<abbr title="Phone">创建时间:</abbr> '+d.createDate+'';
                html+='</address>';
                html+='</div>';
                html+='<div class="clearfix"></div>';
                html+='</a>';
                html+='</div>';
                html+='</div>';
                $("#itemm").append(html);
            }
        }
    });
}