$(function () {
    //加载js函数

    initTable();
});

function initTable() {
    //先销毁表格
    // $("#talll").bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#tall22").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: "allUser", //获取数据的Servlet地址
        striped: true,  //表格显示条纹
        pagination: true, //启动分页
        striped: true,//隔行换色
        pageSize: 5,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showFooter: true,
        showRefresh: false,  //显示刷新按钮
        showToggle: false,
        sidePagination: "server", //表示服务端请求
        queryParamsType: "undefined",
        columns:[
            {
                field: "id",
            },
            {
                field: "name",
            },
            {
                field: "username",
            },
            {
                field: "enabled",
                formatter: function (value, row, index) {
                    if (value == true) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                },
            },
            {
                field: "remark",
            },
            {
                field: 'action',
                title: '操作',
                formatter: function (value, row, index) {
                    //return '<a href="javascript:void(0);" class="say_hi">点我</a>';
                    return [
                        '<input type="submit" value="修改" id="update" class="RoleOfedit btn btn-danger btn-sm"   data-toggle="modal" style="display:inline">',
                        '<input type="submit" value="删除"  id="del" class="RoleOfedit btn btn-warning btn-sm"   data-toggle="modal" style="display:inline" >',
                        '<input type="submit" value="授权"  id="sq" class="RoleOfedit btn btn-success btn-sm"   data-toggle="modal" style="display:inline" >'
                    ];
                },
                events: {
                    'click #update' :function (e,value,row,index) {
                        window.location.href="finance";
                    },
                }
            }
        ]
        // events: actionEvents
    });

}



