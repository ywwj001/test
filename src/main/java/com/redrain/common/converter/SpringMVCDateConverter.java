package com.redrain.common.converter;

import com.redrain.common.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/13 13:01
 */
public class SpringMVCDateConverter implements Converter<String, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMVCDateConverter.class);

    public SpringMVCDateConverter() {
        super();
    }

    @Override
    public Date convert(String source) {
        if (TextUtils.isEmpty(source)) {
            return null;
        }
        //编写时间转换器，支持多种时间格式
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            // 日期格式 yyyy/MM/dd
            if (source.contains("/")) {
                if (source.contains(":")) {
                    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                } else {
                    sdf = new SimpleDateFormat("yyyy/MM/dd");
                }
            } else if (source.contains("-")) {
                if (source.contains(":")) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
            } else {
                // 其他格式请添加
            }
            return sdf.parse(source);
        } catch (ParseException e) {
            LOGGER.error("对日期进行格式转换异常！期望的格式：{}", e.getMessage());
            return null;
        }
    }
}
