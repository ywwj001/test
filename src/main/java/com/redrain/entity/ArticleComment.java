package com.redrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author redrain
 * @Description 文章评论实体类
 * @date 2021-04
 * @qq 1351150492
 */

@Data
public class ArticleComment extends PageBean {

    private Long id;//ID

    private String content;//评论内容

    private Long articleId;//文章

    private Long uid;//用户

    private String nickName;//用户

    private String avatarUrl;

    private Date createTime;//评论时间

    private Date updateTime;//更新时间

    private int state = 1;//状态

}

