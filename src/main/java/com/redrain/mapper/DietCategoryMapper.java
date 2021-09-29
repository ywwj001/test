package com.redrain.mapper;

import com.redrain.entity.DietCategory;

import java.util.List;

/**
 * @author redrain
 * @Description DietCategoryMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietCategoryMapper {

	long getCount(DietCategory dietCategory);

	List<DietCategory> getList(DietCategory dietCategory);

	int add(DietCategory dietCategory);

	int delete(DietCategory dietCategory);

	int update(DietCategory dietCategory);

	int updateState(DietCategory dietCategory);

}

