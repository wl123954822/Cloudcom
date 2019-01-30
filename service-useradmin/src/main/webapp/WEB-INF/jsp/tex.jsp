<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据中心财务信息录入系统</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/css.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrapValidator.min.css">
</head>
<body >
<div class="top">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6" style="color: #003893;">
          <img src="${ctx}/images/logo.png" style="float: left">  <h1 style="text-align: center">数据中心财务信息录入系统</h1>
        </div>
        <div class="col-md-3" id="Date"></div>
    </div>
</div>

<div class="main">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6" style="padding-top: 20px; ">
    <form action="${ctx}/toaddContent" method="POST">
        <div class="form-group">
            <label for="hbsjsr">${time}合并数据收入</label>
            <input type="text" name="hbsjsr" class="form-control" id="hbsjsr" placeholder="合并数据收入">
        </div>
        <div class="form-group">
            <label for="hbsjyszzzl">${time}合并数据应收账款周转率</label>
            <input type="text" class="form-control" name="hbsjyszzzl" id="hbsjyszzzl" placeholder="合并数据应收账款周转率">
        </div>
        <div class="form-group">
            <label for="hbsjchzzl">${time}合并数据存货周转率</label>
            <input type="text" class="form-control" name="hbsjchzzl" id="hbsjchzzl" placeholder="合并数据存货周转率">
        </div>
        <div class="form-group">
            <label for="zzfzl">${time}合并数据资产负债率</label>
            <input type="text" class="form-control" name="zzfzl" id="zzfzl" placeholder="合并数据资产负债率">
        </div>
        <div class="form-group">
            <label for="cz">${time}总产值（按产业）</label>
            <input type="text" class="form-control" name="cz" id="cz" placeholder="总产值（按产业）">
        </div>
        <div class="form-group">
            <label for="yysr">${time}营业收入（按产业）</label>
            <input type="text" class="form-control" name="yysr" id="yysr" placeholder="营业收入（按产业）">
        </div>
        <div class="form-group">
            <label for="hbsjjk">${time}合并数据进款（按产业）</label>
            <input type="text" class="form-control" name="hbsjjk" id="hbsjjk" placeholder="合并数据进款（按产业）">
        </div>
        <div class="form-group">
            <label for="gdsbwc">${time}光电设备2018年完成（按产业）</label>
            <input type="text" class="form-control" id="gdsbwc"  name="gdsbwc" placeholder="光电设备2018年完成（按产业）">
        </div>
        <div class="form-group">
            <label for="9">${time}电子器件2018年完成（按产业）</label>
            <input type="text" class="form-control" id="9" placeholder="电子器件2018年完成（按产业）">
        </div>
        <div class="form-group">
            <label for="dzzzff">${time}电子制造服务2018年完成（按产业）</label>
            <input type="text" class="form-control" id="dzzzff" name="dzzzff" placeholder="电子制造服务2018年完成（按产业）">
        </div>
        <div class="form-group">
            <label for="jsfw">${time}技术服务2018年完成（按产业）</label>
            <input type="text" class="form-control" id="jsfw" name="jsfw" placeholder="技术服务2018年完成（按产业）">
        </div>
        <div class="form-group">
            <label for="qt">${time}其他2018年完成（按产业）</label>
            <input type="text" class="form-control" id="qt"  name="qt" placeholder="其他2018年完成（按产业）">
        </div>
        <div class="form-group">
            <div style="text-align: center">
            <button type="submit" class="btn btn-warning">提交</button>
            </div>
        </div>
    </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        setInterval(function () {
            var date = new Date();
            var year = date.getFullYear(); //获取当前年份
            var mon = date.getMonth() + 1; //获取当前月份
            var da = date.getDate(); //获取当前日
            var day = date.getDay(); //获取当前星期几
            var h = date.getHours(); //获取小时
            var m = date.getMinutes(); //获取分钟
            var s = date.getSeconds(); //获取秒
            var d = document.getElementById('Date');
            d.innerHTML = '当前时间:' + year + '年' + mon + '月' + da + '日' + '星期' + day + ' ' + h + ':' + m + ':' + s;
        }, 1000)
    }
</script>
<script src="${ctx}/js/jquery-3.1.1.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/bootstrapValidator.min.js"></script>
<script>
    $(function () {
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                hbsjsr: {
                    validators: {
                        notEmpty: {
                            message: '合并数据不能为空'
                        },
                        regexp13: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                hbsjyszzzl: {
                    validators: {
                        notEmpty: {
                            message: '合并数据应收账款周转率不能为空'
                        },
                        regexp12: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                hbsjchzzl: {
                    validators: {
                        notEmpty: {
                            message: '合并数据存货周转率不能为空'
                        },
                        regexp: {
                            regexp00: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                zzfzl: {
                    validators: {
                        notEmpty: {
                            message: '资产负债率不能为空'
                        },
                        regexp: {
                            regexp9: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                cz: {
                    validators: {
                        notEmpty: {
                            message: '总产值不能为空'
                        },
                        regexp: {
                            regexp8: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                yysr: {
                    validators: {
                        notEmpty: {
                            message: '营业收入不能为空'
                        },
                        regexp7: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                hbsjjk: {
                    validators: {
                        notEmpty: {
                            message: '合并数据进款不能为空'
                        },
                        regexp: {
                            regexp6: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                gdsbwc: {
                    validators: {
                        notEmpty: {
                            message: '光电设备完成不能为空'
                        },
                        regexp4: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                dzzzff: {
                    validators: {
                        notEmpty: {
                            message: '电子器件完成不能为空'
                        },
                        regexp3: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                jsfw: {
                    validators: {
                        notEmpty: {
                            message: '技术服务完成不能为空'
                        },
                        regexp2: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
                qt: {
                    validators: {
                        notEmpty: {
                            message: '其他完成不能为空'
                        },
                        regexp1: {
                            regexp: /^[0-9]*$ /,
                            message: '只能包含数字'
                        }
                    }
                },
            }
        });
    });
</script>
</body>
</html>
