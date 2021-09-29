package com.redrain.controller;

import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.entity.SysUser;
import com.redrain.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description SysUsercontroller实现类
 * @date 2021-03
 * @qq 1351150492
 */

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("listPage")
    public String listPage() {
        return "sysUser/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "sysUser/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sysUser/edit";
    }


    @RequestMapping("updatePwdPage")
    public String updatePwdPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sysUser/updatePwd";
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public ReturnData updatePwd(Long id, String password, String repassword) {
        return sysUserService.updatePwd(id, password, repassword);
    }


    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(SysUser sysUser) {
        return sysUserService.getList(sysUser);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(SysUser sysUser) {
        return sysUserService.add(sysUser);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(SysUser sysUser) {
        return sysUserService.delete(sysUser);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(SysUser sysUser) {
        return sysUserService.update(sysUser);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(SysUser sysUser) {
        return sysUserService.updateState(sysUser);
    }

}

