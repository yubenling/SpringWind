package com.baomidou.springwind.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserInfo;

/**
 *
 * MemberInfo 表数据库控制层接口
 *
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
	
	List<UserInfo> findUserInfoList();

	void updateUserInfo(Integer smsNum, String userId);

	Integer getSmsNumByUserId(String userId);

	UserInfo getUserByUserId(String userId);

	Integer updateUserInfoRecharge(Map<String, Object> map);

	Integer updateUserInfoDeduct(Map<String, Object> map);

	List<UserInfo> findUserInfoSmsNumList(List<String> list);

	List<UserInfo> findUserAccountList();

	Integer findUserInfoSmsNum(String userId);

	UserInfo findUserInfoByCustomer(String userId);
	

}