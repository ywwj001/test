package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 营养师预约记录实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class DietitianOrder extends PageBean {

	private Long id;//ID

	private BigDecimal payPrice;// 支付金额

	private Long uid;//用户

	private Long dietitianId;//营养师

	private String dietitianName;//营养师

	private Date appointmentTime;//预约时间

	private Date createTime;//支付时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

