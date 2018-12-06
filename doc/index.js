httpurl="http://localhost:4444/admin";
$(function(){
    //加载js函数
    tesCook();
});

function lodeData() {
   
}
// 定义一些全局变量


function jz(){
    $.ajax({
        type : "get",
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

// 左侧管理员显示
function showGly() {
    $.ajax({
        type:"POST",
        url:httpurl +"/user",
        data:{
            'token':'admin',
        },
        success : function(data) {
            console.log(data);
            var resultList;
            if (data.result.status == "success") {
                resultList = data.result.data.user;
                //为用户信息赋值
                $("#usernameXM").html(resultList.username);
                $("#userRoleXM").html(resultList.name);
                $("#usernameImgXM").attr("src",resultList.userface);
                var menuData = data.result.data.menu;
                showMenu(menuData);
            }
        }
    })
}
/* 
<li><a href="#"><i class="fa fa-home"></i><span class="nav-label">账号管理</span>
 <span class="fa arrow"></span> </a><ul class="nav nav-second-level">
  <li><a class="J_menuItem" href="index_v1.html" data-index="0">管理员添加</a>
 </li> <li><a class="J_menuItem" href="index_v2.html">管理员管理</a></li>
 <li> <a class="J_menuItem" href="index_v3.html">密码修改</a></li> </ul></li> */
function showMenu(menuData) {
    console.log(menuData);
    $.each(menuData, function (index, item) {
        console.log(item);
        var html = '';
        html+='<li><a href="#"><i class="fa fa-home"></i><span class="nav-label">'+item.name+'</span>';
        html+=' <span class="fa arrow"></span> </a><ul class="nav nav-second-level"></ul></li>';
        $("#menuShow").append(html);
    })
}



function tesCook () {
    var arr=[];
    arr.push(1,2,3,5);
    console.log(arr);
    $.ajax({
        xhrFields:{
            withCredentials:true
        },
        type : "post",
        url : "http://localhost:4444/cart/itemCart/cartAddNoLogin",
        traditional:true,
        data:{
            'ids': arr
        },
        success : function(data) {
           console.log(data);
           //sss(data.result.data)
        }
    });
}

function sss(daa) {
    var arr=[];
    arr.push(6,7);
    console.log(arr);
    $.ajax({
        xhrFields:{
            withCredentials:true
        },
        type : "post",
        url : "http://localhost:8888/itemCart/cartAddNoLogin",
        traditional:true,
        data:{
            'itemCart':'1c691412-f24a-46a5-9fa0-6baf5b830bab',
            'ids': arr
        },
        success : function(data) {
           console.log(data);
        }
    });
}