package com.redrain.controller;

import com.redrain.common.constant.TokenInfoConstant;
import com.redrain.common.utils.CheckList;
import com.redrain.common.utils.CookiesUtil;
import com.redrain.common.utils.JSONUtils;
import com.redrain.common.utils.TextUtils;
import com.redrain.common.utils.redis.RedisUtils;
import com.redrain.common.utils.ConstantUtils;
import com.redrain.entity.SysMenuMapping;
import com.redrain.entity.SysUser;
import com.redrain.mapper.SysMenuMappingMapper;
import com.redrain.service.SysUserService;
import com.redrain.common.result.ReturnData;
import com.redrain.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/9 17:44
 */
@Controller
public class LoginController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    UserService userService;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SysMenuMappingMapper sysMenuMappingMapper;


    @RequestMapping("/")
    public String rootUrl() {
        return "redirect:index";
    }


    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        //判断有没有登录
        Cookie cookie = CookiesUtil.getCookieByName(request, "token");
        String token;
        if (cookie == null || TextUtils.isEmpty(token = cookie.getValue())) {
            return "login";
        }
        return getFirstPage(request, token);
    }


    @RequestMapping("login")
    public String login(HttpServletRequest request) throws Exception {
        //判断有没有登录
        Cookie cookie = CookiesUtil.getCookieByName(request, "token");
        String token;
        if (cookie == null || TextUtils.isEmpty(token = cookie.getValue())) {
            return "login";
        }
        return getFirstPage(request, token);
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //先判断有没有cookie
        Cookie cookie = CookiesUtil.getCookieByName(request, "token");
        String token;
        if (cookie != null && !StringUtils.isEmpty(token = cookie.getValue())) {
            CookiesUtil.delectCookieByName(request, response, "token");
            redisUtils.deleteKey(token);
        }
        return "redirect:login";
    }


    @RequestMapping("requestLogin")
    @ResponseBody
    public ReturnData requestLogin(SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response) {
        return sysUserService.login(loginSysUser, request, response);
    }

    @RequestMapping("requestLoginByWx")
    @ResponseBody
    public ReturnData requestLoginByWx(SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(loginSysUser, request, response);
    }

    @RequestMapping("homePage")
    public String homePage() {
        return "article/list";
    }

    private String getFirstPage(HttpServletRequest request, String token) {
        //从redis中获取token
        String firstPage = "login";
        request.setAttribute("msg", "系统错误");
        Object tokenInfo = redisUtils.getValue(token);
        if (tokenInfo == null) {
            return firstPage;
        }
        SysUser sysUser = JSONUtils.obj2pojo(tokenInfo, SysUser.class);
        String redisKeyMenuMapping = TokenInfoConstant.KEY_SYS_MENU_MAPPING + ":" + sysUser.getId();
        Object menuMappingObj = redisUtils.getValue(redisKeyMenuMapping);
        List<SysMenuMapping> menuMappings;
        if (menuMappingObj == null) {
            //从数据库查询
            SysMenuMapping sysMenuMapping = new SysMenuMapping();
            sysMenuMapping.setSysUserId(sysUser.getId());
            sysMenuMapping.setState(1);
            menuMappings = sysMenuMappingMapper.getList(sysMenuMapping);
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
            firstPage = "redirect:" + menuMappings.get(0).getUrl();
        }
        return firstPage;
    }

}
