package com.baomidou.springwind.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.MsgSendRecord;

/**
 * 
 * MsgSendRecord 表数据服务层接口
 * 
 */

public interface IMsgSendRecordService extends IService<MsgSendRecord> {

	Page<MsgSendRecord> findMsgSendRecordPage(Map<String, Object> map);
}