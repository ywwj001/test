package com.redrain.mapper;

import com.redrain.entity.SysMenu;

import java.util.List;

/**
 * @author redrain
 * @Description SysMenuMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface SysMenuMapper {

	long getCount(SysMenu sysMenu);

	List<SysMenu> getList(SysMenu sysMenu);

	int add(SysMenu sysMenu);

	int delete(SysMenu sysMenu);

	int update(SysMenu sysMenu);

	int updateState(SysMenu sysMenu);

}

