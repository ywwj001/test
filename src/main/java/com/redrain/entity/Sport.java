package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 运动实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class Sport extends PageBean {

	private Long id;//ID

	private String sportName;//运动名称

	private String description;//简介

	private BigDecimal calorie;//热量(卡路里)

	private String calorieUnit;//单位

	private String coverImg;//封面图

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

