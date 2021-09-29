package com.redrain.mapper;


import com.redrain.entity.SysUser;

import java.util.List;

/**
 * @author redrain
 * @Description SysUserMapper
 * @date 2021-03
 * @qq 1351150492
 */

public interface SysUserMapper {

	long getCount(SysUser sysUser);

	List<SysUser> getList(SysUser sysUser);

	int add(SysUser sysUser);

	int delete(SysUser sysUser);

	int update(SysUser sysUser);

	int updateState(SysUser sysUser);

    int updatePwd(SysUser sysUser);
}

