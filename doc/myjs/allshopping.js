httpNginx="http://localhost";
$(function(){
    //加载js函数
    itemList();

});

function itemList() {
    $.ajax({
        xhrFields:{
            withCredentials:true
        },
        type : "post",
        url :"http://localhost:4444/qa/commodityList",
        traditional:true,
        success : function(data) {
            console.log(data)
            for (var i = 0; i < data.result.data.length; i++) {
                var d = data.result.data[i];
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
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 911) {
                window.location.href = 'login.html';
            }
        },
    });
}