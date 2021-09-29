package com.redrain.common.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/13 13:01
 */
public class SpringMVCLongConverter implements Converter<String, Long> {
    public SpringMVCLongConverter() {
        super();
    }

    @Override
    public Long convert(String source) {
        if (source == null || !isValidLong(source)) {
            return null;
        }
        return Long.parseLong(source);
    }

    public static boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
