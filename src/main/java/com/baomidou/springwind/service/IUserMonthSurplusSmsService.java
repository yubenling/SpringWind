package com.baomidou.springwind.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.UserMonthSurplusSms;

/**
 *
 * UserRecharge 表数据服务层接口
 *
 */
public interface IUserMonthSurplusSmsService extends IService<UserMonthSurplusSms> {

	Integer findMonthStartNum(Map<String, Object> numMap);

}