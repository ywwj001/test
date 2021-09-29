package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.Dietitian;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.DietitianMapper;

import com.redrain.service.DietitianService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description Dietitianservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class DietitianServiceImpl implements DietitianService {

    @Autowired
    private DietitianMapper dietitianMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(Dietitian dietitian) {
        PageHelper.startPage(dietitian.getPage(), dietitian.getLimit());
        List<Dietitian> list = dietitianMapper.getList(dietitian);
        PageInfo<Dietitian> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(Dietitian dietitian) {
        int i = dietitianMapper.add(dietitian);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(Dietitian dietitian) {
        int i = dietitianMapper.delete(dietitian);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(Dietitian dietitian) {
        int i = dietitianMapper.update(dietitian);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(Dietitian dietitian) {
        int i = dietitianMapper.updateState(dietitian);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

