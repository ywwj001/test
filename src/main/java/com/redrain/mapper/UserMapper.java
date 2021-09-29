package com.redrain.mapper;

import com.redrain.entity.Dietitian;
import com.redrain.entity.DietitianOrder;
import com.redrain.entity.MoneyChargeRecord;
import com.redrain.entity.User;

import java.util.List;

/**
 * @author redrain
 * @Description UserMapper
 * @date 2021-04
 * @qq 1351150492
 */

public interface UserMapper {

	long getCount(User user);

	List<User> getList(User user);

	int add(User user);

	int delete(User user);

	int update(User user);

	int updateState(User user);

    int updateBalance(MoneyChargeRecord moneyChargeRecord);

	int minusBalance(DietitianOrder dietitianOrder);
}

