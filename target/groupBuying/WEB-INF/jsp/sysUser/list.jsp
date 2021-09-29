<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
    <style>
        .avatarUrl {
            width: 25px;
            height: 25px;
            min-width: 25px;
            min-height: 25px;
            border-radius: 50%;
            box-shadow: 0 0 3px #dd35b6;
        }

        .container-header {
            justify-content: flex-start;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../header.jsp"/>
    <jsp:include page="../leftnavi.jsp"/>

    <%--  主体部分  --%>
    <div class="layui-body" style="margin-top: 45px;margin-left: 10px">
        <table id="id_table" lay-filter="lineToolFilter"></table>
    </div>
</body>
<script type="text/html" id="toolBar">
    <div class="container-row container-header-items">
        <div class="item">
            <span>搜索内容:</span>
            <input type="text" class="layui-input"
                   id="name_search" name="name_search" placeholder="请输入搜索内容" autocomplete="off">
        </div>
        <div class="container-row flex-center mt-10">
            <a class="layui-btn" lay-event="add">
                <i class="layui-icon layui-icon-add-1"></i>添加
            </a>

            <a class="layui-btn layui-btn-normal mr-5" lay-event="search" id="toolBar_btn_search">
                <i class="layui-icon layui-icon-search"></i>搜索
            </a>
        </div>
    </div>
</script>
<script>
    var modleName = '${ctxPath}/sysUser';

    var tableIns;
    layui.use(['table', 'util'], function () {
        var table = layui.table;
        var util = layui.util;
        //第一个实例
        tableIns = table.render({
            elem: '#id_table'
            , skin: 'line' //行边框风格
            , even: true//开启隔行背景
            // , size: 'lg' //小尺寸的表格
            , height: 'full-65'
            , id: 'id_table'
            , toolbar: '#toolBar'
            , defaultToolbar: ["filter", "exports", "print"]
            , url: modleName + '/getList?/getList?state=1' //数据接口
            , page: true //开启分页
            , cellMinWidth: 30
            , cols: [[ //表头
                {
                    width: 50,
                     title: '序号', type: 'numbers', fixed: 'left'
                }
                , {
                    field: 'headImg', title: '头像',
                    width: 80,

                    align: 'center', templet: function (d) {
                        if (d.headImg) {
                            return '<img class="avatarUrl" src="${ctxPath}/' + d.headImg + '"/>'
                        }
                        return '<img class="avatarUrl" src="${ctxPath}/resources/imgs/default_head.png"/>'
                    }
                }
                , {
                    field: 'username',

                    minWidth: 200,
                    title: '登陆名', align: 'center'
                }
                , {
                    field: 'name',
                    minWidth: 150,
                    title: '姓名', align: 'center'
                }
                , {
                    field: 'sex',
                    width: 80,
                    title: '性别', align: 'center',
                    templet: function (d) {
                        if (d.sex === 1) {
                            return '帅哥';
                        } else if (d.sex === 2) {
                            return '美女';
                        } else {
                            return '-';
                        }
                    }
                }
                , {
                    field: 'age',
                    width: 80,
                    title: '年龄', align: 'center'
                }
                , {
                    field: 'linkman',
                    width: 150,
                    title: '联系人', align: 'center'
                }
                , {
                    field: 'phone',
                    width: 180,
                    title: '手机号', align: 'center'
                }
                , {
                    field: 'userType',
                    width: 100,
                    title: '身份', align: 'center',
                    templet: function (d) {
                        if (d.userType === 1) {
                            return '普通管理员';
                        } else if (d.userType === 2) {
                            return '超级管理员';
                        }
                        return '-';
                    }
                }
                , {
                    width: 180,
                    fixed: 'right',
                    field: 'createTime', title: '创建时间', align: 'center', templet: function (d) {
                        return layui.util.toDateString(d.createTime);
                    }
                }
                , {
                    width: 180,
                    fixed: 'right',
                    field: 'id', title: '操作', align: 'center', templet: function (d) {
                        var btn = '';
                        btn += "<span style='margin-left:10px ;' class='layui-btn layui-btn-warm layui-btn-xs' onclick='showEditPage(" + d.id + ")'>编辑</span>";
                        btn += "<span style='margin-left:10px ;' class='layui-btn layui-btn-danger layui-btn-xs' onclick='deleteItem(" + d.id + ")'>删除</span>";
                        return btn;
                    }
                }
            ]]
        });

        //监听事件
        table.on('toolbar(lineToolFilter)', function (obj) {
            switch (obj.event) {
                case 'search':
                    onSearch();
                    break;
                case 'add':
                    //iframe窗
                    openIndex = layer.open({
                        type: 2,
                        title: '添加管理员',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['60%', '80%'],
                        content: '${ctxPath}/sysUser/addPage'
                    });
                    break;
            }
        });

        function onSearch() {
            var val = $('#name_search').val();
            tableIns.reload({
                where: {
                    searchContent: val
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                },
                done: function () {
                    $('#name_search').val(val);
                }
            }); //表格重载
        }
    });

    function deleteItem(id) {
        postData({
            url: '${ctxPath}/sysUser/updateState',
            data: {
                id: id,
                state: 404
            },
            success: function (res) {
                tableIns.reload();
                if (res.code === 0) {
                    layer.msg(res.msg, {icon: 1, anim: 2, time: 2000});
                } else {
                    layer.msg(res.msg, {icon: 5, anim: 6, time: 3000});
                }
            }
        });
    }

    var openIndex;

    function closeEditPage() {
        layer.close(openIndex);
        tableIns.reload();
    }

    function showEditPage(id) {
        openIndex = layer.open({
            type: 2,
            title: '修改管理员信息',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['60%', '80%'],
            content: '${ctxPath}/sysUser/editPage?id=' + id
        });
    }

</script>
</html>

