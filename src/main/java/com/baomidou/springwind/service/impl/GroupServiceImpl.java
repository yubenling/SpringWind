package com.baomidou.springwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.CrmGroup;
import com.baomidou.springwind.mapper.GroupMapper;
import com.baomidou.springwind.service.IGroupService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * Group 表数据服务层接口实现类
 *
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, CrmGroup> implements IGroupService {

	@Autowired
	private GroupMapper groupMapper;

	 
}
