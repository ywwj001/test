package com.redrain.controller;

import com.redrain.common.utils.JSONUtils;
import com.redrain.common.utils.TextUtils;
import com.redrain.common.utils.redis.RedisUtils;
import com.redrain.entity.User;
import com.redrain.service.UserService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.service.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description Usercontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserUtils userUtils;

    @Resource
    private UserService userService;

    @RequestMapping("listPage")
    public String listPage() {
        return "user/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "user/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "user/edit";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(User user, boolean onlyMe, HttpServletRequest request) {
        if (onlyMe) {
            ReturnData returnData = userUtils.setUserId(request, user);
            if (!returnData.isSuccess()) {
                return ReturnDataForLayui.fail(returnData.getMsg());
            }
        }
        return userService.getList(user);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(User user, Long userId) {
        return userService.add(user);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(User user) {
        return userService.delete(user);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(User user) {
        return userService.update(user);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(User user) {
        return userService.updateState(user);
    }

}

