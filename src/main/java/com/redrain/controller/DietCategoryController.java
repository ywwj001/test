package com.redrain.controller;

import com.redrain.entity.DietCategory;
import com.redrain.service.DietCategoryService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description DietCategorycontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/dietCategory")
public class DietCategoryController {

    @Autowired
    private DietCategoryService dietCategoryService;

	@RequestMapping("listPage")
    public String listPage() {
        return "dietCategory/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "dietCategory/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "dietCategory/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(DietCategory dietCategory) {
        return dietCategoryService.getList(dietCategory);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(DietCategory dietCategory, Long userId) {
        return dietCategoryService.add(dietCategory);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(DietCategory dietCategory) {
        return dietCategoryService.delete(dietCategory);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(DietCategory dietCategory) {
        return dietCategoryService.update(dietCategory);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(DietCategory dietCategory) {
        return dietCategoryService.updateState(dietCategory);
    }

}

