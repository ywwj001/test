package com.redrain.service;

import com.redrain.common.result.ReturnData;
import com.redrain.common.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    protected RedisUtils redisUtils;

    public ReturnData setFailResult() {
        return setFailResult("失败");
    }

    public ReturnData setFailResult(String msg) {
        return ReturnData.fail(msg);
    }

    public ReturnData setSuccessResult(Object o) {
        return ReturnData.success(o);
    }

    public ReturnData setSuccessMsg(String msg) {
        return ReturnData.success(msg);
    }

    public ReturnData setSuccessResult() {
        return ReturnData.success("成功");
    }

}
