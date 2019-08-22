package com.baomidou.springwind.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.UserRecharge;
import com.baomidou.springwind.mapper.UserRechargeMapper;
import com.baomidou.springwind.service.IUserRechargeService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * Classify 表数据服务层接口实现类
 *
 */
@Service
public class UserRechargeServiceImpl extends BaseServiceImpl<UserRechargeMapper, UserRecharge> implements IUserRechargeService {
	
	@Autowired
	private UserRechargeMapper userRechargeMapper;
	
	@Override
	@DataSource(value="crmDataSource")
	public Page<UserRecharge> findUserRecharge(Map<String, Object> map){
		Page<UserRecharge> page = new Page<UserRecharge>();
		List<UserRecharge> sumUserRecharge = userRechargeMapper.findMonthlyUserRecharge(map);
		Integer total = userRechargeMapper.findMonthlyUserRechargeCount(map);
		page.setRecords(sumUserRecharge);
		page.setTotal(total);
		return page;
	}

	@Override
	@DataSource(value="crmDataSource")
	public List<String> findUserRechargeName() {
		return userRechargeMapper.findUserRechargeName();
	}

	@Override
	@DataSource(value="crmDataSource")
	public Page<UserRecharge> findUserRechargeList(Map<String, Object> map) {
		Page<UserRecharge> page = new Page<UserRecharge>();
		List<UserRecharge> list = userRechargeMapper.findUserRechargeList(map);
		Integer total = userRechargeMapper.findUserRechargeListCount(map);
		page.setRecords(list);
		page.setTotal(total);
		return page;
	}

	@Override
	@DataSource(value="crmDataSource")
	public void executeDeleteSql() {
		userRechargeMapper.executeDeleteSql();
	}
}