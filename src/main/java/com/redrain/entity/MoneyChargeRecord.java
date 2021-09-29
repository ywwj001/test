package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 充值记录实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class MoneyChargeRecord extends PageBean {

	private Long id;//ID

	private BigDecimal money;//充值金额

	private Long uid;//充值人

	private String nickName;//充值人

	private Date createTime;//充值时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

