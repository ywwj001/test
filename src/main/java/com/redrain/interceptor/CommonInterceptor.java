
/**
 *
 */
package com.redrain.interceptor;

import com.redrain.common.constant.TokenInfoConstant;
import com.redrain.common.exception.CustomerException;
import com.redrain.common.result.ReturnData;
import com.redrain.common.utils.CheckList;
import com.redrain.common.utils.CookiesUtil;
import com.redrain.common.utils.JSONUtils;
import com.redrain.common.utils.TextUtils;
import com.redrain.common.utils.redis.RedisUtils;
import com.redrain.common.utils.ConstantUtils;
import com.redrain.entity.SysMenuMapping;
import com.redrain.entity.SysUser;
import com.redrain.mapper.SysMenuMappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author tfj
 * 2014-8-1
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysMenuMappingMapper sysMenuMappingMapper;

	/*
	 * 利用正则映射到需要拦截的路径    
	 
    private String mappingURL;
    
    public void setMappingURL(String mappingURL) {    
               this.mappingURL = mappingURL;    
    }
  */

    /**
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
//    		RequestUtil.saveRequest();
        }
        log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        log.info("requestUri:" + requestUri);
        log.info("contextPath:" + contextPath);
        log.info("url:" + url);
        //先去cookie中获取，就知道是不是浏览器访问的
        Cookie cookie = CookiesUtil.getCookieByName(request, "token");
        String token;
        boolean fromWeb = true;
        if (cookie == null || TextUtils.isEmpty(token = cookie.getValue())) {
            token = request.getHeader("token");
            if (TextUtils.isEmpty(token)) {
                //说明既不是浏览器后台也不是小程序访问
                throw new CustomerException("您没有访问权限");
            }
            fromWeb = false;//说明是小程序访问
        } else {
            //重新设置cookie的过期时间
            CookiesUtil.setCookie(response, "token", token, ConstantUtils.key_token_timeout);
        }
        //从redis中获取token
        Object tokenInfo = redisUtils.getValue(token);
        if (tokenInfo == null) {
            throw new CustomerException(ReturnData.KEY_CODE_LOGIN_TIMEOUT, "请先登录");
        }
        if (fromWeb) {
            //重新设置过期时间为24小时，刷新时间
            redisUtils.expire(token, ConstantUtils.key_token_timeout);
            SysUser sysUser = com.redrain.common.utils.JSONUtils.obj2pojo(tokenInfo, SysUser.class);
            if (sysUser == null) {
                //重新设置过期时间为24小时，刷新时间
                redisUtils.deleteKey(token);
                throw new CustomerException(ReturnData.KEY_CODE_LOGIN_TIMEOUT, "请先登录");
            }
            Map<String, String[]> parameterMap = request.getParameterMap();
            System.out.println(parameterMap.toString());
            //重新设置过期时间为24小时，刷新时间
            redisUtils.expire(TokenInfoConstant.TOKEN_TYPE_ADMIN + "_" + sysUser.getId(), 24 * 60 * 60);
        } else {
            //重新设置过期时间为24*7小时，刷新时间
            redisUtils.expire(token, ConstantUtils.key_token_wx_timeout);
        }
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("==============执行顺序: 2、postHandle================");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        //先去cookie中获取，就知道是不是浏览器访问的
        Cookie cookie = CookiesUtil.getCookieByName(request, "token");
        String token;
        boolean fromWeb = true;
        if (cookie != null && !TextUtils.isEmpty(token = cookie.getValue())) {
            //从redis中获取token
            Object tokenInfo = redisUtils.getValue(token);
            if (modelAndView != null) {
                SysUser sysUser = JSONUtils.obj2pojo(tokenInfo, SysUser.class);
                //获取redis中用户的权限
                String redisKeyMenuMapping = TokenInfoConstant.KEY_SYS_MENU_MAPPING + ":" + sysUser.getId();
                Object menuMappingObj = redisUtils.getValue(redisKeyMenuMapping);
                List<SysMenuMapping> menuMappings;
                if (menuMappingObj == null) {
                    //从数据库查询
                    SysMenuMapping sysMenuMapping = new SysMenuMapping();
                    sysMenuMapping.setSysUserId(sysUser.getId());
                    sysMenuMapping.setState(1);
                    menuMappings = sysMenuMappingMapper.getList(sysMenuMapping);
                    if (CheckList.listIsEmpty(menuMappings)) {
                        throw new CustomerException("您的账号还没有未分配菜单权限呢");
                    }
                } else {
                    ArrayList list = (ArrayList) menuMappingObj;
                    menuMappings = new ArrayList<>();
                    for (Object o : list) {
                        menuMappings.add(JSONUtils.obj2pojo(o, SysMenuMapping.class));
                    }
                }
                if (!CheckList.listIsEmpty(menuMappings)) {
                    redisUtils.setValue(redisKeyMenuMapping, menuMappings);
                    redisUtils.expire(redisKeyMenuMapping, ConstantUtils.key_token_timeout);//24小时后过期
                    //将菜单放入到request域中
                    modelAndView.addObject("currentPath", url);
                    modelAndView.addObject("myMuneList", menuMappings);
                    modelAndView.addObject("myHeadImg", sysUser.getHeadImg());
                    modelAndView.addObject("myName", sysUser.getName());
                    modelAndView.addObject("mySysyUserId", sysUser.getId());
                }
            }
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("==============执行顺序: 3、afterCompletion================");
    }

}