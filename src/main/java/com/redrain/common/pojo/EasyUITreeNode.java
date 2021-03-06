package com.redrain.common.pojo;

import java.io.Serializable;

/**
 * tree 控件的节点的pojo
 *
 * @author ljh
 * @version 1.0
 * @title EasyUITreeNode.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 */
public class EasyUITreeNode implements Serializable {
    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
