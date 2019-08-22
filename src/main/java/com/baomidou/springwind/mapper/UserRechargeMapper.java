package com.baomidou.springwind.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.UserRecharge;

/**
 *
 * UserRecharge 表数据库控制层接口
 *
 */
public interface UserRechargeMapper extends BaseMapper<UserRecharge> {
	
	
	List<UserRecharge> findMonthlyUserRecharge(Map<String, Object> map);
	
	Integer findMonthlyUserRechargeCount(Map<String, Object> map);

	List<String> findUserRechargeName();

	List<UserRecharge> findUserRechargeList(Map<String, Object> map);
	
	Integer findUserRechargeListCount(Map<String, Object> map);

	void executeDeleteSql();
}