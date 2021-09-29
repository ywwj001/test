package com.redrain.mapper;

import com.redrain.entity.DietitianOrder;

import java.util.List;

/**
 * @author redrain
 * @Description DietitianOrderMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface DietitianOrderMapper {

	long getCount(DietitianOrder dietitianOrder);

	List<DietitianOrder> getList(DietitianOrder dietitianOrder);

	int add(DietitianOrder dietitianOrder);

	int delete(DietitianOrder dietitianOrder);

	int update(DietitianOrder dietitianOrder);

	int updateState(DietitianOrder dietitianOrder);

}

