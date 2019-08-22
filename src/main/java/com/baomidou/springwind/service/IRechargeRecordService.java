package com.baomidou.springwind.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.RechargeRecord;

/**
 *
 * RechargeRecord 表数据服务层接口
 *
 */
public interface IRechargeRecordService extends IService<RechargeRecord> {
	
	Integer findUserPresentNum(Map<String, Object> map);
	
	Integer findUserDeductNum(Map<String, Object> map);
	
	Integer findUserShiftToNum(Map<String, Object> map);
	
}