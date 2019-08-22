package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 客户表
 *
 */
@TableName(value = "crm_customer")
public class Customer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;
	
	/** 客户id*/
	private Long uid;

	/** 客户昵称 */
	private String customerName;

	/** qq */
	private String qqAcount;

	/** 手机号 */
	private String phone;

	/** 描述 */
	private String description;
	
	/** 是否成单  2是充值 1 是 0 否 */
	private Integer isBuy;
	
	/** 创建时间 */
	private Date createTime;
	/** 到期时间 */
	private Date expireTime;
	/** 更新时间 */
	private Date updateTime;
	
	/** 更新人*/
	private String opeater;
	
	/** 延期次数 */
	private Integer delayCount;
	
	/** 自己共享or组内共享or 全员共享 */
	private Long ownGroup;
	
	
	/** 现在客户所有者 */
	private String owner;
	/** 之前客户所有者 */
	private String beforeOwner;
	/** 之前客户所有者 */
	private Integer isOperate;
	
	
	//短信剩余数量
	private Integer restSmsNum;
	//当天登录次数
	private Integer todayLoginCount;
	//历史登录次数
	private Integer loginCount;
	/** 开始时间*/
	@TableField(exist = false)
	private String startExpireTime;
	/** 结束时间 */
	@TableField(exist = false)
	private String endExpireTime;
	
	/** 最后一次登录时间 */
	private Date lastLoginDate;
	/** 是否未登录用户  0--是 1--否*/
	private Integer notRegister;
	
	
	/** 用户等级，分为20个级别*/
	private Long level;
	
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Integer getNotRegister() {
		return notRegister;
	}
	public void setNotRegister(Integer notRegister) {
		this.notRegister = notRegister;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getQqAcount() {
		return qqAcount;
	}
	public void setQqAcount(String qqAcount) {
		this.qqAcount = qqAcount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOpeater() {
		return opeater;
	}
	public void setOpeater(String opeater) {
		this.opeater = opeater;
	}
	public Integer getDelayCount() {
		return delayCount;
	}
	public void setDelayCount(Integer delayCount) {
		this.delayCount = delayCount;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBeforeOwner() {
		return beforeOwner;
	}
	public void setBeforeOwner(String beforeOwner) {
		this.beforeOwner = beforeOwner;
	}
	public String getStartExpireTime() {
		return startExpireTime;
	}
	public void setStartExpireTime(String startExpireTime) {
		this.startExpireTime = startExpireTime;
	}
	public String getEndExpireTime() {
		return endExpireTime;
	}
	public void setEndExpireTime(String endExpireTime) {
		this.endExpireTime = endExpireTime;
	}
	public Long getOwnGroup() {
		return ownGroup;
	}
	public void setOwnGroup(Long ownGroup) {
		this.ownGroup = ownGroup;
	}
	public Integer getIsOperate() {
		return isOperate;
	}
	public void setIsOperate(Integer isOperate) {
		this.isOperate = isOperate;
	}
	public Integer getRestSmsNum() {
		return restSmsNum;
	}
	public void setRestSmsNum(Integer restSmsNum) {
		this.restSmsNum = restSmsNum;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getTodayLoginCount() {
		return todayLoginCount;
	}
	public void setTodayLoginCount(Integer todayLoginCount) {
		this.todayLoginCount = todayLoginCount;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
}
