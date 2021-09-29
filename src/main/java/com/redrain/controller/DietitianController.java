package com.redrain.controller;

import com.redrain.entity.Dietitian;
import com.redrain.service.DietitianService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description Dietitiancontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/dietitian")
public class DietitianController {

    @Autowired
    private DietitianService dietitianService;

	@RequestMapping("listPage")
    public String listPage() {
        return "dietitian/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "dietitian/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "dietitian/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Dietitian dietitian) {
        return dietitianService.getList(dietitian);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Dietitian dietitian, Long userId) {
        return dietitianService.add(dietitian);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(Dietitian dietitian) {
        return dietitianService.delete(dietitian);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Dietitian dietitian) {
        return dietitianService.update(dietitian);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Dietitian dietitian) {
        return dietitianService.updateState(dietitian);
    }

}

