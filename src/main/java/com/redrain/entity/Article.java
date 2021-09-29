package com.redrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 文章实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class Article extends PageBean {

	private Long id;//ID

	private String title;//文章标题

	private String detail;//文章详情

	private String coverImg;//封面图

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

