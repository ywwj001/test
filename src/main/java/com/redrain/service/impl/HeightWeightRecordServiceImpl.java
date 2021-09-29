package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.HeightWeightRecord;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.HeightWeightRecordMapper;

import com.redrain.service.HeightWeightRecordService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description HeightWeightRecordservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class HeightWeightRecordServiceImpl implements HeightWeightRecordService {

    @Autowired
    private HeightWeightRecordMapper heightWeightRecordMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(HeightWeightRecord heightWeightRecord) {
        PageHelper.startPage(heightWeightRecord.getPage(), heightWeightRecord.getLimit());
        List<HeightWeightRecord> list = heightWeightRecordMapper.getList(heightWeightRecord);
        PageInfo<HeightWeightRecord> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(HeightWeightRecord heightWeightRecord) {
        //查询该用户该日期的是否打卡，是的话需要更新打卡信息
        HeightWeightRecord heightWeightRecord_query = new HeightWeightRecord();
        heightWeightRecord_query.setUid(heightWeightRecord.getUid());
        heightWeightRecord_query.setRecordDay(heightWeightRecord.getRecordDay());
        long count = heightWeightRecordMapper.getCount(heightWeightRecord_query);
        int i;
        if (count > 0) {
            i = heightWeightRecordMapper.update(heightWeightRecord);
        } else {
            i = heightWeightRecordMapper.add(heightWeightRecord);
        }

        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(HeightWeightRecord heightWeightRecord) {
        int i = heightWeightRecordMapper.delete(heightWeightRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(HeightWeightRecord heightWeightRecord) {
        int i = heightWeightRecordMapper.update(heightWeightRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(HeightWeightRecord heightWeightRecord) {
        int i = heightWeightRecordMapper.updateState(heightWeightRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

