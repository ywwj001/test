package com.redrain.controller;

import com.redrain.entity.HeightWeightRecord;
import com.redrain.entity.User;
import com.redrain.service.HeightWeightRecordService;
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
 * @Description HeightWeightRecordcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/heightWeightRecord")
public class HeightWeightRecordController {


    @Resource
    private UserUtils userUtils;

    @Autowired
    private HeightWeightRecordService heightWeightRecordService;

    @RequestMapping("listPage")
    public String listPage() {
        return "heightWeightRecord/list";
    }

    @RequestMapping("addPage")
    public String addPage() {
        return "heightWeightRecord/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "heightWeightRecord/edit";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(HeightWeightRecord heightWeightRecord) {

        return heightWeightRecordService.getList(heightWeightRecord);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(HeightWeightRecord heightWeightRecord, HttpServletRequest request) {
        User user = new User();
        ReturnData returnData = userUtils.setUserId(request, user);
        if (!returnData.isSuccess()) {
            return returnData;
        }
        heightWeightRecord.setUid(user.getId());
        return heightWeightRecordService.add(heightWeightRecord);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(HeightWeightRecord heightWeightRecord) {
        return heightWeightRecordService.delete(heightWeightRecord);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(HeightWeightRecord heightWeightRecord) {
        return heightWeightRecordService.update(heightWeightRecord);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(HeightWeightRecord heightWeightRecord) {
        return heightWeightRecordService.updateState(heightWeightRecord);
    }

}

