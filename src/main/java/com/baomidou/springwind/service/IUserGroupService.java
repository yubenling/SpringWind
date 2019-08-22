package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.UserGroup;

/**
 *
 * UserRole 表数据服务层接口
 *
 */
public interface IUserGroupService extends IService<UserGroup> {

	void deleteByUserId(Long userId);

 

}