package com.redrain.controller;

import com.redrain.entity.SysMenuMapping;
import com.redrain.service.SysMenuMappingService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description SysMenuMappingcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/sysMenuMapping")
public class SysMenuMappingController {

    @Autowired
    private SysMenuMappingService sysMenuMappingService;

	@RequestMapping("listPage")
    public String listPage() {
        return "sysMenuMapping/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "sysMenuMapping/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sysMenuMapping/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(SysMenuMapping sysMenuMapping) {
        return sysMenuMappingService.getList(sysMenuMapping);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(SysMenuMapping sysMenuMapping, Long userId) {
        return sysMenuMappingService.add(sysMenuMapping);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(SysMenuMapping sysMenuMapping) {
        return sysMenuMappingService.delete(sysMenuMapping);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(SysMenuMapping sysMenuMapping) {
        return sysMenuMappingService.update(sysMenuMapping);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(SysMenuMapping sysMenuMapping) {
        return sysMenuMappingService.updateState(sysMenuMapping);
    }

}

