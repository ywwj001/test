package com.redrain.service;

import com.redrain.entity.Diet;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description Dietservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietService {

	ReturnDataForLayui getList(Diet diet);

	ReturnData add(Diet diet);

	ReturnData delete(Diet diet);

	ReturnData update(Diet diet);

	ReturnData updateState(Diet diet);

}

