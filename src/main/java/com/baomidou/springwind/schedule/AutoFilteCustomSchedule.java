package com.baomidou.springwind.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.UserGroup;
import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.IUserGroupService;
import com.baomidou.springwind.util.DateUtils;
@Service
public class AutoFilteCustomSchedule implements  Job{
	private static final Log log = LogFactory.getLog(AutoFilteCustomSchedule.class);
	
	@Resource
	protected ICustomService customService;
	@Resource
	private IUserGroupService userGroupService;
	@Override
	public void execute(JobExecutionContext arg0) {
		//1.扫描客户
		//2.每个更新客户的异常不能影响其他的客户(自行try)
//		String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd  HH:mm:ss");
//		date  = date.substring(0, date.length()-2)+"00";
        log.info("客户超时过滤处理开始~~~~~~~~~~~~~~~~~");
//        handlePersonalCustom(date);
//        handleGroupCustom(date);
		log.info("客户超时过滤处理结束~~~~~~~~~~~~~~~~~");
	}

	@Transactional
	private void handleGroupCustom(String date) {
		 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		 ew.le("expireTime", date);
		 ew.notIn("ownGroup", 1l,2l);
		 List<Customer> customerList = customService.selectList(ew);
		 if(null!=customerList&&customerList.size()>0){
			 log.info("释放到资源池里面的数据size~~~~~~~~~~~~~~~~~"+customerList.size());
			  for (Customer customer2 : customerList) {
				 try {
					 customer2.setExpireTime(DateUtils.addMonth(customer2.getExpireTime(), 12)); 
					 customer2.setOwnGroup(2l);
					 customer2.setDescription("到期自动释放到资源池！");
					 customService.updateById(customer2);
				} catch (Exception e) {
					 log.error("更新客户失败"+customer2.getId()+e.getMessage());
				}
			 }
		 }
		 
	}

	@Transactional
	private void handlePersonalCustom(String date) {
		
		 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		 Customer   customer = new Customer();
		 customer.setOwnGroup(1l);
		 ew.le("expireTime", date);
		 ew.setEntity(customer);
		 List<Customer> customerList = customService.selectList(ew);
		 if(null!=customerList&&customerList.size()>0){
			 log.info("释放到组里面的数据size~~~~~~~~~~~~~~~~~"+customerList.size());
			  for (Customer customer2 : customerList) {
				 try {
					String owner = customer2.getOwner();
					 UserGroup userGroup  = getUserGroup(owner);
					 if(null!=userGroup){
						 customer2.setExpireTime(DateUtils.addMonth(customer2.getExpireTime(), 1)); 
						 customer2.setOwnGroup(userGroup.getGid());
						 customer2.setDescription(customer2.getDescription()+"到期自动释放到同组！");
						 customService.updateById(customer2);
					 }else{
						 log.error("更新客户失败"+customer2.getId()+"组为空！"); 
					 }
				} catch (Exception e) {
					 log.error("更新客户失败"+customer2.getId()+e.getMessage());
				}
			 }
		 }
	}
	
	private UserGroup getUserGroup(String owner) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("uid", owner);
		List<UserGroup> userGroupList = userGroupService.selectByMap(map);
		if(null!=userGroupList&&userGroupList.size()>0){
			return userGroupList.get(0);
		}
		return  null;
	}
}
