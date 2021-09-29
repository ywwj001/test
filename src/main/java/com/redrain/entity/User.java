package com.redrain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author redrain
 * @Description 用户实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class User extends PageBean {

	private Long id;//

	private String username;//登录名

	private String password;//密码

	private String nickName;//用户昵称

	private int gender;//用户性别0未知，1男，2，女

	private String avatarUrl;//用户头像图片

	private String phone;//手机号

	private String address;//地址

	private BigDecimal moneyBalance;//余额

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

