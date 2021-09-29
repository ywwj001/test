package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.FitnessMeal;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.FitnessMealMapper;

import com.redrain.service.FitnessMealService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description FitnessMealservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class FitnessMealServiceImpl implements FitnessMealService {

    @Autowired
    private FitnessMealMapper fitnessMealMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(FitnessMeal fitnessMeal) {
        PageHelper.startPage(fitnessMeal.getPage(), fitnessMeal.getLimit());
        List<FitnessMeal> list = fitnessMealMapper.getList(fitnessMeal);
        PageInfo<FitnessMeal> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(FitnessMeal fitnessMeal) {
        int i = fitnessMealMapper.add(fitnessMeal);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(FitnessMeal fitnessMeal) {
        int i = fitnessMealMapper.delete(fitnessMeal);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(FitnessMeal fitnessMeal) {
        int i = fitnessMealMapper.update(fitnessMeal);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(FitnessMeal fitnessMeal) {
        int i = fitnessMealMapper.updateState(fitnessMeal);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

