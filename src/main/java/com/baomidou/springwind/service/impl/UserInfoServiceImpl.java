package com.baomidou.springwind.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.UserInfo;
import com.baomidou.springwind.mapper.UserInfoMapper;
import com.baomidou.springwind.service.IUserInfoService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * UserInfo 表数据服务层接口实现类
 *
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService{

	@Autowired
	private UserInfoMapper   userInfoMapper;
	
	@Override
	@DataSource(value="crmDataSource")
	public List<UserInfo> findUserInfoList() {
		 return userInfoMapper.findUserInfoList();
	}
	

	@Override
	@DataSource(value="crmDataSource")
	public List<UserInfo> findUserAccountList() {
		 return userInfoMapper.findUserAccountList();
	}


	@Override
	@DataSource(value="crmDataSource")
	public Integer getSMSCount(String userId) {
		//a.2用户当前的剩余短信
		Integer smsNumNow = userInfoMapper.getSmsNumByUserId(userId);
		return smsNumNow;
	}

	@Override
	@DataSource(value="crmDataSource")
	public UserInfo getUserByUserId(String userId) {
		UserInfo userInfo = userInfoMapper.getUserByUserId(userId);
		return userInfo;
	}

	@Override
	@DataSource(value="crmDataSource")
	@Transactional
	public void updateUserInfo(Integer smsNum, String userId) throws Exception {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("smsNum", smsNum);
		 map.put("taobaoUserNick", userId);
		 Integer i = 0;
	     if(smsNum>0){
	    	 i = userInfoMapper.updateUserInfoRecharge(map);
	     }
	     if(smsNum<0){
	    	 map.put("smsNum", Math.abs(smsNum));
	    	 i = userInfoMapper.updateUserInfoDeduct(map);
	     }
		 if(i!=1){
			 throw new Exception("补短信----执行充值短信时操作的用户数："+i+"个");
		 }
	}

	@Override
	@DataSource(value="crmDataSource")
	@Transactional
	public void updateSmsToTransfer(String outUserName,
			String rechargeUserName, int rechargeNum) throws Exception {
				Map<String, Object> deductMap = new HashMap<String, Object>();
				deductMap.put("smsNum", rechargeNum);
				deductMap.put("taobaoUserNick", outUserName);
				Integer Deduct = userInfoMapper.updateUserInfoDeduct(deductMap);
				if(Deduct==1){
					Map<String, Object> rechargeMap = new HashMap<String, Object>();
					rechargeMap.put("smsNum", rechargeNum);
					rechargeMap.put("taobaoUserNick", rechargeUserName);
					Integer recharge = userInfoMapper.updateUserInfoRecharge(rechargeMap);
					if(recharge !=1){
						throw new Exception("转短信----执行充值短信时操作的用户数："+recharge+"个");
					}
				 }else{
					 throw new Exception("转短信----执行扣除短信时操作的用户数："+Deduct+"个");
				 }
	}

	@Override
	@DataSource(value="crmDataSource")
	public List<UserInfo> findUserInfoSmsNumList(List<String> list) {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		if(null != list && list.size()>0){
			userInfoList = userInfoMapper.findUserInfoSmsNumList(list);
		}
		return userInfoList;
	}


	@Override
	@DataSource(value="crmDataSource")
	public Integer findUserInfoSmsNum(String userId) {
		return userInfoMapper.findUserInfoSmsNum(userId);
	}


	@Override
	@DataSource(value="crmDataSource")
	public UserInfo findUserInfoByCustomer(String userId) {
		return userInfoMapper.findUserInfoByCustomer(userId);
	}

}