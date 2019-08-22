package com.baomidou.springwind.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.UserMonthSurplusSms;

/**
 *
 * RechargeRecord 表数据库控制层接口
 *
 */
public interface UserMonthSurplusSmsMapper extends BaseMapper<UserMonthSurplusSms> {

	Integer findMonthStartNum(Map<String, Object> numMap);

}