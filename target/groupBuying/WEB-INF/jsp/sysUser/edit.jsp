<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
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
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">登陆名</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="username"
                       disabled="disabled"
                       class="layui-input"/>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name"
                       lay-reqText="请输入姓名"
                       placeholder="请输入姓名"
                       lay-verify="required" placeholder="请输入姓名" value=""
                       class="layui-input"/>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline container-row container-imags">
                <input type="hidden"
                       lay-verify="required"
                       lay-reqText="请上传管理员真实头像"
                       name="headImg" id="headImg"/>
                <button type="button" class="layui-btn" id="headImgBtn">
                    <i class="layui-icon">&#xe67c;</i>上传
                </button>
                <img src="" id="headImgShow" style="display: none" class="headImg"/>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="sex"
                        lay-reqText="请选择性别"
                        placeholder="请选择性别"
                        id="sex" lay-filter="sex"
                        lay-verify="required|number">
                    <option value="1">帅哥</option>
                    <option value="2">美女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-inline">
                <input type="text" name="age"
                       lay-verify="required|number"
                       lay-reqText="请输入年龄"
                       placeholder="请输入年龄" value="20"
                       class="layui-input"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系人</label>
            <div class="layui-input-inline">
                <input type="text" name="linkman"
                       lay-verify="required"
                       lay-reqText="请输入联系人"
                       placeholder="请输入联系人" value=""
                       class="layui-input"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="phone"
                       lay-reqText="请输入手机号"
                       placeholder="请输入手机号"
                       lay-verify="required" placeholder="请输入手机号" value=""
                       class="layui-input"/>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">
                <button type="button" lay-filter="btn_form_submit" lay-submit
                        class="my-btn-submit layui-btn layui-btn-normal">
                    保存修改内容
                </button>
            </div>
        </div>

        <div style="margin-top: 50px"></div>
    </form>
</div>
</body>
<script>
    var modleName = '${ctxPath}/sysUser';

    layui.use(['element', 'laydate', 'upload', 'form'], function () {
        var element = layui.element;
        var laydate = layui.laydate;
        var upload = layui.upload;
        var form = layui.form;
        form.on("submit(btn_form_submit)", function (data) {
            //简单校验并提交
            postData({
                url: '${ctxPath}/sysUser/update',
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
        });
        //执行实例
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


        //获取基本信息
        postData({
            url: '${ctxPath}/sysUser/getList',
            data: {
                id: '${id}'
            },
            success: function (res) {
                if (res.code === 0) {
                    //获取账单信息成功
                    var data = res.data[0];
                    if (!data) {
                        layer.msg('未查询到管理员信息', {icon: 5, anim: 6, time: 3000});
                        return;
                    }
                    $('#form_submit').initForm(data);
                    //店铺图片
                    $('#headImgShow').attr('src', '${ctxPath}/' + data.headImg);
                    $('#headImgShow').show(500);
                    form.render();
                } else {
                    layer.msg(res.msg, {icon: 5, anim: 6, time: 3000});
                }
            }

        });
    });
</script>
</html>

