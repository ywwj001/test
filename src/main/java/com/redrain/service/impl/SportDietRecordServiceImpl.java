package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.HeightWeightRecord;
import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.SportDietRecord;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.SportDietRecordMapper;

import com.redrain.service.SportDietRecordService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description SportDietRecordservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class SportDietRecordServiceImpl implements SportDietRecordService {

    @Autowired
    private SportDietRecordMapper sportDietRecordMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(SportDietRecord sportDietRecord) {
        PageHelper.startPage(sportDietRecord.getPage(), sportDietRecord.getLimit());
        List<SportDietRecord> list = sportDietRecordMapper.getList(sportDietRecord);
        PageInfo<SportDietRecord> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(SportDietRecord sportDietRecord) {
        //查询该用户该日期的是否打卡，是的话需要更新打卡信息
        SportDietRecord sportDietRecord_query = new SportDietRecord();
        sportDietRecord_query.setUid(sportDietRecord.getUid());
        sportDietRecord_query.setRecordDay(sportDietRecord.getRecordDay());
        long count = sportDietRecordMapper.getCount(sportDietRecord_query);
        int i;
        if (count > 0) {
            i = sportDietRecordMapper.update(sportDietRecord);
        } else {
            i = sportDietRecordMapper.add(sportDietRecord);
        }
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(SportDietRecord sportDietRecord) {
        int i = sportDietRecordMapper.delete(sportDietRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(SportDietRecord sportDietRecord) {
        int i = sportDietRecordMapper.update(sportDietRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(SportDietRecord sportDietRecord) {
        int i = sportDietRecordMapper.updateState(sportDietRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

