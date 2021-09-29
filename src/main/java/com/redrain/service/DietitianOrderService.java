package com.redrain.service;

import com.redrain.entity.DietitianOrder;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description DietitianOrderservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietitianOrderService {

	ReturnDataForLayui getList(DietitianOrder dietitianOrder);

	ReturnData add(DietitianOrder dietitianOrder);

	ReturnData delete(DietitianOrder dietitianOrder);

	ReturnData update(DietitianOrder dietitianOrder);

	ReturnData updateState(DietitianOrder dietitianOrder);

}

