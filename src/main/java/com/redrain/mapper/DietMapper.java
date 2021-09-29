package com.redrain.mapper;

import com.redrain.entity.Diet;

import java.util.List;

/**
 * @author redrain
 * @Description DietMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietMapper {

	long getCount(Diet diet);

	List<Diet> getList(Diet diet);

	int add(Diet diet);

	int delete(Diet diet);

	int update(Diet diet);

	int updateState(Diet diet);

}

