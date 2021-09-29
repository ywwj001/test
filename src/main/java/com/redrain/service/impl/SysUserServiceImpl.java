package com.redrain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.redrain.common.constant.TokenInfoConstant;
import com.redrain.common.exception.CustomerException;
import com.redrain.common.jwt.JwtUtil;
import com.redrain.common.result.ReturnData;
import com.redrain.common.utils.*;
import com.redrain.common.utils.ConstantUtils;
import com.redrain.entity.SysMenu;
import com.redrain.entity.SysMenuMapping;
import com.redrain.mapper.SysMenuMapper;
import com.redrain.mapper.SysMenuMappingMapper;
import com.redrain.mapper.SysUserMapper;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.entity.SysUser;
import com.redrain.service.BaseService;
import com.redrain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author redrain
 * @Description SysUserservice实现类
 * @date 2021-03
 * @qq 1351150492
 */

@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysMenuMappingMapper sysMenuMappingMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public ReturnDataForLayui getList(SysUser sysUser) {
        PageHelper.startPage(sysUser.getPage(), sysUser.getLimit());
        List<SysUser> list = sysUserMapper.getList(sysUser);
        PageInfo<SysUser> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list, info.getTotal());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData add(SysUser sysUser) {
        if (!StringUtils.hasText(sysUser.getUsername())) {
            return setFailResult("登录名不能为空");
        }
        sysUser.setUsername(sysUser.getUsername().trim());
        if (sysUser.getUsername().length() < 2) {
            return setFailResult("登录名至少为2位");
        }
        if (!StringUtils.hasText(sysUser.getPassword())) {
            return setFailResult("密码不能为空");
        }
        sysUser.setPassword(sysUser.getPassword().trim());
        sysUser.setUserType(1);
        sysUser.setState(1);
        //判断用户是否存在
        long count = sysUserMapper.getCount(sysUser);
        if (count > 0) {
            return setFailResult("登录名已存在");
        }
        //生成密码
        String salt = SaltUtils.getRandomStrForSalt();
        sysUser.setPassword(MD5Util.MD5(sysUser.getPassword() + salt));
        sysUser.setSalt(salt);
        sysUserMapper.add(sysUser);
        SysUser sysUser_query = new SysUser();
        sysUser_query.setUsername(sysUser.getUsername());
        SysUser sysUserDb = sysUserMapper.getList(sysUser_query).get(0);
        //设置权限
        List<SysMenu> list = sysMenuMapper.getList(new SysMenu());
        list.forEach((sysMenu) -> {
            if (!"/homePage".equals(sysMenu.getUrl()) &&
                    !"/orders/statisticsPage".equals(sysMenu.getUrl()) &&
                    !"/sysUser/listPage".equals(sysMenu.getUrl())) {
                SysMenuMapping sysMenuMapping = new SysMenuMapping();
                sysMenuMapping.setSysUserId(sysUserDb.getId());
                sysMenuMapping.setMenuId(sysMenu.getId());
                sysMenuMappingMapper.add(sysMenuMapping);
            }
        });
        return setSuccessMsg("新增管理员成功");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData delete(SysUser sysUser) {
        int i = sysUserMapper.delete(sysUser);
        return UpdateOrInsertResultDeal.dealWith(i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData update(SysUser sysUser) {
        int i = sysUserMapper.update(sysUser);
        sysUser = new SysUser();
        sysUser.setId(sysUser.getId());
        sysUser = sysUserMapper.getList(sysUser).get(0);
        try {
            //延迟策略
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new CustomerException("修改信息失败");
        }
        //更新redis
        String token = (String) redisUtils.getValue("sysUser_" + sysUser.getId());
        if (StringUtils.hasText(token)) {
            //将用户信息存储在redis中,24小时有效期
            redisUtils.setValueExpire(token, sysUser, ConstantUtils.key_token_timeout);
            redisUtils.setValueExpire("sysUser_" + sysUser.getId(), token, ConstantUtils.key_token_timeout);
        }
        return setSuccessMsg("修改管理员成功");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ReturnData updateState(SysUser sysUser) {
        int i = sysUserMapper.updateState(sysUser);
        if (sysUser.getState() == 1) {
            return setSuccessMsg("启用成功");
        } else if (sysUser.getState() == 2) {
            try {
                //延迟策略
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new CustomerException("修改信息失败");
            }
            //直接删除token中的信息
            String token = (String) redisUtils.getValue("sysUser_" + sysUser.getId());
            if (StringUtils.hasText(token)) {
                redisUtils.deleteKey("sysUser_" + sysUser.getId(), token);
            }
            return setSuccessMsg("禁用成功");
        }
        return setSuccessMsg("修改成功");
    }

    @Override
    public ReturnData updatePwd(Long id, String password, String repassword) {
        if (id == null) {
            return setFailResult("id参数缺失");
        }
        if (TextUtils.isEmpty(password)) {
            return setFailResult("请输入新密码");
        }
        if (TextUtils.isEmpty(repassword)) {
            return setFailResult("请输入确认密码");
        }
        if (!repassword.equals(password)) {
            return setFailResult("两次密码输入不一致");
        }
        //更新密码
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        //生成密码
        String salt = SaltUtils.getRandomStrForSalt();
        sysUser.setPassword(MD5Util.MD5(password + salt));
        sysUser.setSalt(salt);
        int i = sysUserMapper.updatePwd(sysUser);
        if (i > 0) {
            return setSuccessMsg("修改密码成功");
        }
        return setFailResult("修改密码失败");
    }

    @Override
    public ReturnData login(SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response) {
        //数据库查询用户登录信息
        SysUser sysUser = new SysUser();
        sysUser.setUsername(loginSysUser.getUsername());
        List<SysUser> list = sysUserMapper.getList(sysUser);
        if (CheckList.listIsEmpty(list)) {
            return setFailResult("用户不存在");
        }
        SysUser sysUserDb = list.get(0);
        if (sysUserDb.getState() == 2) {
            return setFailResult("该账号已被禁用");
        }
        //校验密码
        String salt = sysUserDb.getSalt();
        String password = sysUserDb.getPassword();
        String a = MD5Util.MD5(loginSysUser.getPassword() + salt);
        if (!password.equals(a)) {
            return setFailResult("密码有误");
        }
        //登录成功，将用户令牌存储在redis中
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", sysUserDb.getId());
        tokenMap.put("name", sysUserDb.getName());
        tokenMap.put(TokenInfoConstant.TOKEN_TYPE_KEY, TokenInfoConstant.TOKEN_TYPE_ADMIN);
        tokenMap.put("exp", DateTimeUtils.getSomeDay(new Date(), 1));
        String token = JwtUtil.creatToken(tokenMap);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("userName", sysUserDb.getUsername());
        resultMap.put("headImg", sysUserDb.getHeadImg());
        //将用户信息存储在redis中,24小时有效期
        redisUtils.setValueExpire(token, sysUserDb, ConstantUtils.key_token_timeout);
        redisUtils.setValueExpire("sysUser_" + sysUserDb.getId(), token, ConstantUtils.key_token_timeout);
        //设置cookie
        CookiesUtil.setCookie(response, "token", token, ConstantUtils.key_token_timeout);
        return setSuccessResult(resultMap);
    }
}

