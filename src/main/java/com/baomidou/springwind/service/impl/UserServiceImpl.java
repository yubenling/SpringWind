package com.baomidou.springwind.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserGroup;
import com.baomidou.springwind.entity.UserRole;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IUserGroupService;
import com.baomidou.springwind.service.IUserRoleService;
import com.baomidou.springwind.service.IUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IUserGroupService userGroupService;
	@Autowired
	private ICustomService customService;

	@Log("登录")
	@Override
	public User selectByLoginName(String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		return super.selectOne(new EntityWrapper<User>(user));
	}

	@Log("删除用户")
	@Override
	public void deleteUser(Long userId) {
		// 删除用户角色，再删除用户
		try {
			roleService.deleteByUserId(userId);
			userGroupService.deleteByUserId(userId);
			super.deleteById(userId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Log("添加用户")
	public boolean insert(User entity) {
		System.err.println("覆盖父类方法 ");
		UserRole userRole  = new UserRole();
		UserGroup userGroup  = new UserGroup();
		boolean result = true;
		Map<String,Object>  map  = new HashMap<String, Object>();
		try {
			super.insert(entity);
			map.put("loginName", entity.getLoginName());
			List<User> selectByMap = super.selectByMap(map);
			if(null!=selectByMap&&selectByMap.size()>0){
				 User user = selectByMap.get(0);
				 userRole.setUid(user.getId());
				 userRole.setRid(entity.getRid()==null?2l:Long.valueOf(entity.getRid())); 
				 userRoleService.insert(userRole); 
				 userGroup.setUid(user.getId());
			     userGroup.setGid(entity.getGid()==null?0l:Long.valueOf(entity.getGid())); 
			     userGroupService.insert(userGroup);  
			}
		} catch (Exception e) {
			result = false;
			throw new RuntimeException();
		}
		return result;
	}
	@Log("修改用户角色群组")
	@Override
	public boolean updateUser(User user) {
		System.err.println("覆盖父类方法 ");
		boolean result = true;
		Map<String,Object>  map  = new HashMap<String, Object>();
		try {
			super.updateById(user);
			map.put("uid", user.getId());
			if(null== user.getId() || 0==user.getId()) return false;
			List<UserRole> selectByMap = userRoleService.selectByMap(map);
			if(null!=selectByMap&&selectByMap.size()>0){
				UserRole userRole = selectByMap.get(0);
				userRole.setRid(user.getRid()==null?userRole.getRid():Long.valueOf(user.getRid()));
				userRoleService.updateById(userRole) ;
			}
			List<UserGroup> userGroupList = userGroupService.selectByMap(map);
			if(null!=userGroupList&&userGroupList.size()>0){
				UserGroup userGroup = userGroupList.get(0);
				if(null!=user.getGid()&&!"".equals(user.getGid())){
					if(!user.getGid().equals(userGroup.getGid())){
						System.out.println("更改 "+user.getId()+"从"+userGroup.getGid()+"组到"+userGroup.getGid()+"组");
						userGroup.setGid(Long.valueOf(user.getGid()));
						userGroupService.updateById(userGroup) ;
					}
				}
			}
			if(null!=user.getOid()&&!"".equals(user.getOid())){
				 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
				 Customer   customer = new Customer();
				 customer.setOwnGroup(1l);
				 customer.setOwner(user.getId()+"");
				 ew.setEntity(customer);
				 List<Customer> customerList = customService.selectList(ew);
				 for (Customer customer2 : customerList) {
					 System.out.println("更改 "+user.getId()+"的用户"+customer2.getId()+"到"+user.getOid());
					 customer2.setOwner(user.getOid());
					customService.updateById(customer2);
				}
			}
		} catch (Exception e) {
			result = false;
			throw new RuntimeException();
		}
		return result;
	}
	
	
}