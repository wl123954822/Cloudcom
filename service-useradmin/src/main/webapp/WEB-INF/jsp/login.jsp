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
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/css.css" rel="stylesheet">
    <link href="/css/style.min.css" rel="stylesheet">
</head>
<body>
<div class="row" style="margin-right: 5px">
    <div class="col-md-8">
        <div class="news-image">
        <img src="/images/login-bg-2.jpg"style="width: 100%;height: 650px;padding: 10px 0 0 10px;"/>
        </div>
    </div>
    <div class="col-md-4">
        <div style="padding-top: 150px;margin-left: 10px">
            <div>
                <h3 style="text-align: center">光电数据中心展厅</h3>
            </div>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="用户名" value="admin">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="密码" value="admin">
            </div>
            <button type="submit" class="btn btn-success btn-block btn-lg">登录</button>
        </form>
        </div>
    </div>
</div>
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>


</body>
</html>
