package com.baomidou.springwind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.UserLoginInfo;
import com.baomidou.springwind.mapper.UserLoginInfoMapper;
import com.baomidou.springwind.service.IUserLoginInfoService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * UserInfo 表数据服务层接口实现类
 *
 */
@Service
public class UserLoginInfoServiceImpl extends BaseServiceImpl<UserLoginInfoMapper, UserLoginInfo> implements IUserLoginInfoService{

	@Autowired
	private UserLoginInfoMapper  userLoginInfoMapper;

	@Override
	@DataSource("crmDataSource")
	public List<UserLoginInfo> findUserLoginInfoList( UserLoginInfo  paramUserLoginInfo ) {
		return userLoginInfoMapper.findUserLoginInfoList(paramUserLoginInfo );
	}
}