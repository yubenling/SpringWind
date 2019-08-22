package com.baomidou.springwind.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.UserLoginInfo;

/**
 * 
 * UserLoginInfo 表数据服务层接口
 * 
 */
public interface IUserLoginInfoService extends IService<UserLoginInfo> {

	List<UserLoginInfo> findUserLoginInfoList( UserLoginInfo  paramUserLoginInfo );

	
}