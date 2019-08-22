package com.baomidou.springwind.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.RechargeRecord;
import com.baomidou.springwind.mapper.RechargeRecordMapper;
import com.baomidou.springwind.service.IRechargeRecordService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * RechargeRecord 表数据服务层接口实现类
 *
 */
@Service
public class RechargeRecordServiceImpl extends BaseServiceImpl<RechargeRecordMapper, RechargeRecord> implements IRechargeRecordService {
	
	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;
	
	@Override
	@DataSource("oaDataSource")
	public Integer findUserShiftToNum(Map<String, Object> numMap) {
		return rechargeRecordMapper.findUserShiftToNum(numMap);
	}

	@Override
	@DataSource("oaDataSource")
	public Integer findUserPresentNum(Map<String, Object> numMap) {
		return rechargeRecordMapper.findUserPresentNum(numMap);
	}

	@Override
	@DataSource("oaDataSource")
	public Integer findUserDeductNum(Map<String, Object> numMap) {
		return rechargeRecordMapper.findUserDeductNum(numMap);
	}


}