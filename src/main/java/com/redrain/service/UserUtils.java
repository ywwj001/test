package com.redrain.service;

import com.redrain.common.result.ReturnData;
import com.redrain.common.utils.JSONUtils;
import com.redrain.common.utils.TextUtils;
import com.redrain.common.utils.redis.RedisUtils;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserUtils {

    @Autowired
    private RedisUtils redisUtils;

    public ReturnData setUserId(HttpServletRequest request, User user) {
        //获取我的个人信息
        //解析token，获取userId
        String token = request.getHeader("token");
        if (TextUtils.isEmpty(token)) {
            return ReturnData.fail("登录过期，请重新进入");
        }
        //从redis中获取token
        Object tokenInfo = redisUtils.getValue(token);
        User user_redis = JSONUtils.obj2pojo(tokenInfo, User.class);
        if (user_redis == null || user_redis.getId() == null) {
            redisUtils.deleteKey(token);
            return ReturnData.fail("登录过期，请重新进入");
        }
        user.setId(user_redis.getId());
        return ReturnData.success();
    }
}
