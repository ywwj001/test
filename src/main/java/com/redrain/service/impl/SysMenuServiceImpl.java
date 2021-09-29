package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.SysMenu;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.SysMenuMapper;

import com.redrain.service.SysMenuService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description SysMenuservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(SysMenu sysMenu) {
        PageHelper.startPage(sysMenu.getPage(), sysMenu.getLimit());
        List<SysMenu> list = sysMenuMapper.getList(sysMenu);
        PageInfo<SysMenu> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(SysMenu sysMenu) {
        int i = sysMenuMapper.add(sysMenu);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(SysMenu sysMenu) {
        int i = sysMenuMapper.delete(sysMenu);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(SysMenu sysMenu) {
        int i = sysMenuMapper.update(sysMenu);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(SysMenu sysMenu) {
        int i = sysMenuMapper.updateState(sysMenu);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

