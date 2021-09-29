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
        <input type="hidden" name="id"><div class="layui-form-item">
            <label class="layui-form-label">充值金额</label>
            <div class="layui-input-inline">
                <input type="text" name="money"
                       lay-reqText="请输入充值金额"
                       placeholder="请输入充值金额"
                       lay-verify="required" placeholder="请输入充值金额" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">充值人</label>
            <div class="layui-input-inline">
                <input type="text" name="uid"
                       lay-reqText="请输入充值人"
                       placeholder="请输入充值人"
                       lay-verify="required" placeholder="请输入充值人" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
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
    var modleName = '${ctxPath}/moneyChargeRecord';

    layui.use(['element', 'laydate', 'upload', 'form'], function () {
        var element = layui.element;
        var laydate = layui.laydate;
        var upload = layui.upload;
        var form = layui.form;
        form.on("submit(btn_form_submit)", function (data) {
            //简单校验并提交
            postData({
                url: modleName+'/update',
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
        });//获取基本信息
        postData({
            url: modleName+'/getList',
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
 form.render();
                } else {
                    layer.msg(res.msg, {icon: 5, anim: 6, time: 3000});
                }
            }

        });    });
</script>
</html>
