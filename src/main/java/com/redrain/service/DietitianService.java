package com.redrain.service;

import com.redrain.entity.Dietitian;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description Dietitianservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietitianService {

	ReturnDataForLayui getList(Dietitian dietitian);

	ReturnData add(Dietitian dietitian);

	ReturnData delete(Dietitian dietitian);

	ReturnData update(Dietitian dietitian);

	ReturnData updateState(Dietitian dietitian);

}

