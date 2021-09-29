package com.redrain.controller;

import com.redrain.entity.SportDietRecord;
import com.redrain.entity.User;
import com.redrain.service.SportDietRecordService;
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
 * @Description SportDietRecordcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/sportDietRecord")
public class SportDietRecordController {


    @Resource
    private UserUtils userUtils;

    @Autowired
    private SportDietRecordService sportDietRecordService;

    @RequestMapping("listPage")
    public String listPage() {
        return "sportDietRecord/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "sportDietRecord/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "sportDietRecord/edit";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(SportDietRecord sportDietRecord) {
        return sportDietRecordService.getList(sportDietRecord);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(SportDietRecord sportDietRecord, HttpServletRequest request) {
        User user = new User();
        ReturnData returnData = userUtils.setUserId(request, user);
        if (!returnData.isSuccess()) {
            return returnData;
        }
        sportDietRecord.setUid(user.getId());
        return sportDietRecordService.add(sportDietRecord);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(SportDietRecord sportDietRecord) {
        return sportDietRecordService.delete(sportDietRecord);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(SportDietRecord sportDietRecord) {
        return sportDietRecordService.update(sportDietRecord);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(SportDietRecord sportDietRecord) {
        return sportDietRecordService.updateState(sportDietRecord);
    }

}

