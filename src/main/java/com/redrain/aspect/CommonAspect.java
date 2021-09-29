package com.redrain.aspect;

import com.redrain.common.exception.CustomerException;
import com.redrain.common.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chh
 * @version 1.0.0
 * @class MyApiLogAspect
 * @classdesc 实现ApiLog的功能，即打印请求日志和响应日志
 * @date 2020/8/26  15:07
 * @see
 * @since
 */
@Aspect
@Component("commonAspect")
@Slf4j
public class CommonAspect {
    /**
     * 记录业务请求的时间
     */
    private long req;

    private String reqTime;

    /**
     * @param: void
     * @return: void
     * @desc: 定义空方法用于切点表达式
     * @see
     * @since
     */
    @Pointcut("execution(* com.redrain.controller.*.*(..))")
    public void pointcut() {
        //do nothing just for filtering
    }

    @SuppressWarnings("unchecked")
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result;
        long beginTime = System.currentTimeMillis();
        //请求的方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof Map) {
            Map requestMap = (Map) args[0];
//            requestMap.put("userId", );
            Object arg = args[0];
            String requestJsonStr = JSONUtils.obj2json(arg);
        }
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new CustomerException(throwable.getMessage());
        }
        //获取Request请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //设置IP地址
//        String ip = getIpAddress(request);
        //记录时间（毫秒）
        long time = System.currentTimeMillis() - beginTime;
        return result;
    }
}
