package com.redrain.service;

import com.redrain.entity.DietCategory;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description DietCategoryservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietCategoryService {

	ReturnDataForLayui getList(DietCategory dietCategory);

	ReturnData add(DietCategory dietCategory);

	ReturnData delete(DietCategory dietCategory);

	ReturnData update(DietCategory dietCategory);

	ReturnData updateState(DietCategory dietCategory);

}

