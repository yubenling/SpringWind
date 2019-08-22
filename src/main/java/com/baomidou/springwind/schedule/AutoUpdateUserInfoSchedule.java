package com.baomidou.springwind.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.UserInfo;
import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.IUserInfoService;
import com.baomidou.springwind.service.IUserLoginInfoService;
@Service
public class AutoUpdateUserInfoSchedule implements  Job{
	private static final Log log = LogFactory.getLog(AutoUpdateUserInfoSchedule.class);
	
	@Resource
	protected IUserInfoService userInfoService;
	@Resource
	protected IUserLoginInfoService userLoginInfoService;
	@Resource
	protected ICustomService customService;
	
	@Override
	public void execute(JobExecutionContext arg0) {
        log.info("更新用户信息处理开始~~~~~~~~~~~~~~~~~");
        autoUpdateUserInfo();
		log.info("更新用户信息处理结束~~~~~~~~~~~~~~~~~");
	}
	
	
	public void autoUpdateUserInfo (){
		 //用户信息
		 List<UserInfo> userInfoList = userInfoService.findUserInfoList();
		 log.info("用户list"+userInfoList.size());
		 
		 if(null!=userInfoList&&userInfoList.size()>0){
				 HashMap<String,UserInfo> resultMap = new HashMap<String,UserInfo>();
				 for (UserInfo userInfo : userInfoList) {
					 resultMap.put(userInfo.getTaobaoUserNick().trim(), userInfo);
				 }
				 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
				 ew.in("customerName", resultMap.keySet());
				 List<Customer> selectList = customService.selectList(ew);
				 int i =0;
				 List<Customer>  list  =  new ArrayList<Customer>();
				 if(null!=selectList&&selectList.size()>0){
					 for (Customer customer : selectList) {
						try {
							if(resultMap.containsKey(customer.getCustomerName().trim())){
								UserInfo userInfo = resultMap.get(customer.getCustomerName().trim());
								
								Date userLoginDate = userInfo.getLastLoginDate();
								Date customerLoginDate = customer.getLastLoginDate();
								if(!userLoginDate.equals(customerLoginDate)){
									 Integer loginCount = customer.getLoginCount();
									 if(loginCount == null){
										 loginCount = 0;
										}
									 Integer todayLoginCount = customer.getTodayLoginCount();
									 if(todayLoginCount == null){
										 todayLoginCount = 0;
										}
									 loginCount++;
									 todayLoginCount++;
									 customer.setLoginCount(loginCount);
									 customer.setTodayLoginCount(todayLoginCount);
								 }		
								 customer.setUid(userInfo.getId());
								 customer.setLastLoginDate(userInfo.getLastLoginDate());
								 customer.setExpireTime(userInfo.getExpirationTime());
								 if(null!=userInfo.getQqNum()&& !"".equals(userInfo.getQqNum())){
									 customer.setQqAcount(userInfo.getQqNum());
								 }
								 if(null!=userInfo.getMobile()&& !"".equals(userInfo.getMobile())){
									 customer.setPhone(userInfo.getMobile());
								 }
								 customer.setLevel(userInfo.getLevel());
								 if(null!=userInfo.getHasProvide()&&userInfo.getHasProvide()){
									 customer.setNotRegister(1);
								 }else{
									 customer.setNotRegister(0);
								 }
								 customer.setRestSmsNum(userInfo.getSmsNum());
								 
								 i++;
								 list.add(customer);
								 if(i!=0&&i%100==0){
									 log.info("保存用户list"+list.size());
									 customService.updateBatchById(list); 
									 i=0;
									 Thread.sleep(100);
									 list= new ArrayList<Customer>();
								 }
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					 }
					 if(null!=list&&list.size()>0){
						 customService.updateBatchById(list);
					 }
					 log.info("完毕");
				 }
			 }
		 }
	
	
	//---------------------------历史的数据同步-------------------------
	

//	public void autoUpdateUserInfo (String startDate,String endDate){
//		 //用户信息
//		 List<UserInfo> userInfoList = userInfoService.findUserInfoList();
//		 log.info("用户list"+userInfoList.size());
//		 //余额信息
//		 List<UserInfo> useAccountList = userInfoService.findUserAccountList();
//		 log.info("余额list"+useAccountList.size());
//		 //登录信息
//		 UserLoginInfo  paramUserLoginInfo  = new  UserLoginInfo();
//		 paramUserLoginInfo.setStartTime(startDate);
//		 paramUserLoginInfo.setEndTime(endDate);
//		 List<UserLoginInfo> userLoginInfoList = userLoginInfoService.findUserLoginInfoList(paramUserLoginInfo);
//		 log.info("用户登录list"+userLoginInfoList.size());
//		 
//		 if(null!=useAccountList&&useAccountList.size()>0){
//			 HashMap<String,Integer> map = new HashMap<String,Integer>();
//			 HashMap<String,String> mapLogin = new HashMap<String,String>();
//			 for (UserInfo userAccount : useAccountList) {
//				 map.put(userAccount.getTaobaoUserNick(), userAccount.getSmsNum());
//			 }
//			 if(null!=userLoginInfoList&&userLoginInfoList.size()>0){
//				 for (UserLoginInfo userLoginInfo : userLoginInfoList) {
//					 mapLogin.put(userLoginInfo.getSellerNick(), userLoginInfo.getIpAddress()); 
//				 }
//			 }
//			 for (UserInfo userInfo : userInfoList) {
//				 if(null!=map&&map.size()>0){
//					 Integer smsNum = map.get(userInfo.getTaobaoUserNick());
//					 if(null!=smsNum){
//						 userInfo.setSmsNum(smsNum);
//					 }
//				 }
//				 if(null!=mapLogin&&mapLogin.size()>0){
//					 String loginCount = mapLogin.get(userInfo.getTaobaoUserNick());
//					 if(null!=loginCount&&!"".equals(loginCount)){
//						 //借用字段充当下
//						 userInfo.setAppkey(loginCount);
//					 }
//				 }
//			 }
//			 HashMap<String,UserInfo> resultMap = new HashMap<String,UserInfo>();
//			 for (UserInfo userInfo : userInfoList) {
//				 resultMap.put(userInfo.getTaobaoUserNick(), userInfo);
//			 }
//			 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
//			 Customer paramCustomer = new Customer();
//			 ew.setEntity(paramCustomer);
//			 List<Customer> selectList = customService.selectList(ew);
//			 int i =0;
//			 List<Customer>  list  =  new ArrayList<Customer>();
//			 if(null!=selectList&&selectList.size()>0){
//				 for (Customer customer : selectList) {
//					try {
//						if(resultMap.containsKey(customer.getCustomerName())){
//							 UserInfo userInfo = resultMap.get(customer.getCustomerName());
//							 customer.setLastLoginDate(userInfo.getLastLoginDate());
//							 customer.setExpireTime(userInfo.getExpirationTime());
//							 customer.setQqAcount(userInfo.getQqNum());
//							 customer.setPhone(userInfo.getMobile());
//							 customer.setLevel(userInfo.getLevel());
//							 if(null == userInfo.getMobile() || "".equals(userInfo.getMobile())){
//								 customer.setNotRegister(0);
//							 }else{
//								 customer.setNotRegister(1);
//							 }
//							 customer.setRestSmsNum(userInfo.getSmsNum());
//							 customer.setLoginCount(0);
//							 if(null!=userInfo.getAppkey()&&!"".equals(userInfo.getAppkey())){
//								 customer.setLoginCount(Integer.parseInt(userInfo.getAppkey())); 
//							 }
//							 i++;
//							 list.add(customer);
//							 if(i!=0&&i%100==0){
//								 log.info("保存用户list"+userLoginInfoList.size());
//								 customService.updateBatchById(list); 
//								 i=0;
//								 Thread.sleep(100);
//								 list= new ArrayList<Customer>();
//							 }
//						}
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				 }
//				 if(null!=list&&list.size()>0){
//					 customService.updateBatchById(list);
//				 }
//				 log.info("完毕");
//			 }
//		 }else{
//			 log.info("account表为空！"); 
//		 }
//		 
//		 
//		 if(null!=userInfoList&&userInfoList.size()>0){
//			 log.info("用户list"+userInfoList.size());
//			 UserLoginInfo  paramUserLoginInfo  = new  UserLoginInfo();
//			 paramUserLoginInfo.setStartTime(startDate);
//			 paramUserLoginInfo.setEndTime(endDate);
//			 List<UserLoginInfo> userLoginInfoList = userLoginInfoService.findUserLoginInfoList(paramUserLoginInfo);
//			 log.info("用户登录list"+userLoginInfoList.size());
//			 
//			 List<Customer>  list  =  new ArrayList<Customer>();
//			 int i = 0;
//			 Customer paramCustomer  = null;
//			 EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
//			 
//			 for (UserInfo userInfo : userInfoList) {
//				 try {
//					 if(null!=userInfo&&!"".equals(userInfo.getTaobaoUserNick())){
//						 Thread.sleep(100);
//						 paramCustomer = new Customer();
//						 paramCustomer.setCustomerName(userInfo.getTaobaoUserNick());
//						 ew.setEntity(paramCustomer);
//						 Customer customer = (Customer)customService.selectOne(ew);
//						 if(null!=customer){
//							 customer.setLastLoginDate(userInfo.getLastLoginDate());
//							 customer.setExpireTime(userInfo.getExpirationTime());
//							 customer.setQqAcount(userInfo.getQqNum());
//							 customer.setPhone(userInfo.getMobile());
//							 if(null == userInfo.getMobile() || "".equals(userInfo.getMobile())){
//								 customer.setNotRegister(0);
//							 }else{
//								 customer.setNotRegister(1);
//							 }
//							 customer.setRestSmsNum(userInfo.getSmsNum());
//							 customer.setLoginCount(0);
//							 for (UserLoginInfo userLoginInfo : userLoginInfoList) {
//								 if(userLoginInfo.getSellerNick().equals(userInfo.getTaobaoUserNick())){
//									 customer.setLoginCount(Integer.parseInt(userLoginInfo.getIpAddress())); 
//								 }
//							 }
//							 i++;
//							 list.add(customer);
//							 if(i!=0&&i%100==0){
//								 log.info("保存用户list"+userLoginInfoList.size());
//								 customService.updateBatchById(list); 
//								 i=0;
//								 list= new ArrayList<Customer>();
//							 }
//						 }else{
//							 log.info("查不到信息"+userInfo.getTaobaoUserNick());
//						 }
//					 }
//				} catch (Exception e) {
//					log.error("userInfo为空或昵称为空！"+e.getMessage());
//				}
//			 }
//			 
//			 if(null!=list&&list.size()>0){
//				 customService.updateBatchById(list);
//			 }
//			 log.info("完毕");
//		 }
//	}
	

	
}
