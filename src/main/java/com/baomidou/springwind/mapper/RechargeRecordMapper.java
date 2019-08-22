package com.baomidou.springwind.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.RechargeRecord;

/**
 *
 * RechargeRecord 表数据库控制层接口
 *
 */
public interface RechargeRecordMapper extends BaseMapper<RechargeRecord> {

	Integer findUserPresentNum(Map<String, Object> map);
	
	Integer findUserDeductNum(Map<String, Object> map);
	
	Integer findUserShiftToNum(Map<String, Object> map);
}