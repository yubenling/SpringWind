package com.baomidou.springwind.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.VipUser;

/**
 *
 * VipUser 表数据服务层接口
 *
 */
public interface IVipUserService extends IService<VipUser> {

	Page<VipUser> findVipUserList(Map<String, Object> map);

	String insertVipUser(VipUser vipUser);

	String deleteVipUser(String id);

	Integer findVipUserCount(String vipUserNick);

	Long findVipUserUid(String vipUserNick);
}