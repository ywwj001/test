<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
    <!-- 富文本编辑器-->
    <!-- 配置文件 -->
    <script type="text/javascript" src="${ctxPath}/resources/third/ueditor/ueditor.config.js?v=3"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctxPath}/resources/third/ueditor/ueditor.all.js?v=2"></script>
    <script type="text/javascript" charset="utf-8"
            src="${ctxPath}/resources/third/ueditor/lang/zh-cn/zh-cn.js"></script>
    <style>
        .headImg {
            width: 38px;
            height: 38px;
            min-width: 38px;
            min-height: 38px;
            border-radius: 4px;
            margin-left: 15px;
            box-shadow: 3px 3px 8px #c1c1c1;
        }
    </style>
</head>
<body>
<div style="width: 700px; padding-top: 10px;">
    <form id="form_submit" class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">文章标题</label>
            <div class="layui-input-inline">
                <input type="text" name="title"
                       lay-reqText="请输入文章标题"
                       placeholder="请输入文章标题"
                       lay-verify="required" placeholder="请输入文章标题" value=""
                       class="layui-input"/>
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">封面图</label>
            <div class="layui-input-inline container-row container-imags">
                <input type="hidden"
                       lay-verify="required"
                       lay-reqText="请上传封面图"
                       name="coverImg" id="headImg"/>
                <button type="button" class="layui-btn" id="headImgBtn">
                    <i class="layui-icon">&#xe67c;</i>上传
                </button>
                <img src="" id="headImgShow" style="display: none" class="headImg"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">文章详情</label>
            <div class="layui-input-inline">
                <!-- 加载编辑器的容器 -->
                <script type="text/plain"
                        style="width:100%;  min-height: 300px;"
                        id="detail"></script>
                <%--                <input type="text" name="detail"--%>
                <%--                       lay-reqText="请输入文章详情"--%>
                <%--                       placeholder="请输入文章详情"--%>
                <%--                       lay-verify="required" placeholder="请输入文章详情" value=""--%>
                <%--                       class="layui-input"/>--%>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">
                <button type="button" lay-filter="btn_form_submit" lay-submit
                        class="my-btn-submit layui-btn layui-btn-normal">
                    确认添加
                </button>
            </div>
        </div>
        <div style="margin-top: 50px"></div>
    </form>
</div>
</body>
<script>
    var modleName = '${ctxPath}/article';

    layui.use(['element', 'laydate', 'upload', 'form'], function () {
        var element = layui.element;
        var laydate = layui.laydate;
        var upload = layui.upload;
        var form = layui.form;
        form.on("submit(btn_form_submit)", function (data) {

            //描述
            var html = uEditor.getContent();
            if (!html) {
                layer.msg('请填写描述', {icon: 5, anim: 6, time: 3000});
                return;
            }
            data.field['detail'] = html;

            //简单校验并提交
            postData({
                url: modleName + '/add',
                data: data.field,
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg(res.msg, {icon: 1, anim: 2, time: 2000}, function () {
                            //关闭弹窗并刷新页面
                            window.parent.closeEditPage();
                        });
                    } else {
                        layer.msg(res.msg, {icon: 5, anim: 6, time: 3000});
                    }
                }
            });
            return false;
        });//执行实例
        var uploadInst = upload.render({
            elem: '#headImgBtn' //绑定元素
            , url: '${ctxPath}/getActionEntry?action=uploadimage&encode=utf-8' //上传接口
            , accept: 'images' //允许上传的文件类型
            , field: 'files'
            , size: 1024 * 5 //最大允许上传的文件大小 5兆
            , done: function (res) {
                //上传完毕回调
                $('#headImg').val("uploadFiles" + res.url);
                $('#headImgShow').attr('src', '${ctxPath}/' + "uploadFiles" + res.url);
                $('#headImgShow').show(500);
                layer.msg('上传成功', {icon: 1, anim: 2, time: 2000}, function () {

                });
            }
            , error: function () {
                //请求异常回调
                layer.msg('上传失败', {icon: 5, anim: 6, time: 3000});
            }
        });
        initUeditor();
    });

    var uEditor;

    function initUeditor() {
        uEditor = UE.getEditor('detail', {
            autoHeightEnabled: false,
            autoFloatEnabled: false,
            initialFrameHeight: 300,
            initialFrameWidth: '100%'
        });
        //对编辑器的操作最好在编辑器ready之后再做
        uEditor.ready(function () {
            //设置编辑器的内容
            uEditor.setContent('');
        });
    }
</script>
</html>
