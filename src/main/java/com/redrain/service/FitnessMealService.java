package com.redrain.service;

import com.redrain.entity.FitnessMeal;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description FitnessMealservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface FitnessMealService {

	ReturnDataForLayui getList(FitnessMeal fitnessMeal);

	ReturnData add(FitnessMeal fitnessMeal);

	ReturnData delete(FitnessMeal fitnessMeal);

	ReturnData update(FitnessMeal fitnessMeal);

	ReturnData updateState(FitnessMeal fitnessMeal);

}

