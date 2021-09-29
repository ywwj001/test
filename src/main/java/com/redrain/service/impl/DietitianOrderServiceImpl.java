package com.redrain.service.impl;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

import com.redrain.entity.User;
import com.redrain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.DietitianOrder;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.DietitianOrderMapper;

import com.redrain.service.DietitianOrderService;

import com.redrain.common.utils.UpdateOrInsertResultDeal;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author redrain
 * @Description DietitianOrderservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class DietitianOrderServiceImpl implements DietitianOrderService {

    @Autowired
    private DietitianOrderMapper dietitianOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(DietitianOrder dietitianOrder) {
        PageHelper.startPage(dietitianOrder.getPage(), dietitianOrder.getLimit());
        List<DietitianOrder> list = dietitianOrderMapper.getList(dietitianOrder);
        PageInfo<DietitianOrder> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(DietitianOrder dietitianOrder) {
        //查询我的余额还够不够扣除
        User user = new User();
        user.setId(dietitianOrder.getUid());
        User userDb = userMapper.getList(user).get(0);
        if (dietitianOrder.getPayPrice().doubleValue()
                - userDb.getMoneyBalance().doubleValue() > 0) {
            return ReturnData.fail("余额不足，请充值~");
        }
        int i = dietitianOrderMapper.add(dietitianOrder);
        if (i > 0) {
            //更新我的余额
            userMapper.minusBalance(dietitianOrder);
        }
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(DietitianOrder dietitianOrder) {
        int i = dietitianOrderMapper.delete(dietitianOrder);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(DietitianOrder dietitianOrder) {
        int i = dietitianOrderMapper.update(dietitianOrder);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(DietitianOrder dietitianOrder) {
        int i = dietitianOrderMapper.updateState(dietitianOrder);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

}

