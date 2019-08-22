package com.baomidou.springwind.common;

import java.io.Serializable;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;

public class GlobalConfig extends GlobalConfiguration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6254023006198859564L;
	//重写父类主键生成方式
	public GlobalConfig(){
		 setIdType(0);
	}
	
}
