package com.baomidou.springwind.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.UserLoginInfo;

/**
 *
 * UserLoginInfo 表数据库控制层接口
 *
 */
public interface UserLoginInfoMapper extends BaseMapper<UserLoginInfo> {

	List<UserLoginInfo> findUserLoginInfoList(UserLoginInfo  paramUserLoginInfo);
}