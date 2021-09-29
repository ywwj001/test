package com.redrain.mapper;

import com.redrain.entity.MoneyChargeRecord;

import java.util.List;

/**
 * @author redrain
 * @Description MoneyChargeRecordMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface MoneyChargeRecordMapper {

	long getCount(MoneyChargeRecord moneyChargeRecord);

	List<MoneyChargeRecord> getList(MoneyChargeRecord moneyChargeRecord);

	int add(MoneyChargeRecord moneyChargeRecord);

	int delete(MoneyChargeRecord moneyChargeRecord);

	int update(MoneyChargeRecord moneyChargeRecord);

	int updateState(MoneyChargeRecord moneyChargeRecord);

}

