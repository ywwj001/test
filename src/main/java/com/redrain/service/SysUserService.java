package com.redrain.service;

import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.entity.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author redrain
 * @Description SysUserservice接口
 * @date 2021-03
 * @qq 1351150492
 */

public interface SysUserService {

    ReturnDataForLayui getList(SysUser sysUser);

    ReturnData add(SysUser sysUser);

    ReturnData delete(SysUser sysUser);

    ReturnData update(SysUser sysUser);

    ReturnData updateState(SysUser sysUser);

    ReturnData login(SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response);

    ReturnData updatePwd(Long id, String password, String repassword);
}

