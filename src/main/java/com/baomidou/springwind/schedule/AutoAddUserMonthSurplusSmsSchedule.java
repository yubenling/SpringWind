package com.baomidou.springwind.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.UserInfo;
import com.baomidou.springwind.entity.UserMonthSurplusSms;
import com.baomidou.springwind.service.IUserInfoService;
import com.baomidou.springwind.service.IUserMonthSurplusSmsService;
import com.baomidou.springwind.service.IUserRechargeService;
import com.baomidou.springwind.util.DateUtils;
@Service
public class AutoAddUserMonthSurplusSmsSchedule implements  Job{
	private static final Log log = LogFactory.getLog(AutoAddUserMonthSurplusSmsSchedule.class);
	
	@Resource
	protected IUserRechargeService userRechargeService;
	@Resource
	protected IUserMonthSurplusSmsService userMonthSurplusSmsService;
	@Resource
	protected IUserInfoService userInfoService;
	
	@Override
	public void execute(JobExecutionContext arg0) {
		Date date = new Date();
        log.info("保存客户每月底剩余短信数量处理开始~~~~~~~~"+DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"~~~~~~~~~");
        autoAddUserMonthSms(date);
		log.info("保存客户每月底剩余短信数量处理结束~~~~~~~~"+DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"~~~~~~~~~");
	}

	public void autoAddUserMonthSms (Date date){
		try {
			List<String> list = userRechargeService.findUserRechargeName();
			List<UserInfo> UserInfo = userInfoService.findUserInfoSmsNumList(list);
			if(null != UserInfo && UserInfo.size()>0){
				List<UserMonthSurplusSms> entityList = new ArrayList<UserMonthSurplusSms>();
				for (UserInfo u : UserInfo) {
					UserMonthSurplusSms userMonth = new UserMonthSurplusSms();
					userMonth.setCreateTime(date);
					userMonth.setUserNick(u.getTaobaoUserNick());
					userMonth.setSurplusNum(u.getSmsNum());
					entityList.add(userMonth);
				}		
				userMonthSurplusSmsService.insertBatch(entityList);
				log.info("保存客户每月底剩余短信数量处理~~~~~~~~成功~("+UserInfo.size()+")~~~~~~~~");
			}else{
				log.info("保存客户每月底剩余短信数量处理~~~~~~~~无数据~~~~~~~~~");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("保存客户每月底剩余短信数量处理~~~~~~~~失败~~~~~~~~~");
		}
	}
	
}
