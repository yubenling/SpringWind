package com.baomidou.springwind.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.CustomerPayRecord;
import com.baomidou.springwind.mapper.CustomMapper;
import com.baomidou.springwind.service.ICustomPayRecordService;
import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import com.baomidou.springwind.util.DateUtils;

/**
 *
 * Customer 表数据服务层接口实现类
 *
 */
@Service
public class CustomServiceImpl extends BaseServiceImpl<CustomMapper, Customer> implements ICustomService {

	@Autowired
	private ICustomPayRecordService customPayRecordService;
	@Autowired
	private ICustomService customService;
	@Autowired
	private CustomMapper customMapper;
	@Override
	public boolean updateAndSaveReord(CustomerPayRecord customerPayRecord) {
		boolean rlt =false;
		 try {
			Customer customer = customService.selectById(customerPayRecord.getCustomerId());
			  if(null!=customer){
				  customer.setIsOperate(1);
				  if(null!=customerPayRecord.getPayTimeStr()&&!"".equals(customerPayRecord.getPayTimeStr())){
					  customerPayRecord.setPayTime(DateUtils.StringToDate(customerPayRecord.getPayTimeStr(),"yyyy-MM-dd"));
				  }
				if(customerPayRecord.getBuyType()==1){
					customer.setIsBuy(1);	
					//修改
					customer.setExpireTime(DateUtils.addMonth(new Date(), 3));
					customService.updateById(customer);
					customerPayRecord.setCreateTime(new Date());
					customPayRecordService.insert(customerPayRecord);
				}else if(2==customerPayRecord.getBuyType()&&customer.getIsBuy()==1){
					customer.setIsBuy(2);	
					customer.setExpireTime(DateUtils.addMonth(new Date(), 12));
					customService.updateById(customer);
					customerPayRecord.setCreateTime(new Date());
					customPayRecordService.insert(customerPayRecord);
				}else if(2==customerPayRecord.getBuyType()&&customer.getIsBuy()==0){
					 customer.setIsBuy(2);	
					 customService.updateById(customer);
					 customerPayRecord.setCreateTime(new Date());
					 customPayRecordService.insert(customerPayRecord);
				}else if(3==customerPayRecord.getBuyType()){
					 customer.setIsBuy(3);	
					 customService.updateById(customer);
					 customerPayRecord.setCreateTime(new Date());
					 customPayRecordService.insert(customerPayRecord);
				}
				rlt=true;
			  } 
		} catch (Exception e) {
			rlt=false;
			throw new RuntimeException();
		}
		return rlt;
	}
	@Override
	public void batchUpdateCusomer(List<Customer> customerList) {
		customMapper.batchUpdateCusomer(customerList);
	}
	@Override
	public void updateByUserId(Integer restSmsNum, String customerName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restSmsNum", restSmsNum);
		map.put("customerName", customerName);
		customMapper.updateByUserId(map);
	}
	@Override
	public void updateTodayLoginCount() {
		customMapper.updateTodayLoginCount();
	}
	@Override
	public Integer batchChangeTheOwnerOperate(String outId, String enterId) {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("outId", outId);
		map.put("enterId", enterId);
		return customMapper.batchChangeTheOwnerOperate(map);
	}
}