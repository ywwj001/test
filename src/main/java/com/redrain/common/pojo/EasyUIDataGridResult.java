package com.redrain.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * datagrid 展示数据的POJO 包括商品的POJO
 *
 * @title EasyUIDataGridResult.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh
 * @version 1.0
 */

public class EasyUIDataGridResult implements Serializable {

    private Integer total = 0;

    private List rows = new ArrayList();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
