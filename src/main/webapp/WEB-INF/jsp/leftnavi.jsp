<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/global.jsp" %>
    <style>
        .logo {
            width: 126px;
            margin-left: 27px;
            height: 120px;
            border-radius: 50%;
            margin-bottom: 20px;
            margin-top: 20px;
            cursor: pointer;
        }

        .my-icon {
            position: relative;
            top: 3px;
            font-size: 18px;
            margin-right: 10px;
        }

        @keyframes scaleDraw {
            /*定义关键帧、scaleDrew是需要绑定到选择器的关键帧名称*/
            0% {
                transform: scale(1);
                /*开始为原始大小*/
            }
            25% {
                transform: scale(1.1);
                /*放大1.1倍*/
            }
            50% {
                transform: scale(1);
            }
            75% {
                transform: scale(1.1);
            }
        }

        .logo {
            -webkit-animation-name: scaleDraw;
            /*关键帧名称*/
            -webkit-animation-timing-function: ease-in-out;
            /*动画的速度曲线*/
            -webkit-animation-iteration-count: infinite;
            /*动画播放的次数*/
            -webkit-animation-duration: 8s;
            /*动画所花费的时间*/
        }
    </style>
</head>
<body>
<%--  左侧导航部分 --%>
<div class="layui-side layui-bg-black" style="margin-top: 45px">
    <div class="layui-side-scroll">
        <%--公司logo--%>
        <img class="logo" src="${ctxPath}/resources/imgs/logo_mofang.jpg"/>
        <ul class="layui-nav layui-nav-tree">
            <c:forEach items="${myMuneList}" var="item">
                <li class="layui-nav-item  ${currentPath==item.url?'layui-this':''}">
                    <a href="${ctxPath}${item.url}">
                        <i class="layui-icon my-icon ${item.icon}"></i>
                            ${item.name} </a>
                </li>
            </c:forEach>
        </ul>
        <div style="height: 120px"></div>
    </div>
</div>
</body>
</html>