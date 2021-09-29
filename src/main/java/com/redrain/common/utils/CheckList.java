package com.redrain.common.utils;

import java.util.List;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/13 14:58
 */
public class CheckList {
    public static boolean listIsEmpty(List data) {
        if (data == null || data.size() == 0) {
            return true;
        }
        return false;
    }
}
