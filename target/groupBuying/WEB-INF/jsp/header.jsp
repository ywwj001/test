<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/global.jsp" %>
    <style>
        .layui-logo,
        .layui-nav-item {
            line-height: 45px !important;
        }

        .myHeadImg {
            box-shadow: 0 0 3px #9f2423;
        }

        .layui-layout-admin .layui-logo {
            font-weight: bold;
            font-family: fangsong;
            font-size: 18px;
        }
    </style>
</head>
<body>
<%--   顶部部分 --%>
<div class="layui-header" style="height: 45px">
    <div class="layui-logo">健身饮食管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">

    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <c:choose>
                    <c:when test="${myHeadImg}!=''">
                        <img src="${ctxPath}/resources/imgs/default_head.png" class="layui-nav-img myHeadImg">
                    </c:when>
                    <c:otherwise>
                        <img src="${ctxPath}/${myHeadImg}" class="layui-nav-img myHeadImg">
                    </c:otherwise>
                </c:choose>
                ${myName}
            </a>
        </li>
        <li class="layui-nav-item"><a href="javascript:void(0)" onclick="showUpdateMyInfoPage()">修改个人资料</a></li>
        <li class="layui-nav-item"><a href="${ctxPath}/logout">退了</a></li>
    </ul>
</div>
</div>
</body>
</html>
<script>
    layui.use(['table', 'util'], function () {
        var table = layui.table;
        var util = layui.util;
    });
    var openIndex;

    function showUpdateMyInfoPage() {
        openIndex = layer.open({
            type: 2,
            title: '修改个人资料',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['60%', '80%'],
            content: '${ctxPath}/sysUser/editPage?id=${mySysyUserId}'
        });
    }

    function closeEditPage() {
        layer.close(openIndex);
        window.location.reload();
    }
</script>