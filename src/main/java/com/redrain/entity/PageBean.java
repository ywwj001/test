package com.redrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author redrain
 * @Description 分页实体类
 * @date 2021-03
 * @qq 1351150492
 */

@Data
public class PageBean implements Serializable {
    @JsonIgnore
    private Integer page;
    @JsonIgnore
    private String searchContent;
    @JsonIgnore
    private Integer limit;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit == null ? 500 : limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}

