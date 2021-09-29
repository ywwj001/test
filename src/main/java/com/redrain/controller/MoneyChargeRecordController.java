package com.redrain.controller;

import com.redrain.entity.MoneyChargeRecord;
import com.redrain.common.result.ReturnData;
import com.redrain.entity.ReturnDataForLayui;
import com.redrain.service.MoneyChargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author redrain
 * @Description MoneyChargeRecordcontroller实现类
 * @date 2021-04
 * @qq 1351150492
 */

@Controller
@RequestMapping("/moneyChargeRecord")
public class MoneyChargeRecordController {

    @Autowired
    private MoneyChargeRecordService moneyChargeRecordService;

	@RequestMapping("listPage")
    public String listPage() {
        return "moneyChargeRecord/list";
    }

	@RequestMapping("addPage")
    public String addPage() {
        return "moneyChargeRecord/add";
    }

    @RequestMapping("editPage")
    public String editPage(HttpServletRequest request, Long id) {
        request.setAttribute("id", id);
        return "moneyChargeRecord/edit";
    }

	@RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(MoneyChargeRecord moneyChargeRecord) {
        return moneyChargeRecordService.getList(moneyChargeRecord);
    }

	@RequestMapping("/add")
    @ResponseBody
    public ReturnData add(MoneyChargeRecord moneyChargeRecord, Long userId) {
        return moneyChargeRecordService.add(moneyChargeRecord);
    }

	@RequestMapping("/delete")
    @ResponseBody
    public ReturnData delete(MoneyChargeRecord moneyChargeRecord) {
        return moneyChargeRecordService.delete(moneyChargeRecord);
    }

	@RequestMapping("/update")
    @ResponseBody
    public ReturnData update(MoneyChargeRecord moneyChargeRecord) {
        return moneyChargeRecordService.update(moneyChargeRecord);
    }

	@RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(MoneyChargeRecord moneyChargeRecord) {
        return moneyChargeRecordService.updateState(moneyChargeRecord);
    }

}

