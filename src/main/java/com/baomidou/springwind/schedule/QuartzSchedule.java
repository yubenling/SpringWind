package com.baomidou.springwind.schedule;
import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuartzSchedule {
	
    private static String JOB_GROUP_NAME = "DEFAULT_JOBGROUP_NAME";  
    private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP_NAME";  
    
	@Resource
    Scheduler sched;
  
    /** 
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
     *  
     * @param jobName 
     *            任务名 
     * @param cls 
     *            任务 
     * @param time 
     *            时间设置，参考quartz说明文档 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @version V2.0 
     */  
    @SuppressWarnings("rawtypes")
	public   void addJob(String jobName,String triggerName, Class cls, String time) {  
        try {  
        	@SuppressWarnings("unchecked")
			JobDetail jobDetail = JobBuilder
					.newJob(cls)
					.withIdentity(jobName,JOB_GROUP_NAME)
					.build();
			CronTrigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(triggerName,TRIGGER_GROUP_NAME)
					.forJob(jobDetail).withSchedule(
					CronScheduleBuilder.cronSchedule(time))
					.build();
            sched.scheduleJob(jobDetail, trigger);  
            // 启动  
            if (!sched.isShutdown()) {  
                sched.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 添加一个定时任务 
     *  
     * @param jobName 
     *            任务名 
     * @param jobGroupName 
     *            任务组名 
     * @param triggerName 
     *            触发器名 
     * @param triggerGroupName 
     *            触发器组名 
     * @param jobClass 
     *            任务 
     * @param time 
     *            时间设置，参考quartz说明文档 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @version V2.0 
     */  
    @SuppressWarnings("rawtypes")
    public   void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class jobClass,  
            String time) {  
        try {  
        	@SuppressWarnings("unchecked")
			JobDetail jobDetail = JobBuilder
					.newJob(jobClass)
					.withIdentity(jobName,jobGroupName)
					.build();
			CronTrigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(triggerName,triggerGroupName)
					.forJob(jobDetail).withSchedule(
					CronScheduleBuilder.cronSchedule(time))
					.build();
            sched.scheduleJob(jobDetail, trigger);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /** 
     * @Description: 暂停一个定时任务 
     *  
     * @param jobName 
     *            任务名 
     * @param jobGroupName 
     *            任务组名 
     * @version V2.0 
     */  
	public  void  pauseJob (String jobName,String jobGroup){
	    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
	    try {
			sched.pauseJob(jobKey);
		} catch (SchedulerException e) {
			  throw new RuntimeException(e);  
		}
	}
	/** 
	 * @Description: 恢复一个定时任务 
	 *  
	 * @param jobName 
	 *            任务名 
	 * @param jobGroupName 
	 *            任务组名 
	 * @version V2.0 
	 */  
	public  void  resumeJob (String jobName,String jobGroup){
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			sched.resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);  
		}
	}
	/** 
	 * @Description: 删除一个定时任务 
	 *  
	 * @param jobName 
	 *            任务名 
	 * @param jobGroupName 
	 *            任务组名 
	 * @version V2.0 
	 */  
	public  void  deleteJob (String jobName,String jobGroup){
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			sched.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);  
		}
	}
	/** 
	 * @Description: 立即运行一个定时任务 
	 *  
	 * @param jobName 
	 *            任务名 
	 * @param jobGroupName 
	 *            任务组名 
	 * @version V2.0 
	 */  
	public  void  triggerJob (String jobName,String jobGroup){
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			sched.triggerJob(jobKey); 
		} catch (SchedulerException e) {
			throw new RuntimeException(e);  
		}
	}
  
    /** 
     * @Description:启动所有定时任务 
     *  
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @version V2.0 
     */  
    public   void startJobs() {  
        try {  
            sched.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description:关闭所有定时任务 
     *  
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @version V2.0 
     */  
//    public static void shutdownJobs() {  
//        try {  
//            Scheduler sched = gSchedulerFactory.getScheduler();  
//            if (!sched.isShutdown()) {  
//                sched.shutdown();  
//            }  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//    }  
}  
