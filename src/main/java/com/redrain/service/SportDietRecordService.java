package com.redrain.service;

import com.redrain.entity.SportDietRecord;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description SportDietRecordservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface SportDietRecordService {

	ReturnDataForLayui getList(SportDietRecord sportDietRecord);

	ReturnData add(SportDietRecord sportDietRecord);

	ReturnData delete(SportDietRecord sportDietRecord);

	ReturnData update(SportDietRecord sportDietRecord);

	ReturnData updateState(SportDietRecord sportDietRecord);

}

