package com.redrain.mapper;

import com.redrain.entity.SysMenuMapping;

import java.util.List;

/**
 * @author redrain
 * @Description SysMenuMappingMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface SysMenuMappingMapper {

	long getCount(SysMenuMapping sysMenuMapping);

	List<SysMenuMapping> getList(SysMenuMapping sysMenuMapping);

	int add(SysMenuMapping sysMenuMapping);

	int delete(SysMenuMapping sysMenuMapping);

	int update(SysMenuMapping sysMenuMapping);

	int updateState(SysMenuMapping sysMenuMapping);

}

