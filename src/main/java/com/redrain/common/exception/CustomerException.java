package com.redrain.common.exception;

import com.alibaba.druid.support.json.JSONUtils;
import com.redrain.common.result.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomerException extends RuntimeException implements HandlerExceptionResolver {
    private static ThreadLocal<ReturnData> threadLocal = new ThreadLocal<>();

    public CustomerException() {
    }

    public CustomerException(String message) {
        super(message);
        threadLocal.set(ReturnData.fail(message));
    }

    public CustomerException(int code, String message) {
        super(message);
        threadLocal.set(ReturnData.fail(code, message));
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o, Exception ex) {
        log.info("CustomerException handler  " + ex.getMessage());
        ModelAndView mv = new ModelAndView();
        ReturnData returnData = threadLocal.get();
        threadLocal.remove();
        String msg = "";
        if (ex instanceof CustomerException) {
            msg = returnData.getMsg();
        } else {
            msg = ex.getMessage();
        }
        if (!((request.getHeader("accept") != null
                && request.getHeader("accept").contains("application/json")) ||
                (request.getHeader("X-Requested-With") != null &&
                        request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            //如果不是ajax,返回页面
            String viewName = "redirect:/login";
            mv.setViewName(viewName);
            mv.addObject(ReturnData.KEY_MSG, msg);
        } else {
            response.setStatus(HttpStatus.OK.value());
            // 设置ContentType
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            // 避免乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<>();
                map.put(ReturnData.KEY_CODE, returnData == null ? -1 : returnData.getCode());
                map.put(ReturnData.KEY_MSG, msg);
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }
}