//package com.redrain.common.filter;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.redrain.common.exception.CustomerException;
//import com.redrain.common.http.CusAccessObjectUtil;
//import com.redrain.common.jwt.JwtUtil;
//import com.redrain.common.result.ReturnData;
//import com.redrain.common.utils.MapWrapperUtils;
//import com.redrain.common.utils.TextUtils;
//import com.redrain.entity.SysUser;
//import com.redrain.entity.User;
//import com.nimbusds.jose.JOSEException;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author 张红雨【1351150492@qq.com】
// * @Date 2019/3/10 14:04
// */
//public class MyWebFilter extends GenericFilterBean {
//    public static final String FROM_MOBILE = "mobile";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
//        HttpServletRequest req = ((HttpServletRequest) request);
//        HttpServletResponse resp = ((HttpServletResponse) response);
//        System.out.println(req.getRequestURL());
//
//        List<String> urlIgnorelist = new ArrayList<>();
//        urlIgnorelist.add("/static/");
//        urlIgnorelist.add("/upload/");
//        urlIgnorelist.add("/user/getUserByCode");
//        urlIgnorelist.add("/requestLogin");
//        urlIgnorelist.add("/login");
//        urlIgnorelist.add("/logout");
//        urlIgnorelist.add("/orders/pay/notify");
//        urlIgnorelist.add("/orders/refund/notify");
//
//        boolean isNeedDealwith = true;
//        for (String path : urlIgnorelist) {
//            if (req.getServletPath().contains(path)) {
//                isNeedDealwith = false;
//                break;
//            }
//        }
//        try {
//            //判断用户的请求来源
//            String from = req.getHeader("from");
//            if (TextUtils.isEmpty(from)) {
//                //web端
//                if (isNeedDealwith) {
//                    //判断当前用户是否登录
//                    HttpSession session = req.getSession();
//                    SysUser sysUser = (SysUser) session.getAttribute("userInfo");
//                    if (sysUser == null) {
//                        resp.sendRedirect(req.getContextPath() + "/login");
//                    } else {
//                        //重新设置，这样就会重新计算失效时间
//                        session.setAttribute("userInfo", sysUser);
//                    }
//                }
//                filterChain.doFilter(req, resp);
//            } else if (from.equals(FROM_MOBILE)) {
//                //移动端
//                resp.setCharacterEncoding("utf-8");
//                resp.setContentType("application/json; charset=utf-8");
//                if (!isNeedDealwith) {
//                    filterChain.doFilter(req, resp);
//                    return;
//                }
//                String token = req.getHeader("token");
//                if (TextUtils.isEmpty(token)) {
//                    makeResponse(resp, ReturnData.fail("请携带token"));
//                }
//                ReturnData returnData = JwtUtil.getTokenData(token);
//                if (returnData.getCode() != 200) {
//                    makeResponse(resp, returnData);
//                }
//                User user = (User) returnData.getData();
//                if (user == null || user.getId() == null) {
//                    makeResponse(resp, ReturnData.fail("token有误"));
//                }
//                //利用原始的request对象创建自己扩展的request对象并添加自定义参数
//                RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(req);
//                Map<String, Object> param = new HashMap<>();
//                param.put(MapWrapperUtils.KEY_USER_ID, user.getId());
//                param.put("ip", CusAccessObjectUtil.getIpAddress(req));
//                requestParameterWrapper.addParameters(param);
//                filterChain.doFilter(requestParameterWrapper, resp);
//            } else {
//                makeResponse(resp, ReturnData.fail("from参数有误"));
//            }
//        } catch (JOSEException e) {
//            makeResponse(resp, ReturnData.fail("token异常"));
//        } catch (Exception e) {
//            Throwable throwable = e.getCause();
//            if (throwable instanceof CustomerException) {
//                makeResponse(resp, ReturnData.fail(((CustomerException) throwable).getMsgDes()));
//            } else {
//                makeResponse(resp, ReturnData.fail(e.getMessage()));
//            }
//        }
//
//    }
//
//
//    private void makeResponse(HttpServletResponse resp, ReturnData returnData) {
//        try {
//            resp.getWriter().print(JSONUtils.toJSONString(returnData));
//            resp.getWriter().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
