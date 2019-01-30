$(function(){
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
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 5,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [5, 10, 15, 20, 25],  //记录数可选列表
        search: false,  //是否启用查询
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        showToggle:false,
        sidePagination: "server", //表示服务端请求
        queryParamsType : "undefined"
       // events: actionEvents
    });

}

function actionFormatter1(value, row, index) {
    return [
        '<input type="submit" value="修改"  class="RoleOfedit btn btn-danger btn-sm"   data-toggle="modal" style="display:inline" onclick="update(index)">',
        '<input type="submit" value="删除"  class="RoleOfedit btn btn-warning btn-sm"   data-toggle="modal" style="display:inline" onclick="del(index)">',
        '<input type="submit" value="授权"  class="RoleOfedit btn btn-success btn-sm"   data-toggle="modal" style="display:inline" onclick="sq(index)">'
    ].join('');
}
function del(value) {
    alert(value);
}

