package com.redrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 系统菜单实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class SysMenu extends PageBean {

	private Long id;//ID

	private String name;//菜单名称

	private String url;//菜单地址

	private String icon;//图标

	private Long sort;//排序

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

