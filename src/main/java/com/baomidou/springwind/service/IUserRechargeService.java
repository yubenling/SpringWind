package com.baomidou.springwind.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.UserRecharge;

/**
 *
 * UserRecharge 表数据服务层接口
 *
 */
public interface IUserRechargeService extends IService<UserRecharge> {

	Page<UserRecharge> findUserRecharge(Map<String, Object> map);

	List<String> findUserRechargeName();

	Page<UserRecharge> findUserRechargeList(Map<String, Object> map);

	void executeDeleteSql();
}