package com.redrain.common.utils;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/17 21:09
 */
public class PriceCheckUtils {
    public static boolean isValidatePrice(Long price) {
        if (price == null || price <= 0) {
            return false;
        }
        return true;
    }
}
