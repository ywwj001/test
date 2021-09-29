package com.redrain.controller;

import com.redrain.entity.SysMenu;
import com.redrain.service.SysMenuService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description SysMenucontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

	@RequestMapping("listPage")
    public String listPage() {
        return "sysMenu/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "sysMenu/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sysMenu/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(SysMenu sysMenu) {
        return sysMenuService.getList(sysMenu);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(SysMenu sysMenu, Long userId) {
        return sysMenuService.add(sysMenu);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(SysMenu sysMenu) {
        return sysMenuService.delete(sysMenu);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(SysMenu sysMenu) {
        return sysMenuService.update(sysMenu);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(SysMenu sysMenu) {
        return sysMenuService.updateState(sysMenu);
    }

}

