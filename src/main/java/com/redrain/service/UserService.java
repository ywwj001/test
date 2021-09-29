package com.redrain.service;

import com.redrain.entity.SysUser;
import com.redrain.entity.User;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author redrain
 * @Description Userservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface UserService {

	ReturnDataForLayui getList(User user);

	ReturnData add(User user);

	ReturnData delete(User user);

	ReturnData update(User user);

	ReturnData updateState(User user);

    ReturnData login(SysUser loginSysUser, HttpServletRequest request, HttpServletResponse response);
}

