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
            }else if(data.status=="success"){
                show();
                ad();
            }
        }
    });





}


//页面显示
function show() {
    var url = location.search.toString();
    var  itemId=url.substring(8).toString();

    $.ajax({
        type:"POST",
        url:httpurl2+"/item/listById",
        data:{
            id:itemId
        },
        success:function (data) {
            for (var i = 0; i < data.list.length; i++) {
                var d = data.list[i];
                var html = '';
                html+='<div class="ibox float-e-margins">';
                html+='<div class="ibox-title">';
                html+=' <h5>详情</h5>';
                html+='</div>';
                html+='<div >';
                html+='<div class="ibox-content no-padding border-left-right">';
                html+='<img alt="image" class="img-responsive" src="'+httpNginx+''+d.itemImage+'">';
                html+='</div>';
                html+='<div class="ibox-content profile-content">';
                html+='<h4><strong>'+d.itemName+'</strong></h4>';
                html+='<p><i class="fa fa-map-marker"></i>'+d.name+'</p>';
                html+='<h5>详情</h5>';
                html+='<p>'+d.itemIntroduce+'</p>';
                html+='<div class="user-button">';
                html+='<div class="row">';
                html+='<div class="col-sm-6">';
                html+='<button type="button" class="btn btn-primary btn-sm btn-block"><i class="fa fa-envelope"></i> 发送消息</button>';
                html+='</div>';
                html+='</div></div></div></div></div>'

                $("#showDetal").append(html);
            }
        }
    });
}

function ad(){

    var url = location.search.toString();
    var  itemId=url.substring(8).toString();
    var long;
    $.ajax({
        type:"POST",
        url:httpurl2+"/item/tj",
        data:{
            itemId:itemId
        },
        success:function (data) {
            var last = JSON.stringify(data);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('mainn'));

            // 指定图表的配置项和数据
            option = {
                title : {
                    text: '产品销量统计',
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['销售量']
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'销售量',
                        type:'bar',
                        data:data.longs,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },

                    },
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })


}