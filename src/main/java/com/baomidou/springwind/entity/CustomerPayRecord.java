package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


@TableName(value = "crm_customer_pay_record")
public class CustomerPayRecord implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 8276666061287113213L;



	/** 主键 */
	@TableId
	private Long id;

	/** 客户id */
	private String customerId;
	private Integer buyType;
	private String payType;
	private BigDecimal buyMoney;
	private Date payTime;
	@TableField(exist = false)
	private String payTimeStr;
	private Date createTime;
	private String opeater;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getBuyType() {
		return buyType;
	}
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public BigDecimal getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOpeater() {
		return opeater;
	}
	public void setOpeater(String opeater) {
		this.opeater = opeater;
	}
	public String getPayTimeStr() {
		return payTimeStr;
	}
	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}
}
