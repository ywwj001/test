<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="global.jsp" %>
<%--<base href="http://www.qianfannet.com/merchantGoods/">--%>
<title>健身饮食管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--<meta name="renderer" content="webkit">--%>
<%--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">--%>
<%--<meta name="viewport"--%>
<%--      content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">--%>
<link rel="stylesheet" type="text/css" href="${ctxPath}/resources/third/layui/layui-v2.5.7/css/layui.css?v=4">
<link rel="stylesheet" type="text/css" href="${ctxPath}/resources/css/common.css?v=11">
<script type="text/javascript" src="${ctxPath}/resources/third/jquery/jquery.3.4.1.min.js"></script>
<script type="text/javascript" src="${ctxPath}/resources/third/layui/layui-v2.5.7/layui.js?v=5"></script>
<script type="text/javascript" src="${ctxPath}/resources/js/utils.js?v=1"></script>
<script type="text/javascript" src="${ctxPath}/resources/js/formData.js?v=3"></script>
<script>
    function postData(requestInfo) {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        //获取保存在本地的token令牌
        $.ajax({
            type: 'post',
            url: requestInfo['url'],
            data: requestInfo['data'],
            dataType: 'json',
            success: function (res) {
                requestInfo['success'](res);
                layer.close(index);
            }
        })
    }
</script>
<style>
    div {
        font-family: “Arial”, ”Microsoft YaHei”, ”黑体”, ”宋体”, sans-serif;
    }
</style>