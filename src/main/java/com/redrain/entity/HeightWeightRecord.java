package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 身高体重打开记录实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class HeightWeightRecord extends PageBean {

	private BigDecimal height;//身高

	private BigDecimal weight;//体重

	private BigDecimal bmi;//BMI值

	private Date recordDay;//记录日期

	private String remark;//备注

	private Long uid;//用户

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

