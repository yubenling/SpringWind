package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

/**用户充值记录表，按月统计*/
public class UserRecharge  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**用户ID*/
	private String userNick;

	/**充值金额*/
	private double rechargePrice;

	/**充值数量*/
	private Integer rechargeNUm;

	/**赠送数量*/
	private Integer presentNum;
	
	/**平均单价=rechargePrice/(rechargeNUm+presentNum)*/
	private Double averagePrice;
	
	/**发送数量 = rechargeNUm+presentNum+shiftToNum-deductNum-surplusNum*/
	private Integer sendNum;
	
	/**用户当前剩余数量*/ 
	private Integer surplusNum;
	
	/**汇总月份*/
	private String rechargeDate;

	/**转出数量*/
	private Integer deductNum;
	
	/**转入数量*/
	private Integer shiftToNum;
	
	/**充值时间*/
	private Date rechargeTime;
	
	/**充值状态 1-成功 2-失败 3-待付款*/
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Integer getShiftToNum() {
		return shiftToNum;
	}

	public void setShiftToNum(Integer shiftToNum) {
		this.shiftToNum = shiftToNum;
	}

	public Integer getDeductNum() {
		return deductNum;
	}

	public void setDeductNum(Integer deductNum) {
		this.deductNum = deductNum;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public double getRechargePrice() {
		return rechargePrice;
	}

	public void setRechargePrice(double rechargePrice) {
		this.rechargePrice = rechargePrice;
	}

	public Integer getRechargeNUm() {
		return rechargeNUm;
	}

	public void setRechargeNUm(Integer rechargeNUm) {
		this.rechargeNUm = rechargeNUm;
	}

	public Integer getPresentNum() {
		return presentNum;
	}

	public void setPresentNum(Integer presentNum) {
		this.presentNum = presentNum;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public String getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(String rechargeDate) {
		this.rechargeDate = rechargeDate;
	}


	
}
