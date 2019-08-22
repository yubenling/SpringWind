package com.baomidou.springwind.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.VipUser;
import com.baomidou.springwind.mapper.VipUserMapper;
import com.baomidou.springwind.service.IVipUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * VipUser 表数据服务层接口实现类
 *
 */
@Service
public class VipUserServiceImpl extends BaseServiceImpl<VipUserMapper, VipUser> implements IVipUserService {
	@Autowired
	private VipUserMapper   vipUserMapper;
	
	@Override
	@DataSource(value="crmDataSource")
	public Page<VipUser> findVipUserList(Map<String, Object> map) {
		Page<VipUser> page = new Page<VipUser>();
		List<VipUser> list = vipUserMapper.findVipUserList(map);
		Integer total = vipUserMapper.findVipUserListCount(map);
		page.setRecords(list);
		page.setTotal(total);
		return page;
	}
	
	@Override
	@DataSource(value="crmDataSource")
	public String insertVipUser(VipUser vipUser) {
		try {
			Integer i = vipUserMapper.insertVipUser(vipUser);
			if(i != 0){
				return "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}
	
	@Override
	@DataSource(value="crmDataSource")
	public Integer findVipUserCount(String vipUserNick) {
		return vipUserMapper.findVipUserCount(vipUserNick);
	}

	@Override
	@DataSource(value="crmDataSource")
	public String deleteVipUser(String id) {
		try {
			Integer i = vipUserMapper.deleteVipUser(id);
			if(i != 0){
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}

	@Override
	@DataSource(value="crmDataSource")
	public Long findVipUserUid(String vipUserNick) {
		return vipUserMapper.findVipUserUid(vipUserNick);
	}

}