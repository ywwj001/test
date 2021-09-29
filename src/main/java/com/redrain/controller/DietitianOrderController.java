package com.redrain.controller;

import com.redrain.entity.DietitianOrder;
import com.redrain.entity.User;
import com.redrain.service.DietitianOrderService;
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
 * @Description DietitianOrdercontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/dietitianOrder")
public class DietitianOrderController {

    @Resource
    private UserUtils userUtils;

    @Autowired
    private DietitianOrderService dietitianOrderService;

    @RequestMapping("listPage")
    public String listPage() {
        return "dietitianOrder/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "dietitianOrder/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "dietitianOrder/edit";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(DietitianOrder dietitianOrder) {
        return dietitianOrderService.getList(dietitianOrder);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(DietitianOrder dietitianOrder, HttpServletRequest request) {
        User user = new User();
        ReturnData returnData = userUtils.setUserId(request, user);
        if (!returnData.isSuccess()) {
            return returnData;
        }
        dietitianOrder.setUid(user.getId());
        return dietitianOrderService.add(dietitianOrder);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(DietitianOrder dietitianOrder) {
        return dietitianOrderService.delete(dietitianOrder);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(DietitianOrder dietitianOrder) {
        return dietitianOrderService.update(dietitianOrder);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(DietitianOrder dietitianOrder) {
        return dietitianOrderService.updateState(dietitianOrder);
    }

}

