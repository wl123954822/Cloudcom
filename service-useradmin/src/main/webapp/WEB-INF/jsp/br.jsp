<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="${ctx}/css/main.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
</head>

<body class="main">
<!--top begin-->
<div class="navbar navbar-duomi navbar-static-top" role="navigation">
    <div class="navbar-inner">
        <div class="container" style="height: 45px">
            <a class="brand" href="index.html"><span style="position: absolute;padding:10px">Edmin</span></a>
            <div class="nav-collapse  navbar-inverse-collapse">
                <ul class="nav pull-right" style="margin-top: -28px">
                    <li class="nav-user"><a href="#"  data-toggle="dropdown">
                        <img src="images/user.png" class="nav-avatar">
                        <b class="caret"></b>AA</a>
                         <ul class="dropdown-menu">
                            <li><a href="#">Your Profile</a></li>
                            <li><a href="#">Edit Profile</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </li>
                 </ul>
            </div>
        </div>
    </div>
</div>
<!--top end-->
<div class="row">
    <div class="col-md-2">
        <ul id="main-nav" class="nav nav-tabs nav-stacked" style="padding-top: 50px;">
            <li class="active">
                <a href="#">
                    <i class="glyphicon glyphicon-th-large"></i>
                    首页
                </a>
            </li>
            <li>
                <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
                    <i class="glyphicon glyphicon-cog"></i>
                    系统管理
                    <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                </a>
                <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                    <li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
                </ul>
            </li>
            <li>
                <a href="./plans.html">
                    <i class="glyphicon glyphicon-credit-card"></i>
                    物料管理
                </a>
            </li>
            <li>
                <a href="./grid.html">
                    <i class="glyphicon glyphicon-globe"></i>
                    分发配置
                    <span class="label label-warning pull-right">5</span>
                </a>
            </li>
            <li>
                <a href="./charts.html">
                    <i class="glyphicon glyphicon-calendar"></i>
                    图表统计
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="glyphicon glyphicon-fire"></i>
                    关于系统
                </a>
            </li>
        </ul>
    </div>
        <div class="col-md-8">
            <!--content begin-->
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">Section 1</a></li>
                    <li><a href="#tab2" data-toggle="tab">Section 2</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane xfade  active" id="tab1">

                    </div>

                    <div class="tab-pane fade" id="tab2">

                    </div>
                </div>
            </div>
        </div>
    <div class="col-md-2"></div>
</div>

<!--content end-->
<script src="${ctx}/js/jquery-3.1.1.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
</body>
</html>