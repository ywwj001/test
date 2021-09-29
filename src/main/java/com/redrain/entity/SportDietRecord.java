package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 运动食谱打卡记录实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class SportDietRecord extends PageBean {

	private BigDecimal calorieIn;//摄入热量

	private BigDecimal calorieOut;//消耗热量

	private BigDecimal calorieBalance;//计算结果

	private Date recordDay;//记录日期

	private String dietTarget;//饮食目标

	private Long uid;//用户

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

