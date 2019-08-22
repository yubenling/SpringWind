package com.baomidou.springwind.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.UserMonthSurplusSms;
import com.baomidou.springwind.mapper.UserMonthSurplusSmsMapper;
import com.baomidou.springwind.service.IUserMonthSurplusSmsService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * Classify 表数据服务层接口实现类
 *
 */
@Service
public class UserMonthSurplusSmsServiceImpl extends BaseServiceImpl<UserMonthSurplusSmsMapper, UserMonthSurplusSms> implements IUserMonthSurplusSmsService {

	@Resource
	private UserMonthSurplusSmsMapper userMonthSurplusSmsMapper;
	
	@Override
	public Integer findMonthStartNum(Map<String, Object> numMap) {
		try {
			return userMonthSurplusSmsMapper.findMonthStartNum(numMap);
		} catch (Exception e) {
			return null;
		}
	}
}