package com.redrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 后台用户登录实体类
 * @date 2021-03
 * @qq 1351150492
 */

@Data
public class SysUser extends PageBean {

    private Long id;//主键id

    private String username;//登陆名

    private String password;//密码

    private String salt;//密码加密盐

    private String name;//用户名

    private int sex;//性别

    private int age;//年龄

    private String linkman;//联系人

    private String phone;//手机号

    private String headImg;//头像

    private Integer userType;//用户类别 1普通 2超级管理员

    private Integer state;//用户状态,1正常 2禁用

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    @JsonIgnore
    private String searchContent;

}

