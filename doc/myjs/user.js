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
                initTable();

            }
        }
    });

}



function initTable() {
    //先销毁表格
    // $("#talll").bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#tall22").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: httpurl2+"/wxuser/list", //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType : "undefined",
        events: actionEvents
    });

}

function actionFormatter1(value, row, index) {
    return [
        '<input type="submit" value="查看订单详情"  class="RoleOfedit btn btn-primary btn-sm"   data-toggle="modal" style="display:inline">',
    ].join('');
}
window.actionEvents = {
    'click .RoleOfedit': function (e, value, row, index) {
        $("#myModa2").modal('show');
        var html2='';
        $("#shodis").empty();
        var last = JSON.stringify(row);
        $.ajax({
            type : "POST",
            url : httpurl2+"/order/orderAlLis",
            data:{
                orderId:row.orderId,
                areaId:row.areaId,
                userId:row.userId,
                statusName:row.statusName
            },
            success : function(data) {

                var lasta = JSON.stringify(data);
                for (var i=0;i<data.list.length;i++){
                    d=data.list[i];
                    var html="";
                    html+='<div style="width: 830px">';
                    html+='<a class="fancybox" href="'+httpNginx+'' + d.itemImage + '" title="'+d.itemName+'">';
                    html+='<img alt="image" src="'+httpNginx+'' + d.itemImage + '" />';
                    html+='</a>';
                    html+='</div>';
                    html+='<h3><strong>'+d.itemName+'</strong></h3>';
                    html+='<dl class="dl-horizontal">';
                    html+='<dt>状态：</dt>';
                    html+='<dd><span class="label label-primary">'+d.statusName+'</span>';
                    html+='</dd>';
                    html+='</dl>';
                    html+=' <dl class="dl-horizontal">';
                    html+='<dt>单价：</dt>';
                    html+='<dd>'+d.itemPrice+'</dd>';
                    html+='<dt>订购数量：</dt>';
                    html+='<dd>'+d.quantity+'</dd>';
                    html+='<dt>用户：</dt>';
                    html+='<dd>'+d.nickName+'</dd>';
                    html+='<dt>收货人：</dt>';
                    html+='<dd>'+d.consignee+'</dd>';
                    html+='<dt>联系电话：</dt>';
                    html+='<dd>'+d.telephone+'</dd>';
                    html+='<dt>收货地址：</dt>';
                    html+='<dd>'+d.province+'-'+d.city+'-'+d.area+'-'+d.detailArea+'</dd>';
                    html+='</dl>';
                    $("#shodis").append(html);

                }


            }
        });

    }
};

function deleteCl() {
    $('#tall22').on("click-row.bs.table",function(e,row,$element) {
        var last = JSON.stringify(row);

        alert(last)
    });

}




