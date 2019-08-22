package com.baomidou.springwind.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.UserInfo;

/**
 * 
 * User 表数据服务层接口
 * 
 */

public interface IUserInfoService extends IService<UserInfo> {

	public List<UserInfo>  findUserInfoList();
	
	public Integer getSMSCount(String userId);
	
	public UserInfo getUserByUserId(String userId);
	
	public void updateUserInfo(Integer smsNum, String userId) throws Exception;

	public void updateSmsToTransfer(String outUserName,
			String rechargeUserName, int rechargeNum) throws Exception;

	public List<UserInfo> findUserInfoSmsNumList(
			List<String> list);

	public List<UserInfo> findUserAccountList();

	public Integer findUserInfoSmsNum(String userId);

	public UserInfo findUserInfoByCustomer(String userId);
}