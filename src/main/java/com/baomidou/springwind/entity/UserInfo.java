package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;


@TableName(value = "CRM_USER")
public class UserInfo  implements Serializable{
	

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 6225438854229294022L;

    private Long id;

	//注释(value = "淘宝账户对应Id")
	private String taobaoUserId;

	//注释(value = "淘宝子账户对应Id")
	private String subtaobaoUserId;

	//注释(value = "创建时间")
	private Date createTime;

	//注释(value = "状态")
	private Integer status;

	//注释(value = "最后一次登录时间")
	private Date lastLoginDate;

	//注释(value = "账户过期时间")
	private Date expirationTime;

	//注释(value = "用户手机号")
	private String mobile;

	//注释(value = "top分配给用户的key")
	private String appkey;

	//注释(value = "淘宝账号(昵称)")
	private String taobaoUserNick;

	//注释(value = "淘宝子账号")
	private String subtaobaoUserNick;

	//注释(value = "修改时间")
	private Date modifyTime;

	//注释(value = "短信剩余数量")
	private Integer smsNum;

	//注释(value = "邮件剩余数量")
	private Integer emailNum;

	//注释(value = "卖家用户的sessionKey")
	private String access_token;
	
	
	//注释(value = "卖家用户的QQ号")
	private String qqNum;
	
	/** 用户等级，分为20个级别*/
	private Long level;
	
	// "是否赠送500条短信"
    private Boolean hasProvide;
	
	public Boolean getHasProvide() {
		return hasProvide;
	}
	public void setHasProvide(Boolean hasProvide) {
		this.hasProvide = hasProvide;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaobaoUserId() {
		return taobaoUserId;
	}
	public void setTaobaoUserId(String taobaoUserId) {
		this.taobaoUserId = taobaoUserId;
	}
	public String getSubtaobaoUserId() {
		return subtaobaoUserId;
	}
	public void setSubtaobaoUserId(String subtaobaoUserId) {
		this.subtaobaoUserId = subtaobaoUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getTaobaoUserNick() {
		return taobaoUserNick;
	}
	public void setTaobaoUserNick(String taobaoUserNick) {
		this.taobaoUserNick = taobaoUserNick;
	}
	public String getSubtaobaoUserNick() {
		return subtaobaoUserNick;
	}
	public void setSubtaobaoUserNick(String subtaobaoUserNick) {
		this.subtaobaoUserNick = subtaobaoUserNick;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getSmsNum() {
		return smsNum;
	}
	public void setSmsNum(Integer smsNum) {
		this.smsNum = smsNum;
	}
	public Integer getEmailNum() {
		return emailNum;
	}
	public void setEmailNum(Integer emailNum) {
		this.emailNum = emailNum;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
}
