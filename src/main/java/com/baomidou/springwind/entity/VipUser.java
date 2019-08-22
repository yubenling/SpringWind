package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * VIP小客户表
 */
@TableName(value = "CRM_VIP_USER")
public class VipUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId
    private Long id;
	
	/*vip用户名称*/
	private Long uid;
	
	/*vip用户名称*/
	private String vipUserNick;
	
	/*vip录入用户ID*/
	private String userId;
	
	/*创建时间*/
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getVipUserNick() {
		return vipUserNick;
	}

	public void setVipUserNick(String vipUserNick) {
		this.vipUserNick = vipUserNick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
