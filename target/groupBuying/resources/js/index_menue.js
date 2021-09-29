var menus = [

    {
        name: '招生信息处',
        menus: [
            {
                name: '学生信息管理',
                url: 'student/listPage'
            },
            {
                name: '院系管理',
                url: 'department/listPage'
            },
            {
                name: '专业管理',
                url: 'major/listPage'
            },
            {
                name: '班级管理',
                url: 'classinfo/listPage'
            }
        ]
    },
    {
        name: '行政处',
        menus: [
            {
                name: '教职员工基础信息',
                url: 'teacher/listPage'
            },
            {
                name: '官网新闻发布',
                url: 'news/listPage'
            },
            {
                name: '党建工作管理',
                url: 'news/listPage'
            },
            {
                name: '其他信息发布',
                url: 'news/listPage'
            }
        ]
    },

    {
        name: '教务处',
        menus: [
            {
                name: '教职员工管理',
                url: 'teacher/listPage'
            },
            {
                name: '班级管理',
                url: 'classinfo/listPage'
            },
            {
                name: '课程管理',
                url: 'curriculumSchedule/listPage'
            },
            {
                name: '成绩管理',
                url: 'academicRecord/listPage'
            }
        ]
    },

    {
        name: '学生处',
        menus: [
            {
                name: '学生考核',
                url: 'goods/listPage'
            },
            {
                name: '学生信息查询',
                url: 'orders/listPage'
            },
            {
                name: '班级考核',
                url: 'orders/listPage'
            },
            {
                name: '班级流失学生统计',
                url: 'orders/listPage'
            }
        ]
    },
    {
        name: '就业处',
        menus: [
            {
                name: '勤工俭学管理',
                url: 'workStudyRecord/listPage'
            },
            {
                name: '顶岗实习管理',
                url: 'postPractice/listPage'
            }
        ]
    },
    {
        name: '财务处',
        menus: [
            {
                name: '费用管理',
                url: 'user/listPage'
            },
            {
                name: '固定资产管理',
                url: 'user/listPage'
            }
        ]
    },
    {
        name: '系统管理',
        menus: [
            {
                name: '系统管理',
                url: 'user/listPage'
            }
        ]
    }
]
//JavaScript代码区域
layui.use('element', function () {
    var element = layui.element;
    initMenu();
    element.render('nav_layui');
});

function initMenu() {
    $('#nav_layui').empty();
    var leftMenu = '';
    $.each(menus, function (index, parent) {
        leftMenu += '<li class="layui-nav-item">';
        leftMenu += '<a class="" href="javascript:;">' + parent.name + '</a>';
        leftMenu += '<dl class="layui-nav-child">';
        $.each(parent.menus, function (childIndex, child) {
            var childEl = '<dd>' +
                '<a href="' + child.url + '" target="myframe">' + child.name + '</a>' +
                '</dd>';
            leftMenu += childEl;
        });
        leftMenu += '</dl>';
        leftMenu += '</li>';
    });
    $('#nav_layui').html(leftMenu);
}

