<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>
    var initFormDataObj;
    var table, element;
    var layer;
    var form;
    var upload;
    var tableIns;

    var addText;
    var addTitle;

    var laydate;

    var tree;

    var windowInitNum = 0;//编辑窗口计数器
    var winHeight = window.parent.innerHeight * 0.8 + 'px';
    var winWidth = window.parent.innerWidth * 0.6 + 'px';
    var initFormData = null;

    function onLayuiInit() {

    }

    function onSearch() {
        tableIns.reload({
            where: {
                searchContent: $('#name_search').val(),
                state: $('#state_search').val()
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //表格重载
    }

    layui.use(['element', 'table', 'layer', 'form', 'upload', 'laydate', 'tree'], function () {
        element = layui.element;
        table = layui.table;
        layer = layui.layer;
        form = layui.form;
        upload = layui.upload;
        laydate = layui.laydate;
        tree = layui.tree;
        onLayuiInit();

        //监听事件
        table.on('toolbar(lineToolFilter)', function (obj) {
            switch (obj.event) {
                case 'search':
                    onSearch();
                    break;
                case 'add':
                    //新增
                    layer.open({
                        type: 2,
                        title: addTitle ? addTitle : '添加信息',
                        content: modleName + "/addPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                        shade: 0.4,
                        area: [winWidth, winHeight],
                        success: function (layero, index) {//当前层DOM 当前层索引
                            // 新iframe窗口的对象
                            // iframeWin = layero.find('iframe')[0].contentWindow;
                            //回显
                            // console.log('-----' + initFormData)
                            if (currentItemData) {
                                var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                                tool_event_Form.initForm(currentItemData);
                            }
                        }
                        , btn: [addText ? addText : '添加']
                        , yes: function (index, layero) {
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.find('#btn_form_submit').click()
                        }
                    });
                    break;
                default:
                    if (lineToolFilterEventTop) {
                        lineToolFilterEventTop(obj.event);
                    }
                    break;
            }
        });

        //switch开关
        form.on('switch(switchState)', function (data) {
            var id = data.elem.name;
            var isChecked = data.elem.checked;
            $.ajax({
                type: "post",
                url: modleName + "/updateState",
                data: {
                    id: id,
                    state: isChecked ? 2 : 1
                },
                dataType: 'json',
                success: function (res) {
                    var str = isChecked ? '禁用' : '启用';
                    if (res.code == 0) {
                        layer.msg(str + "成功！", {icon: 1, time: 2000});
                    } else {
                        layer.msg(str + "失败！请重试！", {icon: 5, time: 2000});
                    }
                }
            });
        });


        //switch开关
        form.on('switch(switchRecommend)', function (data) {
            var id = data.elem.name;
            var isChecked = data.elem.checked;
            $.ajax({
                type: "post",
                url: modleName + "/updateRecommend",
                data: {
                    id: id,
                    recommend: isChecked ? 2 : 1
                },
                dataType: 'json',
                success: function (res) {
                    var str = isChecked ? '设置不推荐' : '设置推荐';
                    if (res.code == 0) {
                        layer.msg(str + "成功！", {icon: 1, time: 2000});
                    } else {
                        layer.msg(str + "失败！请重试！", {icon: 5, time: 2000});
                    }
                }
            });
        });

        form.verify({
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^[\\S]{6,15}$").test(value)) {
                    return '用户名长度须6到15位，且不能出现空格';
                }

                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }

                // if (/^\d+\d+\d$/.test(value)) {
                //     return '用户名不能全为数字';
                // }
            }

            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });
    });

</script>