package com.baomidou.springwind.schedule;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.IUserRechargeService;
import com.baomidou.springwind.util.DateUtils;
@Service
public class AutoUpdateTodayLoginCountSchedule implements  Job{
	private static final Log log = LogFactory.getLog(AutoUpdateTodayLoginCountSchedule.class);
	
	@Resource
	protected ICustomService customService;
	
	@Override
	public void execute(JobExecutionContext arg0) {
        log.info("清空用户的当天登录次数处理开始~~~~~~"+DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"~~~~~~~~~~");
        executeDeleteSql();
		log.info("清空用户的当天登录次数处理结束~~~~~~"+DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"~~~~~~~~~~");
	}

	public void executeDeleteSql (){
		try {
			customService.updateTodayLoginCount();
			log.info("清空用户的当天登录次数~~~~~~~成功~~~~~~~~~~");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("清空用户的当天登录次数~~~~~~~失败~~~~~~~~~~");
		}
	}
	
}
