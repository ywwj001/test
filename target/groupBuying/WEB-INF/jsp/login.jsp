<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/resources/third/yekong/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/resources/third/yekong/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/resources/third/yekong/css/component.css"/>

</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3 style="margin-top: 20px;margin-bottom: 15px">健身饮食管理系统</h3>
                <form action="#" name="f" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="username"
                               id="username"
                               class="text"
                               readonly="readonly"
                               style="color: #FFFFFF !important"
                               type="text"
                               placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="password"
                               id="password"
                               class="text"
                               readonly="readonly"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2"><a class="act-but submit" id="requestLogin" href="javascript:;"
                                        style="color: #FFFFFF">登录</a></div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctxPath}/resources/third/yekong/js/TweenLite.min.js"></script>
<script type="text/javascript" src="${ctxPath}/resources/third/yekong/js/EasePack.min.js"></script>
<script type="text/javascript" src="${ctxPath}/resources/third/yekong/js/rAF.js"></script>
<script type="text/javascript" src="${ctxPath}/resources/third/yekong/js/demo-1.js"></script>
<script>
    layui.use(['layer'], function () {
        var layer = layui.layer;
        setTimeout(function removeReadonly() {
            var username = document.getElementById("username");
            var password = document.getElementById("password");
            username.removeAttribute("readonly");
            password.removeAttribute("readonly");
        }, 1000);


        var isRequestingLogin = false;
        $('#requestLogin').click(function () {
            if (isRequestingLogin) {
                return false;
            }
            let username = $('#username').val().trim();
            let password = $('#password').val().trim();
            if (!username) {
                layer.msg("请输入用户名", {icon: 5, anim: 6, time: 3000});
                return false;
            }
            if (!password) {
                layer.msg("请输入密码", {icon: 5, anim: 6, time: 3000});
                return false;
            }
            console.log("开始调用postData")
            isRequestingLogin = true;
            postData({
                url: '${ctxPath}/requestLogin',
                data: {
                    username: username,
                    password: password
                },
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg('登录成功', {icon: 1, anim: 2, time: 2000}, function () {
                            //跳转到首页
                            window.location.href = '${ctxPath}/index';
                        });
                    } else {
                        layer.msg(res.msg, {icon: 5, anim: 6, time: 3000});
                    }
                    isRequestingLogin = false;
                }

            });
        });
    });
</script>