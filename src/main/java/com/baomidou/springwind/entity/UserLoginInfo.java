/**
 * 
 */
package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/** 
 * @Title: 用户信息登录表
 * @date 2017年4月20日--上午10:46:10 
 * @param     设定文件 
 * @return 返回类型 
 * @throws 
 */


@TableName(value = "CRM_USERLOGININFO")
public class UserLoginInfo   implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1649764712407233302L;

	@TableId
    private Long id;
	
	//注释(value = "卖家用户名")
	private String sellerNick;
	
	//注释(value = "登录时间")
	private Date loginTime;
	
	//注释(value = "登录IP地址")
	private String IpAddress;
	
	@TableField(exist = false)
	private Integer  loginCount;
	
	
	/**  开始时间 */
	@TableField(exist = false)
	private String startTime;
	/** 结束时间 */
	@TableField(exist = false)
	private String endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIpAddress() {
		return IpAddress;
	}

	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
}