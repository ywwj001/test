package com.redrain.service;

import com.redrain.entity.Sport;

import com.redrain.common.result.ReturnData;

import com.redrain.entity.ReturnDataForLayui;

import java.util.List;

/**
 * @author redrain
 * @Description Sportservice接口
 * @date 2021-04
 * @qq 1351150492
 */

public interface SportService {

	ReturnDataForLayui getList(Sport sport);

	ReturnData add(Sport sport);

	ReturnData delete(Sport sport);

	ReturnData update(Sport sport);

	ReturnData updateState(Sport sport);

}

