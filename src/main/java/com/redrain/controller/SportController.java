package com.redrain.controller;

import com.redrain.entity.Sport;
import com.redrain.service.SportService;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description Sportcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private SportService sportService;

	@RequestMapping("listPage")
    public String listPage() {
        return "sport/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "sport/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sport/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Sport sport) {
        return sportService.getList(sport);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Sport sport, Long userId) {
        return sportService.add(sport);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(Sport sport) {
        return sportService.delete(sport);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Sport sport) {
        return sportService.update(sport);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Sport sport) {
        return sportService.updateState(sport);
    }

}

