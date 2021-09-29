package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 营养师实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class Dietitian extends PageBean {

	private Long id;//ID

	private String nickName;//营养师昵称

	private int gender;//性别

	private String avatarUrl;//头像

	private String phone;//手机号

	private BigDecimal price;//价格

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

