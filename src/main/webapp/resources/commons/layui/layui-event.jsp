<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>
    function renderTable(param) {
        //第一个实例
        tableIns = table.render({
            elem: param.elem
            , skin: param['skin'] ? param['skin'] : 'line' //行边框风格
            , even: !param['hideEven']//开启隔行背景
            // , size: 'lg' //小尺寸的表格
            , id: param.id
            , toolbar: param.toolbar
            , defaultToolbar: []
            , url: param.url
            , page: !param['hidePage']  //开启分页
            , cellMinWidth: 90
            , cols: param.cols
            , limits: [5, 10, 15, 20, 30, 50, 80, 100, 500]
            , limit: param['limit'] ? param['limit'] : 15,
            done: param['done'] ? param['done'] : ''
        });
    }


    /**
     * 绑定图片上传的组件
     * @param elementId
     * @param initImage 编辑的时候的图片回显
     */
    function bindUpload(options) {


    }

    function beforeSubmit() {

    }

    function bindSubmit(modleName, method, layFilterName, formId, callback) {
        //监听表单提交
        form.on("submit(" + layFilterName + ")", function () {
            if (beforeSubmit) {
                beforeSubmit();
            }
            //进行网络请求
            $.ajax({
                url: modleName + '/' + method,
                type: 'post',
                data: $('#' + formId).serialize(),
                dataType: 'json',
                success: function (res) {
                    if (res.code == 0) {
                        //刷新table表格
                        parent.onSearch();
                        parent.layer.msg(res.msg, {icon: 1, shade: 0.4, time: 1000});
                        /**
                         *分成两种情况：
                         1、弹出层不是新的页面的时候,直接获得该弹窗的索引，然后执行close方法
                         layer.close();
                         2、弹出窗是新的页面的时候
                         var index=parent.layer.getFrameIndex(window.name);
                         parent.layer.close(index);
                         */
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        parent.layer.msg(res.msg, {icon: 5, shade: 0.4, time: 1000});
                    }
                    if (callback) {
                        callback(res);
                    }
                }
            });
            return false;
        });
    }


    function loadOptionList(option) {
        var nameKey = option['name'] ? option['name'] : 'name';
        var valueKey = option['id'] ? option['id'] : 'id';
        $.getJSON(option.url, {
            state: 1,
            page: 1,
            limit: 100
        }, function (res) {
            var item;
            $('#' + option['elementId']).empty();
            if (option['pleaseSelect']) {
                $('#' + option['elementId']).append('<option' +
                    ' value="">请选择<option>');
            }
            if (option['initOptions']) {
                $.each(option['initOptions'], function (index, bean) {
                    if (bean['value'] === option['selectedValue']) {
                        item = '<option selected' +
                            ' value="' + bean['value'] + '">'
                            + bean['name']
                            + '<option>';
                    } else {
                        item = '<option' +
                            ' value="' + bean['value'] + '">'
                            + bean['name']
                            + '<option>';
                    }
                    $('#' + option['elementId']).append(item);
                });
            }
            $.each(res.data, function (index, bean) {
                if (bean[valueKey] === option['selectedValue']) {
                    item = '<option  selected' +
                        ' value="' + bean[valueKey] + '">'
                        + bean[nameKey]
                        + '<option>';
                } else {
                    item = '<option' +
                        ' value="' + bean[valueKey] + '">'
                        + bean[nameKey]
                        + '<option>';
                }

                $('#' + option['elementId']).append(item);
            })
            if (option['dataResult']) {
                option['dataResult'](res.data);
            }
            form.render();
        })
    }

    function loadCheckboxList(option) {
        var nameKey = option['name'] ? option['name'] : 'name';
        var valueKey = option['id'] ? option['id'] : 'id';

        if (option['data']) {
            makeCheckbox(option['data']);
            return;
        }
        $.getJSON(option.url, {
            state: 1,
            page: 1,
            limit: 1000
        }, function (res) {
            makeCheckbox(res.data);
        })

        function makeCheckbox(dataList) {
            var item;
            $('#' + option['elementId']).empty();
            $.each(dataList, function (index, bean) {
                if (option['selectedValue'] && isCheckedIt(option, bean['id'])) {
                    console.log(option['disabled'])
                    if (option['disabled']) {
                        item = '<input type="checkbox" disabled  checked name="' + option['submitName'] + '" value="' + bean[valueKey] + '" title="' + bean[nameKey] + '">'
                    } else {
                        item = '<input type="checkbox"  checked name="' + option['submitName'] + '" value="' + bean[valueKey] + '" title="' + bean[nameKey] + '">'
                    }
                } else {
                    if (option['disabled']) {
                        item = '<input type="checkbox" disabled name="' + option['submitName'] + '" value="' + bean[valueKey] + '" title="' + bean[nameKey] + '">'
                    } else {
                        item = '<input type="checkbox" name="' + option['submitName'] + '" value="' + bean[valueKey] + '" title="' + bean[nameKey] + '">'
                    }
                }
                $('#' + option['elementId']).append(item);
            });
            form.render();
        }
    }

    function isCheckedIt(option, value) {
        for (var i = 0; i < option['selectedValue'].length; i++) {
            var equalValue = option['selectedValue'][i];
            if (option['equalName']) {
                equalValue = option['selectedValue'][i][option['equalName']];
            }
            if (value === equalValue) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用父类的工具栏
     * @param modleName
     */
    function useParentToolEvent(modleName) {
        //监听工具条
        table.on('tool(lineToolFilter)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            console.log(obj)
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            switch (layEvent) {
                case 'delete':
                    layer.confirm('确定要删除吗？', {
                        btn: ['删除', '取消'] //可以无限个按钮
                        , yes: function (index, layero) {
                            //删除逻辑
                            $.ajax({
                                url: modleName + '/delete',
                                type: 'post',
                                data: {
                                    id: data.id
                                },
                                dataType: 'json',
                                success: function (res) {
                                    if (res.code == 0) {
                                        onSearch();
                                        layer.msg('删除成功', {icon: 1, shade: 0.4, time: 1000});
                                    } else {
                                        layer.msg(res.msg, {icon: 5, shade: 0.4, time: 3000});
                                    }
                                }
                            })
                        }
                    })
                    break;
                case 'delete3':
                    layer.confirm('确定要永久删除吗？', {
                        btn: ['删除', '取消'] //可以无限个按钮
                        , yes: function (index, layero) {
                            //删除逻辑
                            $.ajax({
                                url: modleName + '/updateState',
                                type: 'post',
                                data: {
                                    id: data.id,
                                    state: 3
                                },
                                dataType: 'json',
                                success: function (res) {
                                    if (res.code == 0) {
                                        onSearch();
                                        layer.msg('删除成功', {icon: 1, shade: 0.4, time: 1000});
                                    } else {
                                        layer.msg(res.msg, {icon: 5, shade: 0.4, time: 3000});
                                    }
                                }
                            })
                        }
                    })
                    break;
                case 'disable':
                    layer.confirm('确定要禁用吗？', {
                        btn: ['禁用', '取消'] //可以无限个按钮
                        , yes: function (index, layero) {
                            //删除逻辑
                            $.ajax({
                                url: modleName + '/delete',
                                type: 'post',
                                data: {
                                    id: data.id
                                },
                                dataType: 'json',
                                success: function (res) {
                                    if (res.code == 0) {
                                        onSearch();
                                        layer.msg('禁用成功', {icon: 1, shade: 0.4, time: 1000});
                                    } else {
                                        layer.msg(res.msg, {icon: 5, shade: 0.4, time: 3000});
                                    }
                                }
                            })
                        }
                    })
                    break;
                case 'enable':
                    layer.confirm('确定要启用吗？', {
                        btn: ['启用', '取消'] //可以无限个按钮
                        , yes: function (index, layero) {
                            //删除逻辑
                            $.ajax({
                                url: modleName + '/updateState',
                                type: 'post',
                                data: {
                                    id: data.id,
                                    state: 1
                                },
                                dataType: 'json',
                                success: function (res) {
                                    if (res.code == 0) {
                                        onSearch();
                                        layer.msg('启用成功', {icon: 1, shade: 0.4, time: 1000});
                                    } else {
                                        layer.msg(res.msg, {icon: 5, shade: 0.4, time: 3000});
                                    }
                                }
                            })
                        }
                    })
                    break;
                case 'edit':
                    //编辑
                    layer.open({
                        type: 2,
                        title: '编辑信息',
                        content: modleName + "/editPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                        shade: 0.4,
                        area: [winWidth, winHeight],
                        success: function (layero, index) {//当前层DOM 当前层索引
                            //回显内容
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.initForm(data);
                            //写个回调出去，让自己解决下拉框和图片显示问题
                            // 新iframe窗口的对象
                            var iframeWin = layero.find('iframe')[0].contentWindow;
                            iframeWin.onEditViewCreated(data);//执行子页面的回调
                        }
                        , btn: ['修改', '取消']
                        , yes: function (index, layero) {
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.find('#btn_form_submit').click()
                        }
                    })
                    break;

                case 'detail':
                    //详情
                    layer.open({
                        type: 2,
                        title: '详情',
                        content: modleName + "/detailPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                        shade: 0.4,
                        area: [winWidth, winHeight],
                        success: function (layero, index) {//当前层DOM 当前层索引
                            //回显内容
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.initForm(data);
                            //写个回调出去，让自己解决下拉框和图片显示问题
                            // 新iframe窗口的对象
                            var iframeWin = layero.find('iframe')[0].contentWindow;
                            iframeWin.onEditViewCreated(data);//执行子页面的回调
                        }
                        , btn: ['关闭']
                        , yes: function (index, layero) {
                            layer.close(index);
                        }
                    })
                    break;
                default:
                    if (lineToolFilterEvent) {
                        lineToolFilterEvent(data, layEvent);
                    }
                    break;

            }
        });
    }


    var currentItemData;

    function onEditViewCreated(data) {
        currentItemData = data;
        windowInitNum++;
        console.log(windowInitNum)
        if (windowInitNum == 2) {
            initData();
        }
    }

    function onLayuiInit() {
        windowInitNum++;
        console.log(windowInitNum)
        if (windowInitNum == 2) {
            initData();
        }
    }

    function getDateStrByTimeStamp(dateTimestamp, type) {
        type = type ? type : 1;
        var myDate1 = new Date(dateTimestamp);
        var year1 = myDate1.getFullYear(); //获取当前年
        var mon1 = myDate1.getMonth() + 1; //获取当前月
        var date1 = myDate1.getDate(); //获取当前日
        var hours = myDate1.getHours(); //获取当前日
        var minutes = myDate1.getMinutes(); //获取当前日
        var seconds = myDate1.getSeconds(); //获取当前日
        var dateStr1;
        switch (type) {
            case 1:
                dateStr1 = year1 + '-' + mon1 + '-' + date1;
                break;
            case 2:
                dateStr1 = year1 + '-' + mon1 + '-' + date1 + ' ' + hours + ':' + minutes + ':' + seconds;
                break;
        }
        return dateStr1;
    }
</script>