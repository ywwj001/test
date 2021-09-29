package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.SysMenuMapping;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.SysMenuMappingMapper;

import com.redrain.service.SysMenuMappingService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description SysMenuMappingservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class SysMenuMappingServiceImpl implements SysMenuMappingService {

    @Autowired
    private SysMenuMappingMapper sysMenuMappingMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(SysMenuMapping sysMenuMapping) {
        PageHelper.startPage(sysMenuMapping.getPage(), sysMenuMapping.getLimit());
        List<SysMenuMapping> list = sysMenuMappingMapper.getList(sysMenuMapping);
        PageInfo<SysMenuMapping> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(SysMenuMapping sysMenuMapping) {
        int i = sysMenuMappingMapper.add(sysMenuMapping);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(SysMenuMapping sysMenuMapping) {
        int i = sysMenuMappingMapper.delete(sysMenuMapping);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(SysMenuMapping sysMenuMapping) {
        int i = sysMenuMappingMapper.update(sysMenuMapping);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(SysMenuMapping sysMenuMapping) {
        int i = sysMenuMappingMapper.updateState(sysMenuMapping);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

