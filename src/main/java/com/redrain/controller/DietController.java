package com.redrain.controller;

import com.redrain.entity.Diet;
import com.redrain.service.DietService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description Dietcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/diet")
public class DietController {

    @Autowired
    private DietService dietService;

	@RequestMapping("listPage")
    public String listPage() {
        return "diet/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "diet/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "diet/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Diet diet) {
        return dietService.getList(diet);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Diet diet, Long userId) {
        return dietService.add(diet);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(Diet diet) {
        return dietService.delete(diet);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Diet diet) {
        return dietService.update(diet);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Diet diet) {
        return dietService.updateState(diet);
    }

}

