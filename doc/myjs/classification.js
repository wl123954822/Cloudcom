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
            }else if (data.status="success"){
                initTable1();
            }
        }
    });

}

function fenlei() {
    $('#myModa2').modal();
}

function initTable1() {
    $("#talll").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: httpurl2+"/item/clist", //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 8,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        sidePagination: "server", //表示服务端请求
        queryParamsType : "undefined",
    });
}

function actionFormatter(value,row) {
    var c='<a href="#" onclick="deleteCl()">删除</a>';
    return c;
}

//删除分类
function deleteCl() {

    $('#talll').on("click-row.bs.table",function(e, row, $element) {
        var id=row.id;
        $.ajax({
            type:"POST",
            url:httpurl2+"/item/delete",
            data:{
                id:id
            },
            dataType:"json",
            success:function (data) {
                var last = JSON.stringify(data);
                alert(last)
                $('#talll').bootstrapTable('refresh');
            }
        })
    });

}

//更新分类显示
function updatee() {
    $('#talll').on("click-row.bs.table",function(e, row, $element) {
        var id=row.id;
        $.ajax({
            type:"POST",
            url:httpurl2+"/item/update",
            data:{
                id:id,
                status:1
            },
            dataType:"json",
            success:function (data) {
                var last = JSON.stringify(data);
                $('#talll').bootstrapTable('refresh');
            }
        })
    });
}
//更新分类隐藏
function updateq() {
    $('#talll').on("click-row.bs.table",function(e,row,$element) {
        var id=row.id;

        $.ajax({
            type:"POST",
            url:httpurl2+"/item/update",
            data:{
                id:id,
                status:0
            },
            dataType:"json",
            success:function (data) {
                var last = JSON.stringify(data);
                $('#talll').bootstrapTable('refresh');
            }
        })
    });
}

//添加分类
function addfenl() {
    var cname=$("#txt_departmentname1").val();
    $.ajax({
        type:"POST",
        url:httpurl2+"/item/add",
        data:{
            name:cname,
        },
        dataType:"json",
        success:function (data) {
            var last = JSON.stringify(data);
            alert(last);
            var cname=$("#txt_departmentname1").val('');
            $('#talll').bootstrapTable('refresh');
        }
    })
}