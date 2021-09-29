package com.redrain.service;

import com.redrain.entity.SysMenu;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description SysMenuservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface SysMenuService {

	ReturnDataForLayui getList(SysMenu sysMenu);

	ReturnData add(SysMenu sysMenu);

	ReturnData delete(SysMenu sysMenu);

	ReturnData update(SysMenu sysMenu);

	ReturnData updateState(SysMenu sysMenu);

}

