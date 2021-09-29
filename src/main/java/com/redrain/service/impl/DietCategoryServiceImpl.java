package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.DietCategory;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.DietCategoryMapper;

import com.redrain.service.DietCategoryService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description DietCategoryservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class DietCategoryServiceImpl implements DietCategoryService {

    @Autowired
    private DietCategoryMapper dietCategoryMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(DietCategory dietCategory) {
        PageHelper.startPage(dietCategory.getPage(), dietCategory.getLimit());
        List<DietCategory> list = dietCategoryMapper.getList(dietCategory);
        PageInfo<DietCategory> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(DietCategory dietCategory) {
        int i = dietCategoryMapper.add(dietCategory);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(DietCategory dietCategory) {
        int i = dietCategoryMapper.delete(dietCategory);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(DietCategory dietCategory) {
        int i = dietCategoryMapper.update(dietCategory);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(DietCategory dietCategory) {
        int i = dietCategoryMapper.updateState(dietCategory);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

