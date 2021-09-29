package com.redrain.mapper;

import com.redrain.entity.Sport;

import java.util.List;

/**
 * @author redrain
 * @Description SportMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface SportMapper {

	long getCount(Sport sport);

	List<Sport> getList(Sport sport);

	int add(Sport sport);

	int delete(Sport sport);

	int update(Sport sport);

	int updateState(Sport sport);

}

