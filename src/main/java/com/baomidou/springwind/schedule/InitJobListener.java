package com.baomidou.springwind.schedule;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.springwind.schedule.ScheduleConstants.JobStatus;
@Component
public class  InitJobListener implements ApplicationListener<ContextRefreshedEvent>{
	private static final Log logger = LogFactory.getLog(InitJobListener.class);
	
	private static final String AUTOFILTECUSTOM = JobStatus.CLOSE;
	private static final String AUTOFILTECUSTOMCRON = "0 0 1 * * ? *";
	
	private static final String AUTOUPDATEUSERINFO = JobStatus.OPEN;
	private static final String AUTOUPDATEUSERINFOCRON = "0 0/1 * * * ? *";
	
	private static final String AUTOADDUSERMONTHSURPLUSSMS = JobStatus.OPEN;
	private static final String AUTOADDUSERMONTHSURPLUSSMSCRON = "0 0 1 1 * ? *";
	
	/**清空用户的当天登录次数**/
	private static final String AUTOEXECUTEDELETESQL = JobStatus.OPEN;
	private static final String AUTOEXECUTEDELETESQLCRON = "0 0 0 * * ? *";
	
	
	@Resource
	StdScheduler scheduler;
	
	/**
	 * 名称：onApplicationEvent <br/>
	 * 描述：开启哪些调度，能手动控制<br/>
	 * @param event6
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			try {
				if(JobStatus.OPEN.equals(AUTOFILTECUSTOM)){
					autoFilteCustomJob();
				}
				if(JobStatus.OPEN.equals(AUTOUPDATEUSERINFO)){
					autoUpdateUserInfoJob();
				}
				if(JobStatus.OPEN.equals(AUTOADDUSERMONTHSURPLUSSMS)){
					AutoAddUserMonthSurplusSmsJob();
				}
				if(JobStatus.OPEN.equals(AUTOEXECUTEDELETESQL)){
					AutoExecuteDeleteSqlJob();
				}
			} catch (Exception e) {
				
			}
		}
		
	}
	 
	
	/**
	 * 名称：AutoExecuteDeleteSqlJob <br/>
	 * @throws SchedulerException
	 */
	public void AutoExecuteDeleteSqlJob() throws SchedulerException{
		if (logger.isDebugEnabled()) {
			logger.debug("enable AutoExecuteDeleteSqlJob schdule job");
			
		}
		initSchedule(ScheduleConstants.TriggerName.AUTO_EXECUTEDELETESQL, 
				ScheduleConstants.TriggerGroup.AUTO_EXECUTEDELETESQL, 
				ScheduleConstants.JobName.AUTO_EXECUTEDELETESQL, 
				ScheduleConstants.JobGroup.AUTO_EXECUTEDELETESQL, 
				AutoUpdateTodayLoginCountSchedule.class, AUTOEXECUTEDELETESQLCRON);
	}
	
	
	/**
	 * 名称：AutoAddUserMonthSurplusSmsJob <br/>
	 * @throws SchedulerException
	 */
	public void AutoAddUserMonthSurplusSmsJob() throws SchedulerException{
		if (logger.isDebugEnabled()) {
			logger.debug("enable AutoAddUserMonthSurplusSmsJob schdule job");
			
		}
		initSchedule(ScheduleConstants.TriggerName.AUTO_ADD_USERMONTHSURPLUSSMS, 
				ScheduleConstants.TriggerGroup.AUTO_ADD_USERMONTHSURPLUSSMS, 
				ScheduleConstants.JobName.AUTO_ADD_USERMONTHSURPLUSSMS, 
				ScheduleConstants.JobGroup.AUTO_ADD_USERMONTHSURPLUSSMS, 
				AutoAddUserMonthSurplusSmsSchedule.class, AUTOADDUSERMONTHSURPLUSSMSCRON);
	}

	/**
	 * 名称：autoUpdateUserInfoJob <br/>
	 * @throws SchedulerException
	 */
	public void autoUpdateUserInfoJob() throws SchedulerException{
		if (logger.isDebugEnabled()) {
			logger.debug("enable autoUpdateUserInfoJob schdule job");
			
		}
		initSchedule(ScheduleConstants.TriggerName.AUTO_UPDATE_USERINFO, 
				ScheduleConstants.TriggerGroup.AUTO_UPDATE_USERINFO, 
				ScheduleConstants.JobName.AUTO_UPDATE_USERINFO, 
				ScheduleConstants.JobGroup.AUTO_UPDATE_USERINFO, 
				AutoUpdateUserInfoSchedule.class, AUTOUPDATEUSERINFOCRON);
	}
	
	/**
	 * 名称：autoFilteCustomJob <br/>
	 * 描述：定时筛选客户 <br/>
	 * @throws SchedulerException
	 */
	public void autoFilteCustomJob() throws SchedulerException{
		if (logger.isDebugEnabled()) {
			logger.debug("enable autoFilteCustomJob schdule job");
			
		}
		initSchedule(ScheduleConstants.TriggerName.AUTO_FILTE_CUSTOM, 
				ScheduleConstants.TriggerGroup.AUTO_FILTE_CUSTOM, 
				ScheduleConstants.JobName.AUTO_FILTE_CUSTOM, 
				ScheduleConstants.JobGroup.AUTO_FILTE_CUSTOM, 
				AutoFilteCustomSchedule.class, AUTOFILTECUSTOMCRON);
	}
	
	
	/**
	 * @Name: initSchedule <br/>
	 * @description: cron类型的任务初始化数据库信息 <br/>
	 * @param triggerName		触发器名称
	 * @param triggerGroup		触发器组
	 * @param JobName			任务名称
	 * @param jobGroup			任务组
	 * @param clazz				要执行的JOB类
	 * @param cron				cron表达式
	 * @throws SchedulerException
	 */
	@Transactional
	public void initSchedule(String triggerName,String triggerGroup,String JobName,
			String jobGroup,@SuppressWarnings("rawtypes") Class clazz,String cron) throws SchedulerException{
		if (logger.isDebugEnabled()) {
			logger.debug("enable "+clazz.getName()+" schdule job");
		}
		CronTrigger trigger = (CronTrigger) scheduler
				.getTrigger(TriggerKey.triggerKey(triggerName,triggerGroup));
		if (trigger == null) {
			@SuppressWarnings("unchecked")
			JobDetail jobDetail2 = JobBuilder
					.newJob(clazz)
					.withIdentity(JobName,jobGroup)
					.build();
			CronTrigger trigger2 = TriggerBuilder
					.newTrigger()
					.withIdentity(triggerName,triggerGroup)
					.forJob(jobDetail2).withSchedule(
					CronScheduleBuilder.cronSchedule(cron))
					.build();
			scheduler.scheduleJob(jobDetail2, trigger2);
		} else {
			scheduler.resumeTrigger(trigger.getKey());
		}
	}
}
