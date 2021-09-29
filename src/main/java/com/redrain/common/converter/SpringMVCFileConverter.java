package com.redrain.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/13 13:01
 */
public class SpringMVCFileConverter implements Converter<String, CommonsMultipartFile> {
    public SpringMVCFileConverter() {
        super();
    }

    @Override
    public CommonsMultipartFile convert(String source) {
        return null;
    }
}
