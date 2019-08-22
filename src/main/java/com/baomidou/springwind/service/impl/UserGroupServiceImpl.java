package com.baomidou.springwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.UserGroup;
import com.baomidou.springwind.entity.UserRole;
import com.baomidou.springwind.mapper.UserGroupMapper;
import com.baomidou.springwind.mapper.UserRoleMapper;
import com.baomidou.springwind.service.IUserGroupService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * UserGroup 表数据服务层接口实现类
 *
 */
@Service
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroupMapper, UserGroup> implements IUserGroupService {
	@Autowired
	private UserGroupMapper userGroupMapper;
	@Override
	public void deleteByUserId(Long userId) {
		UserGroup ur = new UserGroup();
		ur.setUid(userId);
		userGroupMapper.delete(new EntityWrapper<UserGroup>(ur));
		
	}


 

	

}