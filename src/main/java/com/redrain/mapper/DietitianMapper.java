package com.redrain.mapper;

import com.redrain.entity.Dietitian;

import java.util.List;

/**
 * @author redrain
 * @Description DietitianMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietitianMapper {

	long getCount(Dietitian dietitian);

	List<Dietitian> getList(Dietitian dietitian);

	int add(Dietitian dietitian);

	int delete(Dietitian dietitian);

	int update(Dietitian dietitian);

	int updateState(Dietitian dietitian);

}

