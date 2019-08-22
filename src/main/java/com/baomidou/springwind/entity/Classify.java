package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 客户分类表
 */
@TableName(value = "Classify")
public class Classify implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	private Long id;
	
	/**分类名称*/
	private String classifyName;
	
	/** 分类描述*/
	private String classifyDescribe;
	
	/** 客户所有者 */
	private String owner;
	
	/** 创建时间*/
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getClassifyDescribe() {
		return classifyDescribe;
	}

	public void setClassifyDescribe(String classifyDescribe) {
		this.classifyDescribe = classifyDescribe;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
