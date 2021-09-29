package com.redrain.service.impl;

import com.redrain.common.constant.TokenInfoConstant;
import com.redrain.common.jwt.JwtUtil;
import com.redrain.common.result.ReturnData;

import com.redrain.common.utils.*;
import com.redrain.entity.ReturnDataForLayui;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.redrain.entity.SysUser;
import com.redrain.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.redrain.entity.User;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.redrain.mapper.UserMapper;

import com.redrain.service.UserService;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author redrain
 * @Description Userservice实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(User user) {
        PageHelper.startPage(user.getPage(), user.getLimit());
        List<User> list = userMapper.getList(user);
        PageInfo<User> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(User user) {
        int i = userMapper.add(user);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(User user) {
        int i = userMapper.delete(user);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(User user) {
        int i = userMapper.update(user);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(User user) {
        int i = userMapper.updateState(user);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Override
    public ReturnData login(SysUser loginUser, HttpServletRequest request, HttpServletResponse response) {
        //数据库查询用户登录信息
        User user = new User();
        user.setUsername(loginUser.getUsername());
        List<User> list = userMapper.getList(user);
        if (CheckList.listIsEmpty(list)) {
            return setFailResult("用户不存在");
        }
        User userDb = list.get(0);
        if (userDb.getState() != 1) {
            return setFailResult("还用户已被删除");
        }
        //校验密码
        String password = userDb.getPassword();
        if (!password.equals(loginUser.getPassword())) {
            return setFailResult("密码有误");
        }
        //登录成功，将用户令牌存储在redis中
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", userDb.getId());
        tokenMap.put("nickName", userDb.getNickName());
        tokenMap.put(TokenInfoConstant.TOKEN_TYPE_KEY, TokenInfoConstant.TOKEN_TYPE_BUYER);
        tokenMap.put("exp", DateTimeUtils.getSomeDay(new Date(), 1));
        String token = JwtUtil.creatToken(tokenMap);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("userName", userDb.getUsername());
        resultMap.put("avatarUrl", userDb.getAvatarUrl());
        //将用户信息存储在redis中,24小时有效期
        redisUtils.setValueExpire(token, userDb, ConstantUtils.key_token_wx_timeout);
        redisUtils.setValueExpire("wxUser_" + userDb.getId(), token, ConstantUtils.key_token_wx_timeout);
        //设置cookie
        CookiesUtil.setCookie(response, "token", token, ConstantUtils.key_token_wx_timeout);
        return setSuccessResult(resultMap);
    }
}

