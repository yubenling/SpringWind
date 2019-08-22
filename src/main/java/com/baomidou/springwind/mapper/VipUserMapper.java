package com.baomidou.springwind.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.VipUser;

/**
 *
 * Classify 表数据库控制层接口
 *
 */
public interface VipUserMapper extends BaseMapper<VipUser> {

	Integer insertVipUser(VipUser vipUser);

	Integer deleteVipUser(String id);

	List<VipUser> findVipUserList(Map<String, Object> map);

	Integer findVipUserListCount(Map<String, Object> map);

	Integer findVipUserCount(String vipUserNick);

	Long findVipUserUid(String vipUserNick);
}