package com.baomidou.springwind.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.MsgSendRecord;

/**
 *
 * MsgSendRecord 表数据库控制层接口
 *
 */
public interface MsgSendRecordMapper extends BaseMapper<MsgSendRecord> {

	List<MsgSendRecord> findMsgSendRecordPage(Map<String, Object> map);

	int findMsgSendRecordPageCount(Map<String, Object> map);

	List<MsgSendRecord> findOrderSendRecordPage(Map<String, Object> map);

	Integer findOrderSendRecordPageCount(Map<String, Object> map);
	

}