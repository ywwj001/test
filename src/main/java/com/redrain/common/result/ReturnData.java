package com.redrain.common.result;

import java.util.HashMap;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/10 13:17
 */
public class ReturnData extends HashMap<String, Object> {
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";

    public static final Integer KEY_CODE_GROUP_COMPLETE = -100;
    /**
     * 未完善用户资料
     */
    public static final Integer KEY_CODE_NOT_GET_USERINFO = -3;
    /**
     * 登录超时
     */
    public static final Integer KEY_CODE_LOGIN_TIMEOUT = -2;
    /**
     * 失败
     */
    public static final Integer KEY_CODE_ERROR = -1;
    /**
     * 成功
     */
    public static final Integer KEY_CODE_SUCCESS = 0;


    public ReturnData() {
        put(KEY_CODE, KEY_CODE_SUCCESS);
        put(KEY_MSG, "请求成功");
    }

    public static ReturnData success() {
        return new ReturnData();
    }

    public static ReturnData success(String msg) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_MSG, msg);
        return returnData;
    }

    public static ReturnData success(Object data, String msg) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_MSG, msg);
        returnData.put(KEY_DATA, data);
        return returnData;
    }

    public static ReturnData success(Object data) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_DATA, data);
        return returnData;
    }

    public static ReturnData fail() {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_CODE, KEY_CODE_ERROR);
        returnData.put(KEY_MSG, "请求失败");
        return returnData;
    }


    public static ReturnData fail(String msg) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_CODE, KEY_CODE_ERROR);
        returnData.put(KEY_MSG, msg);
        return returnData;
    }

    public static ReturnData fail(int code, String msg) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_CODE, code);
        returnData.put(KEY_MSG, msg);
        return returnData;
    }

    public static ReturnData loginTimeout() {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_CODE, KEY_CODE_LOGIN_TIMEOUT);
        returnData.put(KEY_MSG, "登陆过期，请您重新登陆");
        return returnData;
    }


    public static ReturnData notGetUserInfo(String token) {
        ReturnData returnData = new ReturnData();
        returnData.put(KEY_CODE, KEY_CODE_NOT_GET_USERINFO);
        returnData.put(KEY_MSG, "用户信息不完善");
        returnData.put(KEY_DATA, token);
        return returnData;
    }


    public int getCode() {
        return (int) get(KEY_CODE);
    }

    public Object getData() {
        return get(KEY_DATA);
    }

    public String getMsg() {
        return (String) get(KEY_MSG);
    }

    @Override
    public Object put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public boolean isSuccess() {
        return KEY_CODE_SUCCESS == get(KEY_CODE);
    }

}