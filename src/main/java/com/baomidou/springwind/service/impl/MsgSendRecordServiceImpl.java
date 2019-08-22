package com.baomidou.springwind.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.DataSource;
import com.baomidou.springwind.entity.MsgSendRecord;
import com.baomidou.springwind.mapper.MsgSendRecordMapper;
import com.baomidou.springwind.service.IMsgSendRecordService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * MsgSendRecord 表数据服务层接口实现类
 *
 */
@Service
public class MsgSendRecordServiceImpl extends BaseServiceImpl<MsgSendRecordMapper, MsgSendRecord> implements IMsgSendRecordService{
	
	@Autowired
	private MsgSendRecordMapper msgSendRecordMapper;

	@Override
	@DataSource(value="crmDataSource")
	public Page<MsgSendRecord> findMsgSendRecordPage(Map<String, Object> map) {
		Page<MsgSendRecord> page = new Page<MsgSendRecord>();
		
		if ( null != map.get("type") && "1".equals(map.get("type"))) {
			List<MsgSendRecord> OrderSmslist = msgSendRecordMapper.findOrderSendRecordPage(map);
			Integer	total = msgSendRecordMapper.findOrderSendRecordPageCount(map);
			page.setRecords(OrderSmslist);
			page.setTotal(total);
		}else{
			List<MsgSendRecord> list = msgSendRecordMapper.findMsgSendRecordPage(map);
			Integer total = msgSendRecordMapper.findMsgSendRecordPageCount(map);
			page.setRecords(list);
			page.setTotal(total);
		}
		return page;
	}
}