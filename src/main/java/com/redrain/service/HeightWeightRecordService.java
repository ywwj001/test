package com.redrain.service;

import com.redrain.entity.HeightWeightRecord;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description HeightWeightRecordservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface HeightWeightRecordService {

	ReturnDataForLayui getList(HeightWeightRecord heightWeightRecord);

	ReturnData add(HeightWeightRecord heightWeightRecord);

	ReturnData delete(HeightWeightRecord heightWeightRecord);

	ReturnData update(HeightWeightRecord heightWeightRecord);

	ReturnData updateState(HeightWeightRecord heightWeightRecord);

}

