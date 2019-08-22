package com.baomidou.springwind.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.CustomerPayRecord;

/**
 *
 * Customer 表数据服务层接口
 *
 */
public interface ICustomService extends IService<Customer> {

	boolean updateAndSaveReord(CustomerPayRecord customerPayRecord);

	@DataSource("oaDataSource")
	public void batchUpdateCusomer(List<Customer> customerList);
	@DataSource("oaDataSource")
	void updateByUserId(Integer restSmsNum, String cid);
	@DataSource("oaDataSource")
	void updateTodayLoginCount();
	@DataSource("oaDataSource")
	Integer batchChangeTheOwnerOperate(String outId, String enterId);

}