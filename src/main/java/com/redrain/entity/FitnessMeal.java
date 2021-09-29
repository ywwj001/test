package com.redrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 健身餐实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class FitnessMeal extends PageBean {

	private Long id;//ID

	private String name;//健身餐名称

	private String description;//简介

	private String constituents;//主要成分

	private String coverImg;//封面图

	private String crowd;//适合人群

	private String detail;//做法

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

