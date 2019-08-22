package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 保存每月底用户剩余的短信条数
 */
@TableName(value = "crm_user_month_surplus_sms")
public class UserMonthSurplusSms implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;
	
	/**用户ID*/
	private String userNick;

	/**用户当前剩余数量*/ 
	private Integer surplusNum;
	
	/**创建时间*/
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
