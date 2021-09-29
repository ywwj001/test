package com.redrain.common.utils;

import com.redrain.common.result.ReturnData;

public class UpdateOrInsertResultDeal {
    public static ReturnData dealWith(int i, String successMsg, String failMsg) {
        return i > 0 ? ReturnData.success(successMsg) : ReturnData.fail(failMsg);
    }

    public static ReturnData dealWith(int i) {
        return i > 0 ? ReturnData.success() : ReturnData.fail();
    }
}
