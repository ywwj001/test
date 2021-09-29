package com.redrain.common.utils;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/10 13:20
 */
public class TextUtils {
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.toString().trim().length() <= 0) {
            return true;
        }
        return false;
    }
}
