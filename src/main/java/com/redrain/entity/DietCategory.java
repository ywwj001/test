package com.redrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 食谱分类实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class DietCategory extends PageBean {

	private Long id;//ID

	private String name;//分类名称

	private Date createTime;//创建时间

	private Date updateTime;//更新时间

	private int state=1;//状态

}

