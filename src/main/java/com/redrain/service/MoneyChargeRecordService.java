package com.redrain.service;

import com.redrain.entity.MoneyChargeRecord;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description MoneyChargeRecordservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface MoneyChargeRecordService {

	ReturnDataForLayui getList(MoneyChargeRecord moneyChargeRecord);

	ReturnData add(MoneyChargeRecord moneyChargeRecord);

	ReturnData delete(MoneyChargeRecord moneyChargeRecord);

	ReturnData update(MoneyChargeRecord moneyChargeRecord);

	ReturnData updateState(MoneyChargeRecord moneyChargeRecord);

}

