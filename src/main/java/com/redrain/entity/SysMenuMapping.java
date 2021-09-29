package com.redrain.entity;

import lombok.Data;

/**
 * @author student
 * @Description SysMenuMapping实体类
 * @date 2021-03
 */

@Data
public class SysMenuMapping extends SysMenu {

    private Long sysUserId;//管理员

    private Long menuId;//菜单
}

