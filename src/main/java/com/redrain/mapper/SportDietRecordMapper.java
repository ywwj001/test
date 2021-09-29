package com.redrain.mapper;

import com.redrain.entity.SportDietRecord;

import java.util.List;

/**
 * @author redrain
 * @Description SportDietRecordMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface SportDietRecordMapper {

	long getCount(SportDietRecord sportDietRecord);

	List<SportDietRecord> getList(SportDietRecord sportDietRecord);

	int add(SportDietRecord sportDietRecord);

	int delete(SportDietRecord sportDietRecord);

	int update(SportDietRecord sportDietRecord);

	int updateState(SportDietRecord sportDietRecord);

}

