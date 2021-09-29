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
            <label class="layui-form-label">身高</label>
            <div class="layui-input-inline">
                <input type="text" name="height"
                       lay-reqText="请输入身高"
                       placeholder="请输入身高"
                       lay-verify="required" placeholder="请输入身高" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">体重</label>
            <div class="layui-input-inline">
                <input type="text" name="weight"
                       lay-reqText="请输入体重"
                       placeholder="请输入体重"
                       lay-verify="required" placeholder="请输入体重" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">BMI值</label>
            <div class="layui-input-inline">
                <input type="text" name="bmi"
                       lay-reqText="请输入BMI值"
                       placeholder="请输入BMI值"
                       lay-verify="required" placeholder="请输入BMI值" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">记录日期</label>
            <div class="layui-input-inline">
                <input type="text" name="recordDay"
                       lay-reqText="请输入记录日期"
                       placeholder="请输入记录日期"
                       lay-verify="required" placeholder="请输入记录日期" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <input type="text" name="remark"
                       lay-reqText="请输入备注"
                       placeholder="请输入备注"
                       lay-verify="required" placeholder="请输入备注" value=""
                       class="layui-input"/>
            </div>
        </div><div class="layui-form-item">
            <label class="layui-form-label">用户</label>
            <div class="layui-input-inline">
                <input type="text" name="uid"
                       lay-reqText="请输入用户"
                       placeholder="请输入用户"
                       lay-verify="required" placeholder="请输入用户" value=""
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
    var modleName = '${ctxPath}/heightWeightRecord';

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
