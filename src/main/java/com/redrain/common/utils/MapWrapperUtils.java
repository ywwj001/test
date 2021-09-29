package com.redrain.common.utils;

import java.util.HashMap;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/23 20:10
 */
public class MapWrapperUtils extends HashMap<String, Object> {
    public static String KEY_USER_ID = "userId";

    @Override
    public MapWrapperUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static MapWrapperUtils builder(String key, Object value) {
        MapWrapperUtils wrapperUtils = new MapWrapperUtils();
        wrapperUtils.put(key, value);
        return wrapperUtils;
    }
}
