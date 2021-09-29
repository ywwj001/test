<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
    <style>
        .logo {
            width: 150px;
            height: 100px;
        }

        .layui-icon {
            position: relative;
            top: 3px;
            font-size: 18px;
            margin-right: 10px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%--   顶部部分 --%>
    <div class="layui-header">
        <div class="layui-logo">健身饮食管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a href="javascript:void(0);" target="_self" onclick="">导出订单</a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${ctxPath}/upload/headpic.jpg" class="layui-nav-img">
                    ${sessionScope.userInfo.name}
                </a>
            </li>
            <li class="layui-nav-item">
                <a class="layui layui-icon layui-icon-edit" href="javascript:void(0)"
                   onclick="showUpdatePwdPage()">修改密码</a>
            </li>
            <li class="layui-nav-item"><a href="${ctxPath}/logout">退了</a></li>
        </ul>
    </div>

    <%--  左侧导航部分 --%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <%--            公司logo--%>
            <img class="logo" src="${ctxPath}/resources/imgs/logo.png"/>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" id="nav_layui">
                <c:forEach items="${myMuneList}" var="item">
                    <li class="layui-nav-item">
                        <a href="${ctxPath}${item.url}">
                            <i class="layui-icon ${item.icon}"></i>
                                ${item.name} </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%--  主体部分  --%>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe src="${ctxPath}${myMuneList[0]['url']}" name="myframe" frameborder="0"
                style="width: 100%;height: 100%;"></iframe>
    </div>
</div>
<script>
    function showUpdatePwdPage() {
        //打开权限分配框
        layer.open({
            type: 2,
            title: '修改密码',
            content: "${ctxPath}/teacher/updatePwdPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            shade: 0.4,
            area: [winWidth, winHeight],
            success: function (layero, index) {//当前层DOM 当前层索引
                //回显内容
                var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                //写个回调出去，让自己解决下拉框和图片显示问题
                // 新iframe窗口的对象
                var iframeWin = layero.find('iframe')[0].contentWindow;
                iframeWin.onEditViewCreated({});//执行子页面的回调
            }
            , btn: ['确认修改']
            , yes: function (index, layero) {
                var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                tool_event_Form.find('#btn_form_submit').click()
            }
        });
    }
</script>
</body>
</html>