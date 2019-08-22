package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 充值记录表
 *
 */
@TableName(value = "crm_recharge_record")
public class RechargeRecord implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	@TableId
	private Long id;
	
	/** 登录名称 */
	private String loginName;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 转出店铺名 */
	private String deductUserName;
	
	/** 充值店铺名 */
	private String rechargeUserName;
	
	/**充值（扣除）短信数量*/
	private Integer rechargeNum;
	
	/**充值店铺----充值前短信数量*/
	private Integer rechargeAgoSmsNum;
	
	/**充值店铺----充值后短信数量*/
	private Integer rechargeLaterSmsNum;
	
	/**转出店铺----转出前短信数量*/
	private Integer deductAgoSmsNum;
	
	/**转出店铺----转出后短信数量*/
	private Integer deductLaterSmsNum;
	
	/**0--充值、1--转账*/
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeductUserName() {
		return deductUserName;
	}

	public void setDeductUserName(String deductUserName) {
		this.deductUserName = deductUserName;
	}

	public String getRechargeUserName() {
		return rechargeUserName;
	}

	public void setRechargeUserName(String rechargeUserName) {
		this.rechargeUserName = rechargeUserName;
	}

	public Integer getRechargeNum() {
		return rechargeNum;
	}

	public void setRechargeNum(Integer rechargeNum) {
		this.rechargeNum = rechargeNum;
	}

	public Integer getRechargeAgoSmsNum() {
		return rechargeAgoSmsNum;
	}

	public void setRechargeAgoSmsNum(Integer rechargeAgoSmsNum) {
		this.rechargeAgoSmsNum = rechargeAgoSmsNum;
	}

	public Integer getRechargeLaterSmsNum() {
		return rechargeLaterSmsNum;
	}

	public void setRechargeLaterSmsNum(Integer rechargeLaterSmsNum) {
		this.rechargeLaterSmsNum = rechargeLaterSmsNum;
	}

	public Integer getDeductAgoSmsNum() {
		return deductAgoSmsNum;
	}

	public void setDeductAgoSmsNum(Integer deductAgoSmsNum) {
		this.deductAgoSmsNum = deductAgoSmsNum;
	}

	public Integer getDeductLaterSmsNum() {
		return deductLaterSmsNum;
	}

	public void setDeductLaterSmsNum(Integer deductLaterSmsNum) {
		this.deductLaterSmsNum = deductLaterSmsNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}