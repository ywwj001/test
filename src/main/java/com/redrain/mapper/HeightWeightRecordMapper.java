package com.redrain.mapper;

import com.redrain.entity.HeightWeightRecord;

import java.util.List;

/**
 * @author redrain
 * @Description HeightWeightRecordMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface HeightWeightRecordMapper {

	long getCount(HeightWeightRecord heightWeightRecord);

	List<HeightWeightRecord> getList(HeightWeightRecord heightWeightRecord);

	int add(HeightWeightRecord heightWeightRecord);

	int delete(HeightWeightRecord heightWeightRecord);

	int update(HeightWeightRecord heightWeightRecord);

	int updateState(HeightWeightRecord heightWeightRecord);

}

