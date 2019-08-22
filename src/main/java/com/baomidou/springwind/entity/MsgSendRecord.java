package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value = "CRM_MSGRECORD")
public class MsgSendRecord implements Serializable{
	
	private static final long serialVersionUID = 4798207809518745750L;

	
	/**卖家昵称*/
	private String userId;
	
	/**短信批量发送总数*/
	private Integer totalCount;
	
	/**短信批量发送成功总数*/
	private Integer succeedCount;
	
	/**短信批量发送失败总数/调用接口失败*/
	private Integer failedCount;
	
	/**短信批量发送手机号错误总数*/
	private Integer wrongCount;
	
	/**短信批量发送手机号重复总数*/
	private Integer repeatCount;
	
	/**短信批量发送黑名单总数*/
	private Integer blackCount;
	
	/**短信批量发送被屏蔽总数*/
	private Integer sheildCount;
	
	/**状态--1:全部成功/2:全部失败/3:部分成功/4:发送中/5:发送完成(该字段作为识别字段)*/
	private String status;
	
	/**短信基础/模板内容*/
	private String templateContent;
	
	/**活动名称*/
	private String activityName;
	
	/**创建时间*/
	private Date sendCreat;
	
	/**短信类型*/
	private String type;
	
	/**是否删除(显示或者隐藏)--true:显示 /false:不显示  默认保存true*/
	private Boolean isShow;
	
	/**是否已发送--true:已发送/false:未发送,未发送说明是定时任务的总记录待发送*/
	private Boolean isSent;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSucceedCount() {
		return succeedCount;
	}

	public void setSucceedCount(Integer succeedCount) {
		this.succeedCount = succeedCount;
	}

	public Integer getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(Integer failedCount) {
		this.failedCount = failedCount;
	}

	public Integer getWrongCount() {
		return wrongCount;
	}

	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Integer getBlackCount() {
		return blackCount;
	}

	public void setBlackCount(Integer blackCount) {
		this.blackCount = blackCount;
	}

	public Integer getSheildCount() {
		return sheildCount;
	}

	public void setSheildCount(Integer sheildCount) {
		this.sheildCount = sheildCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getSendCreat() {
		return sendCreat;
	}

	public void setSendCreat(Date sendCreat) {
		this.sendCreat = sendCreat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}
}
