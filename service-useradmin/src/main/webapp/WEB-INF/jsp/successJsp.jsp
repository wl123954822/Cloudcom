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
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/css.css" rel="stylesheet">
    <link href="${ctx}/css/style.min.css" rel="stylesheet">
</head>
<body>
<h1 style="text-align: center">添加${time}财务数据成功，谢谢！</h1>
</body>
<script src="${ctx}/js/jquery-3.1.1.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
</html>