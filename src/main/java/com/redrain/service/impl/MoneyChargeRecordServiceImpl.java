package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import com.redrain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.MoneyChargeRecord;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.MoneyChargeRecordMapper;

import com.redrain.service.MoneyChargeRecordService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description MoneyChargeRecordservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class MoneyChargeRecordServiceImpl implements MoneyChargeRecordService {

    @Autowired
    private MoneyChargeRecordMapper moneyChargeRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(MoneyChargeRecord moneyChargeRecord) {
        PageHelper.startPage(moneyChargeRecord.getPage(), moneyChargeRecord.getLimit());
        List<MoneyChargeRecord> list = moneyChargeRecordMapper.getList(moneyChargeRecord);
        PageInfo<MoneyChargeRecord> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(MoneyChargeRecord moneyChargeRecord) {
        int i = moneyChargeRecordMapper.add(moneyChargeRecord);
        //
        if (i > 0) {
            //更新我的余额
            userMapper.updateBalance(moneyChargeRecord);
        }
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(MoneyChargeRecord moneyChargeRecord) {
        int i = moneyChargeRecordMapper.delete(moneyChargeRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(MoneyChargeRecord moneyChargeRecord) {
        int i = moneyChargeRecordMapper.update(moneyChargeRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(MoneyChargeRecord moneyChargeRecord) {
        int i = moneyChargeRecordMapper.updateState(moneyChargeRecord);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

