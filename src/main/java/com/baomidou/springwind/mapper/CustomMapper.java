package com.baomidou.springwind.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.Customer;

/**
 *
 * Customer 表数据库控制层接口
 *
 */
public interface CustomMapper extends BaseMapper<Customer> {


	public void batchUpdateCusomer(List<Customer> customerList);

	public void updateByUserId(Integer smsNum, String userId);

	public void updateByUserId(Map<String, Object> map);

	public void updateTodayLoginCount();

	public Integer batchChangeTheOwnerOperate(Map<String, Object> map);
}