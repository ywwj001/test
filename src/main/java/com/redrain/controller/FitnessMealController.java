package com.redrain.controller;

import com.redrain.entity.FitnessMeal;
import com.redrain.service.FitnessMealService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description FitnessMealcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/fitnessMeal")
public class FitnessMealController {

    @Autowired
    private FitnessMealService fitnessMealService;

	@RequestMapping("listPage")
    public String listPage() {
        return "fitnessMeal/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "fitnessMeal/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "fitnessMeal/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(FitnessMeal fitnessMeal) {
        return fitnessMealService.getList(fitnessMeal);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(FitnessMeal fitnessMeal, Long userId) {
        return fitnessMealService.add(fitnessMeal);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(FitnessMeal fitnessMeal) {
        return fitnessMealService.delete(fitnessMeal);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(FitnessMeal fitnessMeal) {
        return fitnessMealService.update(fitnessMeal);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(FitnessMeal fitnessMeal) {
        return fitnessMealService.updateState(fitnessMeal);
    }

}

