package com.redrain.mapper;

import com.redrain.entity.FitnessMeal;

import java.util.List;

/**
 * @author redrain
 * @Description FitnessMealMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface FitnessMealMapper {

	long getCount(FitnessMeal fitnessMeal);

	List<FitnessMeal> getList(FitnessMeal fitnessMeal);

	int add(FitnessMeal fitnessMeal);

	int delete(FitnessMeal fitnessMeal);

	int update(FitnessMeal fitnessMeal);

	int updateState(FitnessMeal fitnessMeal);

}

