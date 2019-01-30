<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 百度ECHarts</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link href="${ctx}/news/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="${ctx}/news/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/news/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/news/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/news/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="${ctx}/news/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/news/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx}/news/css/component.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-pull-1">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户信息</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="graph_flot.html#">选项1</a>
                            </li>
                            <li><a href="graph_flot.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div id="tab1" class="tabson">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <table class="table table-hover" id="tall22"
                                       data-pagination="true"
                                       data-show-refresh="true"
                                       data-show-toggle="true"
                                       data-showColumns="true"
                                       data-click-to-select="true"
                                       data-single-select="true">
                                    <thead>
                                    <tr>
                                        <th style="width: 15%" data-field="id">序号</th>
                                        <th style="width: 15%" data-field="name">姓名</th>
                                        <th style="width: 15%" data-field="username">账号</th>
                                        <th style="width: 15%" data-field="enabled">状态</th>
                                        <th style="width: 15%" data-field="remark">组织</th>
                                        <th class="col-xs-2" data-field="action" data-formatter="actionFormatter1"
                                            data-events="actionEvents" style="width: 16%">操作
                                        </th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/news/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/news/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx}/news/js/bootstrap-table.js"></script>
<script src="${ctx}/news/js/myjs/userDetail.js"></script>
<script src="${ctx}/news/js/classie.js"></script>
<script src="${ctx}/news/js/modalEffects.js"></script>
<script src="${ctx}/news/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${ctx}/news/js/content.min.js?v=1.0.0"></script>
<script src="${ctx}/news/js/plugins/fancybox/jquery.fancybox.js"></script>
<script src="${ctx}/news/js/vue.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.pie.js"></script>
<script src="${ctx}/news/js/plugins/flot/jquery.flot.symbol.js"></script>
<script src="${ctx}/news/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${ctx}/news/js/demo/peity-demo.min.js"></script>
<script src="${ctx}/news/js/content.min.js?v=1.0.0"></script>
<script src="${ctx}/news/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="${ctx}/news/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${ctx}/news/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${ctx}/news/js/plugins/easypiechart/jquery.easypiechart.js"></script>
<script src="${ctx}/news/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${ctx}/news/js/demo/sparkline-demo.min.js"></script>
<script>
    $(document).ready(function () {
        $(".fancybox").fancybox({openEffect: "none", closeEffect: "none"})
    });
</script>
</body>
</html>
