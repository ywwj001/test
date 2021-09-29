package com.redrain.service;

import com.redrain.entity.SysMenuMapping;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description SysMenuMappingservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface SysMenuMappingService {

	ReturnDataForLayui getList(SysMenuMapping sysMenuMapping);

	ReturnData add(SysMenuMapping sysMenuMapping);

	ReturnData delete(SysMenuMapping sysMenuMapping);

	ReturnData update(SysMenuMapping sysMenuMapping);

	ReturnData updateState(SysMenuMapping sysMenuMapping);

}

