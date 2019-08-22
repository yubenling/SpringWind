package com.baomidou.springwind.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Classify;
import com.baomidou.springwind.entity.Customer;
import com.baomidou.springwind.entity.CustomerClassify;
import com.baomidou.springwind.entity.CustomerPayRecord;
import com.baomidou.springwind.entity.MsgSendRecord;
import com.baomidou.springwind.entity.RechargeRecord;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserGroup;
import com.baomidou.springwind.entity.UserInfo;
import com.baomidou.springwind.entity.UserRecharge;
import com.baomidou.springwind.entity.VipUser;
import com.baomidou.springwind.service.IClassifyService;
import com.baomidou.springwind.service.ICustomPayRecordService;
import com.baomidou.springwind.service.ICustomService;
import com.baomidou.springwind.service.ICustomerClassifyService;
import com.baomidou.springwind.service.IMsgSendRecordService;
import com.baomidou.springwind.service.IRechargeRecordService;
import com.baomidou.springwind.service.IUserGroupService;
import com.baomidou.springwind.service.IUserInfoService;
import com.baomidou.springwind.service.IUserMonthSurplusSmsService;
import com.baomidou.springwind.service.IUserRechargeService;
import com.baomidou.springwind.service.IVipUserService;
import com.baomidou.springwind.util.DateUtils;
import com.baomidou.springwind.util.MyUtils;

/**
 * <p>
 * crm客户管理相关操作
 * </p>
 * @Author zlp
 * @Date 2017/3/15 15:03
 */
@Controller
@RequestMapping("/sys/manager")
public class CustomerManagerController extends BaseController {
	private static final Log log = LogFactory.getLog(CustomerManagerController.class);
	@Autowired
	private ICustomService customService;
	@Autowired
	private IUserGroupService userGroupService;
	@Autowired
	private ICustomPayRecordService customPayRecordService;
	@Resource
	protected IUserInfoService userInfoService;
	@Resource
	protected IRechargeRecordService rechargeRecordService;
	@Autowired
	protected IMsgSendRecordService msgSendRecordService;
	@Autowired
	protected IClassifyService classifyService;
	@Autowired
	protected ICustomerClassifyService customerClassifyService;
	@Autowired
	protected IUserRechargeService userRechargeService;
	@Autowired
	protected IUserMonthSurplusSmsService userMonthSurplusSmsService;
	@Autowired
	protected IVipUserService IVipUserService;
	
	@Permission("1001")
	@RequestMapping("/list")
	public String list() {
		return "/crmmanage/custom/list";
	}
	@Permission("1002")
	@RequestMapping("/oldCustomerlist")
	public String oldCustomerlist(Model model) {
		EntityWrapper<Classify> ew = new EntityWrapper<Classify>();
		ew.eq("owner", getSSOToken().getId()+"");
		List<Classify> classifyList = classifyService.selectList(ew);
		model.addAttribute("classifyList", classifyList);
		return "/crmmanage/custom/oldCustomerlist";
	}
	@Permission("1003")
	@RequestMapping("/customerlist")
	public String customerlist(Model model) {
		return "/crmmanage/custom/customerlist";
	}
	@Permission("1006")
	@RequestMapping("/queryCustomerList")
	public String queryCustomer() {
		return "/crmmanage/custom/queryCustomerList";
	}
	
	@Permission("1003")
	@RequestMapping("/customerPayedit")
	public String customerPayedit(Model model,String id,String type) {
		model.addAttribute("cid", id);
		model.addAttribute("type",type);
		return "/crmmanage/custom/customerPayedit";
	}
	@Permission("1003")
	@RequestMapping("/customerTruePayedit")
	public String customerTruePayedit(Model model,String id,String customerName,String restSmsNum) {
		model.addAttribute("cid", id);
		model.addAttribute("customerName",customerName);
		model.addAttribute("restSmsNum",restSmsNum);
		return "/crmmanage/custom/smsReplenish";
	}
	@Permission("1003")
	@RequestMapping("/smsTransfer")
	public String smsTransfer(Model model,String id,String customerName,String restSmsNum) {
		model.addAttribute("cid", id);
		model.addAttribute("customerName",customerName);
		model.addAttribute("restSmsNum",restSmsNum);
		return "/crmmanage/custom/smsTransfer";
	}
	@Permission("1003")
	@RequestMapping("/payRecordView")
	public String payRecordView(Model model,String id,String type) {
		session.setAttribute("customerId", id);
		return "/crmmanage/custom/payRecordlist";
	}
	@Permission("1003")
	@RequestMapping("/changeTheOwner")
	public String changeTheOwner(Model model,String id) {
		List<User> userList = userService.selectList(null);
		model.addAttribute("userList", userList);
		model.addAttribute("cid", id);
		Customer customer = customService.selectById(id);
		model.addAttribute("customer", customer);
		return "/crmmanage/custom/changeTheOwner";
	}
	
	@Permission("1005")
	@RequestMapping("/accountmanage")
	public String accountmanage() {
		return "/crmmanage/custom/accountmanage";
	}
	@Permission("1005")
	@RequestMapping("/accountedit")
	public String accountedit(Model model) {
		User user = userService.selectById(getSSOToken().getId());
		model.addAttribute("user", user);
		return "/crmmanage/custom/accountedit";
	}
	@Permission(action = Action.Skip)
	@RequestMapping("/shareList")
	public String shareList() {
		return "/crmmanage/custom/shareList";
	}
	
	@ResponseBody
	@Permission("1001")
	@RequestMapping("/getCustomlist")
	public String getCustomlist(Customer  customer ) {
		Page<Customer> page = getPage();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		if(null==customer){customer = new Customer();}
		getEntityWrapperByParam(customer, ew);
		customer.setOwner(getSSOToken().getId()+"");if(null==getSSOToken().getId()||"".equals(getSSOToken().getId())){
			return jsonPage(new Page());
		}
		customer.setIsBuy(0);
		customer.setOwnGroup(1l);
		ew.setEntity(customer);
		ew.orderBy("createTime", false);
		return jsonPage(customService.selectPage(page, ew));
	}
	
	@ResponseBody
	@Permission("1002")
	@RequestMapping("/getOldCustomlist")
	public String getOldCustomlist(Customer  customer,Integer sortName ,String classifyId) {
		Page<Customer> page = getPage();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		if(null != sortName){
			if(0 == sortName){
				ew.orderBy("lastLoginDate", false);
			}else if(1 == sortName){
				ew.orderBy("restSmsNum", false);
			}else if(2 == sortName){
				ew.orderBy("loginCount", false);
			}
		}
		
		boolean customerExist = true;
		if(null != classifyId && !"".equals(classifyId)){
			EntityWrapper<CustomerClassify> ewc = new EntityWrapper<CustomerClassify>();
			ewc.eq("gid", classifyId);
			List<CustomerClassify> list = customerClassifyService.selectList(ewc);
			if(null!= list && list.size()>0){
				ArrayList<Long> cidList = new ArrayList<Long>();
				for (CustomerClassify customerClassify : list) {
					cidList.add(customerClassify.getCid());
				}
				ew.in("id", cidList);
			}else{
				customerExist = false;
			}
		}
		
		
		if(customerExist){
			getEntityWrapperByParam(customer, ew);
			customer.setOwner(getSSOToken().getId()+"");
			if(null==getSSOToken().getId()||"".equals(getSSOToken().getId())){
				return jsonPage(new Page());
			}
			ew.setEntity(customer);
			ew.in("isBuy", "1,2,3");
			ew.orderBy("createTime", false);
			return jsonPage(customService.selectPage(page, ew));
		}else{
			return jsonPage(new Page<Customer>());
		}
		
	}
	@ResponseBody
	@Permission("1003")
	@RequestMapping("/searchAllCustomlist")
	public String searchAllCustomlist(Customer  customer,Integer sortName) {
		Page<Customer> page = getPage();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		getEntityWrapperByParam(customer, ew);
//		ew.orderBy("isOperate", true);
		if(null != sortName){
			if(0 == sortName){
				ew.orderBy("lastLoginDate", false);
			}else if(1 == sortName){
				ew.orderBy("restSmsNum", false);
			}
		}
		Page<Customer> selectPage = customService.selectPage(page, ew);
		if(null!=selectPage&&null!=selectPage.getRecords()&&selectPage.getRecords().size()>0){
			List<Customer> records = selectPage.getRecords();
			String ids="";
			for (Customer customer2 : records) {
				ids+=customer2.getOwner()+",";
			}
			ids= ids.substring(0, ids.length()-1);
			EntityWrapper<User> ew1 = new EntityWrapper<User>();
			ew1.in("id", ids);
			List<User> selectList = userService.selectList(ew1);
			for (User user : selectList) {
				for (Customer customer2 : records) {
					if(customer2.getOwner().equals(String.valueOf(user.getId()))){
						customer2.setOwner(user.getLoginName());
					}
				}
			}
		}
		return jsonPage(selectPage);
	}
	private void getEntityWrapperByParam(Customer customer,
			EntityWrapper<Customer> ew) {
		if(null==customer){customer = new Customer();}
		if(null!=customer.getCustomerName()&&!"".equals(customer.getCustomerName())){
			ew.like("customerName", customer.getCustomerName().trim());
		}else{
			customer.setCustomerName(null);
		}
		if(null!=customer.getOwner()&&!"".equals(customer.getOwner())){
			User user = userService.selectByLoginName(customer.getOwner().trim());
			if(null!=user){
				ew.like("owner", user.getId()+"");
			}
		}else{
			customer.setOwner(null);
		}
		if(null!=customer.getPhone()&&!"".equals(customer.getPhone().trim())){
			ew.like("phone", customer.getPhone());
		}else{
			customer.setPhone(null); 
		}
		if(null!=customer.getIsOperate()&&!"".equals(customer.getIsOperate())){
			if(1==customer.getIsOperate()){
				List<Integer>  list = new ArrayList<Integer>();
				list.add(1);
				list.add(2);
				ew.in("isOperate", list);
			}else{
				ew.in("isOperate", "0");
			}
		}else{
			customer.setIsOperate(null); 
		}
		if(null!=customer.getQqAcount()&&!"".equals(customer.getQqAcount().trim())){
			ew.like("qqAcount", customer.getQqAcount());
		}else{
			customer.setQqAcount(null);
		}
		if(!"".equals(customer.getStartExpireTime())&&null!=customer.getStartExpireTime()) {
			ew.ge("expireTime", customer.getStartExpireTime());
		}
		if(!"".equals(customer.getEndExpireTime())&&null!=customer.getEndExpireTime()){
			ew.le("expireTime",customer.getEndExpireTime());
		}
	}
	
	
	


	@Permission("1001")
	@RequestMapping("/editCustom")
	@ResponseBody
	public String editCustom(Customer customer ) {
		boolean rlt = false;
		if (customer != null ) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			Customer queryCustom  = new Customer();
			queryCustom.setCustomerName(customer.getCustomerName().trim());
			ew.setEntity(queryCustom);
			 int count = customService.selectCount(ew);
			 if(count==0) {
				customer.setIsBuy(0);
				Date date = new Date();
				customer.setCreateTime(date);
				customer.setUpdateTime(date);
				customer.setOpeater(getSSOToken().getId()+""); 
				customer.setDelayCount(0);
				customer.setOwner(getSSOToken().getId()+"");
				customer.setBeforeOwner(getSSOToken().getId()+"");
				customer.setExpireTime(DateUtils.addDays(date, 7));
				customer.setIsOperate(0);
				customer.setCustomerName(customer.getCustomerName().trim());
				customer.setOwnGroup(1l);
				rlt = customService.insert(customer);
			}
		}
		return callbackSuccess(rlt);
    }
	
	@Permission("1001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id ) {
        return "/crmmanage/custom/edit";
    }
	
	
	
	@ResponseBody
	@Permission("1001")
	@RequestMapping("/delayCustomer/{cid}")
	public String delayCustomer(@PathVariable Long cid) {
		boolean rlt = false;
		try {
			Map<String,Object>  map  = new HashMap<String, Object>();
			map.put("id", cid);
			map.put("owner", getSSOToken().getId());
			List<Customer> selectByMap = customService.selectByMap(map);
			if(null!=selectByMap&&selectByMap.size()>0){
				Customer customer = selectByMap.get(0);
				Date date = new Date();
				customer.setUpdateTime(date);
				customer.setDelayCount(customer.getDelayCount()+1);
				customer.setExpireTime(DateUtils.addDays(customer.getExpireTime(), 7));
				rlt= customService.updateById(customer);
			}
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	
	@ResponseBody
	@Permission("1003")
	@RequestMapping("/delayCustomerByAdmin/{cid}")
	public String delayCustomerByAdmin(@PathVariable Long cid) {
		boolean rlt = false;
		try {
			Map<String,Object>  map  = new HashMap<String, Object>();
			map.put("id", cid);
			List<Customer> selectByMap = customService.selectByMap(map);
			if(null!=selectByMap&&selectByMap.size()>0){
				Customer customer = selectByMap.get(0);
				Date date = new Date();
				customer.setUpdateTime(date);
				customer.setDelayCount(customer.getDelayCount()+1);
				customer.setExpireTime(DateUtils.addDays(customer.getExpireTime(), 7));
				rlt= customService.updateById(customer);
			}
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	
	
	@ResponseBody
	@Permission("1001")
	@RequestMapping("/searchCustomer")
	public String findCustomer(String  customerName ) {
		boolean rlt = true;
		try {
			if(null!=customerName&&!"".equals(customerName)){
				EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
				Customer  customer = new Customer();
				customer.setCustomerName(customerName.trim());
				ew.setEntity(customer);
				int count = customService.selectCount(ew);
				if(count>0) rlt=false;
			}
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
 
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/updateCustomerStatus", method = RequestMethod.POST)
	public String updateCustomerStatus(CustomerPayRecord customerPayRecord ) {
		boolean rlt = false;
		try {
			customerPayRecord.setOpeater(getSSOToken().getId()+"");
			rlt= customService.updateAndSaveReord(customerPayRecord);
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/updateOperateStatus", method = RequestMethod.POST)
	public String updateOperateStatus(String id ,String status ) {
		boolean rlt = false;
		try {
			if(null!=id&&!"".equals(id)){
				Customer selectById = customService.selectById(id);
				selectById.setOpeater(getSSOToken().getId()+"");
				selectById.setIsOperate(2);
				rlt= customService.updateById(selectById);
			}
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/changeTheOwnerOperate", method = RequestMethod.POST)
	public String changeTheOwnerOperate(Customer customer ) {
		boolean rlt = false;
		try {
			if(null!=customer&&!"".equals(customer.getId())){
				Customer custome = customService.selectById(customer.getId());
				if(null!=customer.getCustomerName()&&!"".equals(customer.getCustomerName())){
					custome.setCustomerName(customer.getCustomerName());
				}
				custome.setOwnGroup(1l);
				custome.setOwner(customer.getOwner());
				custome.setOpeater(getSSOToken().getId()+"");
				rlt= customService.updateById(custome);
			}
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	
	@ResponseBody
	@Permission("1003")
	@RequestMapping("/viewPayRecordList")
	public String viewPayRecordList() {
		Page<CustomerPayRecord> page = getPage();
		String customerId = (String)session.getAttribute("customerId");
		session.removeAttribute("customerId");
		EntityWrapper<CustomerPayRecord> ew = new EntityWrapper<CustomerPayRecord>();
		CustomerPayRecord customerPayRecord = new  CustomerPayRecord();
		customerPayRecord.setCustomerId(customerId);
		ew.setEntity(customerPayRecord);
		ew.orderBy("createTime", false);
		Page<CustomerPayRecord> selectPage = customPayRecordService.selectPage(page, ew);
		return jsonPage(selectPage);
	}
	
	
	@Permission(action = Action.Skip)
	@ResponseBody
	@RequestMapping("/getShareList")
	public String getShareList(Customer  customer,Integer sortName ) {
		Page<Customer> page = getPage();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		List<Object> list = new ArrayList<Object>();list.add("2");
		if(null != sortName){
			if(0 == sortName){
				ew.orderBy("lastLoginDate", false);
			}else if(1 == sortName){
				ew.orderBy("restSmsNum", false);
			}
		}
		
		getEntityWrapperByParam(customer, ew);
		if(null==getSSOToken().getId()||"".equals(getSSOToken().getId())){
			return jsonPage(new Page());
		}
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("uid", getSSOToken().getId());
	    List<UserGroup> userGroupList = userGroupService.selectByMap(map);
		
		if(null!=userGroupList&&userGroupList.size()>0){
			list.add(userGroupList.get(0).getGid());
		} 
		customer.setIsBuy(0);
		ew.setEntity(customer);
		ew.in("ownGroup", list);
		ew.orderBy("createTime", false);
		return jsonPage(customService.selectPage(page, ew));
	}
	
	
	@ResponseBody
	@Permission("1005")
	@RequestMapping("/accountmanageList")
	public String accountmanageList() {
		Page<User> page = getPage();
		EntityWrapper<User> ew = new EntityWrapper<User>();
		if(null==getSSOToken().getId()||"".equals(getSSOToken().getId())){
			return jsonPage(new Page());
		}
		User user = new User();
		user.setId(getSSOToken().getId());
		ew.setEntity(user);
		ew.orderBy("crTime", false);
		Page<User> selectPage = userService.selectPage(page, ew);
		return jsonPage(selectPage);
	}
	
	@ResponseBody
	@Permission("1005")
	@RequestMapping(value="/updateAccountInfo", method = RequestMethod.POST)
	public String updateAccountInfo(User user ) {
		boolean rlt = false;
		try {
			User existUser = userService.selectById(getSSOToken().getId());
			existUser.setPassword(user.getPassword());
			existUser.setEmail(user.getEmail());
			rlt = userService.updateById(existUser);
		} catch (Exception e) {
			return  "false"; 
		}
		return booleanToString(rlt);
	}
	
	
	
	@ResponseBody
	@Permission("1006")
	@RequestMapping("/queryAllCustomlist")
	public String queryAllCustomlist(Customer  customer,String type ) {
		if(null!=customer){
			Page<Customer> page = getPage();
			int flag = 1;
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			Customer  param  = new  Customer();
			if(null!=customer.getCustomerName()&&!"".equals(customer.getCustomerName())){
				param.setCustomerName(customer.getCustomerName().trim());
				flag=2;
			}
			if(null!=customer.getPhone()&&!"".equals(customer.getPhone())){
				param.setPhone(customer.getPhone().trim());
				flag=2;
			}
			if(null!=customer.getQqAcount()&&!"".equals(customer.getQqAcount())){
				param.setQqAcount(customer.getQqAcount().trim());
				flag=2;
			}
			if(flag==2){
				ew.orderBy("createTime", false);
				ew.setEntity(param);
				Page<Customer> selectPage = customService.selectPage(page, ew);
				if(null!=selectPage&&null!=selectPage.getRecords()&&selectPage.getRecords().size()>0){
					List<Customer> records = selectPage.getRecords();
					String ids="";
					for (Customer customer2 : records) {
						ids+=customer2.getOwner()+",";
					}
					ids= ids.substring(0, ids.length()-1);
					EntityWrapper<User> ew1 = new EntityWrapper<User>();
					ew1.in("id", ids);
					List<User> selectList = userService.selectList(ew1);
					for (User user : selectList) {
						for (Customer customer2 : records) {
							if(customer2.getOwner().equals(String.valueOf(user.getId()))){
								customer2.setOwner(user.getLoginName());
							}
						}
					}
					return jsonPage(selectPage);
				}
				
			}
		}
		return jsonPage(new Page());
	}
	
	/**
	 *	补短信
	 */
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/updateUserSmsNum", method = RequestMethod.POST)
	public String updateUserSmsNum(String customerName,
			String rechargeSmsNum,String loginName) {
		String rlt = "执行失败";
		
		int rechargeNum = 0;
		boolean errorNum = false;
		try {
			rechargeNum = Integer.parseInt(rechargeSmsNum);
		} catch (Exception e) {
			rechargeNum = 0;
			errorNum = true;
		}
		if(rechargeNum != 0){
			UserInfo userInfo = userInfoService.getUserByUserId(customerName);
			if(null !=userInfo){
				//更新userInfo表短信
				try {
					userInfoService.updateUserInfo(rechargeNum, customerName);
					if(rechargeNum>0){
						rlt = "充值成功";
					}else{
						rlt = "扣除成功";
					}
					Integer smsCount = userInfoService.getSMSCount(customerName);
					RechargeRecord rechargeRecord = new RechargeRecord();
					rechargeRecord.setLoginName(loginName);
					rechargeRecord.setCreateTime(new Date());
					rechargeRecord.setRechargeUserName(customerName);
					rechargeRecord.setRechargeNum(rechargeNum);
					rechargeRecord.setRechargeAgoSmsNum(userInfo.getSmsNum());
					rechargeRecord.setRechargeLaterSmsNum(smsCount);
					rechargeRecord.setType(0);
					//保存补短信日志
					rechargeRecordService.insert(rechargeRecord);
					//修改oa当前用户的短信条数
					customService.updateByUserId(smsCount, customerName);
					System.out.println("************************************短信充值成功****"
									+ "充值前："+userInfo.getSmsNum()+"****"
									+ "充值条数："+rechargeNum+"****"
									+ "充值后："+smsCount+"**************************");
				} catch (Exception e) {
					System.err.println("************************************短信充值失败"+e.getMessage());
				}
			}else{
				rlt = "用户不存在";
			}
		}else{
			rlt = "充值短信不能为0";
		}
		
		if(errorNum){
			rlt = "短信数量为数字";
		}
		return rlt;
	}
	
	
	/**
	 *	转短信
	 */
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/smsTransfer", method = RequestMethod.POST)
	public String smsTransfer(String loginName,
			String outUserName,String rechargeUserName,String rechargeSmsNum) {
		String rlt = "转账失败";
		int rechargeNum = 0;
		boolean errorNum = false;
		try {
			rechargeNum = Integer.parseInt(rechargeSmsNum);
		} catch (Exception e) {
			rechargeNum = 0;
			errorNum = true;
		}
		
		if(rechargeNum > 0){
			UserInfo userO = userInfoService.getUserByUserId(outUserName);
			UserInfo userR = userInfoService.getUserByUserId(rechargeUserName);
			if(null != userO && null != userR){
				Integer outSmsNum = userInfoService.getSMSCount(outUserName);
				if(null != outSmsNum && outSmsNum>=rechargeNum){
					try {
						userInfoService.updateSmsToTransfer(outUserName,rechargeUserName,rechargeNum);
						rlt = "转账成功";
						Integer outSmsCount = userInfoService.getSMSCount(outUserName);
						Integer rechargeSmsCount = userInfoService.getSMSCount(rechargeUserName);
						RechargeRecord rechargeRecord = new RechargeRecord();
						rechargeRecord.setLoginName(loginName);
						rechargeRecord.setCreateTime(new Date());
						rechargeRecord.setDeductUserName(outUserName);
						rechargeRecord.setRechargeUserName(rechargeUserName);
						rechargeRecord.setRechargeNum(rechargeNum);
						rechargeRecord.setRechargeAgoSmsNum(userR.getSmsNum());
						rechargeRecord.setRechargeLaterSmsNum(rechargeSmsCount);
						rechargeRecord.setDeductAgoSmsNum(userO.getSmsNum());
						rechargeRecord.setDeductLaterSmsNum(outSmsCount);
						rechargeRecord.setType(1);
						//保存补短信日志
						rechargeRecordService.insert(rechargeRecord);
						//修改oa当前用户的扣除短信条数
						customService.updateByUserId(outSmsCount, outUserName);
						//修改oa当前用户的充值短信条数
						customService.updateByUserId(rechargeSmsCount, rechargeUserName);
						
						System.out.println("********************短信转账成功****从："+outUserName+"==转"+rechargeNum+"条==>"+rechargeUserName);
						System.out.println("********************转出店铺："+outUserName+"：转出前__"+userO.getSmsNum()+"、****转出后__"+outSmsCount);
						System.out.println("********************转入店铺："+rechargeUserName+"：转入前__"+userR.getSmsNum()+"、****转入后__"+rechargeSmsCount);
					} catch (Exception e) {
						System.err.println("************************************短信转账失败"+e.getMessage());
					}
				}else{
					rlt = "转出短信不能大于剩余短信";
				}
			}else{
				rlt = "用户不存在";
			}
		}else{
			rlt = "转出短信不能小于0";
		}
		
		if(errorNum){
			rlt = "短信数量为数字";
		}
		return rlt;
	}
	
	
	/***********短信发送记录***********/
	@Permission("1008")
	@RequestMapping("/smsSendRecord")
	public String smsSendRecord() {
		return "/crmmanage/sms/smsSendRecord";
	}
	/**
	 * 短信发送总记录
	 */
	@ResponseBody
	@Permission("1008")
	@RequestMapping("/querySmsSendRecordList")
	public String querySmsSendRecordList(MsgSendRecord msgSendRecord) {
			Page<MsgSendRecord> page = getPage();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", msgSendRecord.getUserId());
			map.put("templateContent", msgSendRecord.getTemplateContent());
			map.put("type", msgSendRecord.getType());
			map.put("currentPage", (page.getCurrent() - 1) * page.getSize());
			map.put("pageSize", page.getSize());
			Page<MsgSendRecord> selectPage = msgSendRecordService.findMsgSendRecordPage(map);
			return jsonPage(selectPage);
	}
	
	
	
	
	/***********用户分类**********/
	@Permission("1007")
	@RequestMapping("/classify")
	public String classify() {
		return "/crmmanage/classify/classify";
	}
	
	@ResponseBody
	@Permission("1007")
	@RequestMapping("/getClassifyList")
	public String getClassifyList() {
		Page<Classify> page = getPage();
		EntityWrapper<Classify> ew = new EntityWrapper<Classify>();
		ew.orderBy("createTime", false);
		ew.eq("owner", getSSOToken().getId()+"");
		return jsonPage(classifyService.selectPage(page, ew));
	}
	
	@Permission("1007")
	@RequestMapping("/editClassifyShow")
	public String editClassifyShow( Model model, Long id ) {
		if ( id != null ) {
			model.addAttribute("classify", classifyService.selectById(id));
		}
		return "/crmmanage/classify/editClassify";
	}
	
	@ResponseBody
	@Permission("1007")
	@RequestMapping("/editClassify")
	public String editClassify( Classify classify ) {
		boolean rlt = false;
		try {
			if ( classify != null ) {
				if ( classify.getId() != null ) {
					rlt = classifyService.updateById(classify);
				} else {
					classify.setCreateTime(new Date());
					classify.setOwner(getSSOToken().getId()+"");
					rlt = classifyService.insert(classify);
				}
			}
		} catch (Exception e) {
		}
		return callbackSuccess(rlt);
	}
	
	
	
	/**********编辑客户名称************/
	@Permission("1002")
	@RequestMapping("/editCustomerName")
	public String editCustomerName(Model model,String id,String customerName) {
		model.addAttribute("cid", id);
		model.addAttribute("customerName",customerName);
		return "/crmmanage/custom/editCustomerName";
	}
	
	@Permission("1002")
	@RequestMapping("/editCustomName")
	@ResponseBody
	public String editCustomName(Customer customer ) {
		boolean rlt = false;
		try {
			if (customer != null && null != customer.getId()
					&& null != customer.getCustomerName()
					&& !"".equals(customer.getCustomerName())) {
				Customer editCustom  = new Customer();
				editCustom.setId(customer.getId());
				editCustom.setCustomerName(customer.getCustomerName());
				editCustom.setUpdateTime(new Date());
				rlt = customService.updateById(editCustom);
			}
		} catch (Exception e) {
		}
		return callbackSuccess(rlt);
    }
	
	/********添加客户分组************/
	@Permission("1002")
	@RequestMapping("/customerClassify")
	public String customerClassify(Model model,String id,String customerName) {
		EntityWrapper<Classify> ew = new EntityWrapper<Classify>();
		ew.eq("owner", getSSOToken().getId()+"");
		List<Classify> classifyList = classifyService.selectList(ew);
		
		EntityWrapper<CustomerClassify> ewc = new EntityWrapper<CustomerClassify>();
		ewc.eq("cid", id);
		CustomerClassify customerClassify = customerClassifyService.selectOne(ewc);
		
		model.addAttribute("classifyList", classifyList);
		model.addAttribute("customerClassify",customerClassify);
		model.addAttribute("cid", id);
		model.addAttribute("customerName",customerName);
		return "/crmmanage/classify/editCustomerClassify";
	}
	
	@Permission("1002")
	@RequestMapping("/editCustomerClassify")
	@ResponseBody
	public String editCustomerClassify(CustomerClassify customerClassify ) {
		boolean rlt = false;
		try {
			if (customerClassify != null && null != customerClassify.getCid()
					&& null != customerClassify.getGid()) {
				EntityWrapper<CustomerClassify> ew = new EntityWrapper<CustomerClassify>();
				ew.eq("cid", customerClassify.getCid());
				int count = customerClassifyService.selectCount(ew);
				if(count>0){
					CustomerClassify entity = new CustomerClassify();
					entity.setGid(customerClassify.getGid());
					rlt = customerClassifyService.update(entity, ew);
				}else{
					rlt = customerClassifyService.insert(customerClassify);
				}
			}
		} catch (Exception e) {
		}
		return callbackSuccess(rlt);
    }
	
	
	
	/**客户充值记录汇总*****************/
	@Permission("1009")
	@RequestMapping("/userRechargeCollect")
	public String userRechargeCollect(Model model) {
		return "/crmmanage/recharge/userRechargeCollect";
	}
	@Permission("1009")
	@RequestMapping("/queryUserRechargeCollect")
	@ResponseBody
	public String queryUserRechargeCollect(UserRecharge userRecharge ) {
		Page<UserRecharge> page = getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNick", userRecharge.getUserNick().trim());
		map.put("currentPage", (page.getCurrent() - 1) * page.getSize());
		map.put("pageSize", page.getSize());
		Page<UserRecharge> selectPage = userRechargeService.findUserRecharge(map);
		List<UserRecharge> records = selectPage.getRecords();
		for (UserRecharge s : records) {
		 	Map<String, Object> numMap = new HashMap<String, Object>();
		 	numMap.put("userNick", s.getUserNick());
		 	numMap.put("rechargeDate", s.getRechargeDate());
			Integer deductNum = rechargeRecordService.findUserDeductNum(numMap);
			Integer presentNum = rechargeRecordService.findUserPresentNum(numMap);
			Integer shiftToNum = rechargeRecordService.findUserShiftToNum(numMap);
			Integer monthStartNum = userMonthSurplusSmsService.findMonthStartNum(numMap);
			Integer monthEndNum = userMonthSurplusSmsService.findMonthStartNum(MyUtils.calculateMonth(numMap));
			s.setPresentNum(presentNum);
			s.setDeductNum(deductNum);
			s.setShiftToNum(shiftToNum);
			if (monthStartNum != null &&  monthEndNum !=null) {
				s.setSurplusNum(monthEndNum);
				//rechargeNUm+presentNum+shiftToNum-deductNum-surplusNum
				s.setSendNum(monthStartNum+s.getRechargeNUm()+s.getPresentNum()+s.getShiftToNum()-s.getDeductNum()-s.getSurplusNum());
			}
			Double averagePrice = MyUtils.calculateAvg(s.getRechargeNUm(),s.getPresentNum(),s.getRechargePrice());
			s.setAveragePrice(averagePrice);
			
		}
		selectPage.setRecords(records);
		return jsonPage(selectPage);
    }
	
	
	/**客户充值记录查询*****************/
	@Permission("1010")
	@RequestMapping("/userRechargeRecharge")
	public String userRechargeRecharge(Model model) {
		return "/crmmanage/recharge/userRechargeRecharge";
	}
	@Permission("1010")
	@RequestMapping("/queryUserRechargeRecharge")
	@ResponseBody
	public String queryUserRechargeRecharge(UserRecharge userRecharge ) {
		Page<UserRecharge> page = getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNick", userRecharge.getUserNick().trim());
		map.put("currentPage", (page.getCurrent() - 1) * page.getSize());
		map.put("pageSize", page.getSize());
		Page<UserRecharge> selectPage = userRechargeService.findUserRechargeList(map);
		return jsonPage(selectPage);
    }
	
	
	/**vip小客户记录操作*****************/
	@Permission("1011")
	@RequestMapping("/vipUser")
	public String vipUser(Model model) {
		return "/crmmanage/vipUser/vipUser";
	}
	@Permission("1011")
	@RequestMapping("/editVipUser")
	public String editVipUser(Model model) {
		return "/crmmanage/vipUser/editVipUser";
	}
	@Permission("1011")
	@RequestMapping("/queryVipUser")
	@ResponseBody
	public String queryVipUser(String vipUserNick ) {
		Page<UserRecharge> page = getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vipUserNick", vipUserNick.trim());
		map.put("currentPage", (page.getCurrent() - 1) * page.getSize());
		map.put("pageSize", page.getSize());
		Page<VipUser> selectPage = IVipUserService.findVipUserList(map);
		return jsonPage(selectPage);
    }
	@Permission("1011")
	@RequestMapping("/insertVipUser")
	@ResponseBody
	public String insertVipUser(String vipUserNick,String loginName) {
		Integer count = IVipUserService.findVipUserCount(vipUserNick.trim());
		Long uid = IVipUserService.findVipUserUid(vipUserNick.trim());
		if(null != count && count > 0){
			return "2";
		}else if(null == uid){
			return "3";
		}else{
			VipUser vipUser = new VipUser();
			vipUser.setUid(uid);
			vipUser.setCreatedDate(new Date());
			vipUser.setVipUserNick(vipUserNick.trim());
			vipUser.setUserId(loginName);
			String insert = IVipUserService.insertVipUser(vipUser);
			return insert;
		}
    }
	@Permission("1011")
	@RequestMapping("/delVipUser/{id}")
	@ResponseBody
	public String delVipUser(@PathVariable String id ) {
		String delete = IVipUserService.deleteVipUser(id);
		return delete;
    }
	
	
	/**
	 * 查询所有用户
	 */
	@Permission("1003")
	@RequestMapping("/batchChangeTheOwner")
	public String batchChangeTheOwner(Model model) {
		List<User> userList = userService.selectList(null);
		model.addAttribute("userList", userList);
		return "/crmmanage/custom/batchChangeTheOwner";
	}
	
	/**
	 *	将客户批量更改所属人
	 */
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/batchChangeTheOwnerOperate", method = RequestMethod.POST)
	public String batchChangeTheOwnerOperate(String outId,String enterId) {
		Integer rlt = 0;
		if (null != outId && !"".equals(outId) && null != enterId
				&& !"".equals(enterId) && !outId.equals(enterId)) {
			rlt = customService.batchChangeTheOwnerOperate(outId,enterId);
		}
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("1002")
	@RequestMapping(value="/updateCustoms", method = RequestMethod.POST)
	public String updateCustoms(String[] custIds) {
		boolean rlt = true;
		String ids = "";
		if(null != custIds && custIds.length>0){
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.in("id", custIds);
			List<Customer> customers = customService.selectList(ew);
			if(null != customers && customers.size()>0){
				for (Customer customer : customers) {
					UserInfo user = userInfoService.findUserInfoByCustomer(customer.getCustomerName().trim());
					if(null != user){
						ids+=customer.getId()+",";
						Customer cust = new Customer();
						cust.setId(customer.getId());
						cust.setRestSmsNum(user.getSmsNum());
						cust.setLastLoginDate(user.getLastLoginDate());
						cust.setExpireTime(user.getExpirationTime());
						cust.setLevel(user.getLevel());
						 if(null!=user.getQqNum()&& !"".equals(user.getQqNum())){
							 cust.setQqAcount(user.getQqNum());
						 }
						 if(null!=user.getMobile()&& !"".equals(user.getMobile())){
							 cust.setPhone(user.getMobile());
							 cust.setNotRegister(1);
						 }else{
							 cust.setNotRegister(0);
						 }
						customService.updateById(cust);
					}
				}
			}
		}
		log.info("~~~~~~~~~~本次更新用户："+ids);
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("1003")
	@RequestMapping(value="/updateCustomsId", method = RequestMethod.POST)
	public String updateCustomsId(String[] custIds) {
		boolean rlt = true;
		String ids = "";
		if(null != custIds && custIds.length>0){
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.in("id", custIds);
			List<Customer> customers = customService.selectList(ew);
			if(null != customers && customers.size()>0){
				for (Customer customer : customers) {
					UserInfo user = userInfoService.findUserInfoByCustomer(customer.getCustomerName().trim());
					if(null != user){
						ids+=customer.getId()+",";
						Customer cust = new Customer();
						cust.setId(customer.getId());
						cust.setRestSmsNum(user.getSmsNum());
						cust.setLastLoginDate(user.getLastLoginDate());
						cust.setExpireTime(user.getExpirationTime());
						cust.setLevel(user.getLevel());
						 if(null!=user.getQqNum()&& !"".equals(user.getQqNum())){
							 cust.setQqAcount(user.getQqNum());
						 }
						 if(null!=user.getMobile()&& !"".equals(user.getMobile())){
							 cust.setPhone(user.getMobile());
							 cust.setNotRegister(1);
						 }else{
							 cust.setNotRegister(0);
						 }
						customService.updateById(cust);
					}
				}
			}
		}
		log.info("~~~~~~~~~~本次更新用户："+ids);
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("1002")
	@RequestMapping(value="/deleteCustom", method = RequestMethod.POST)
	public String deleteCustom(String id) {
		boolean rlt = customService.deleteById(id);
		return callbackSuccess(rlt);
	}
	
	
	
	
	
	@Permission("1012")
	@RequestMapping("/userSupplementSms")
	public String userSupplementSms(Model model) {
		return "/crmmanage/recharge/userSupplementSms";
	}
	
	@ResponseBody
	@Permission("1012")
	@RequestMapping("/queryUserSupplementSms")
	public String queryUserSupplementSms(RechargeRecord rechargeRecord) {
		Page<RechargeRecord> page = getPage();
		EntityWrapper<RechargeRecord> ew = new EntityWrapper<RechargeRecord>();
		ew.orderBy("createTime", false);
		if(null != rechargeRecord.getDeductUserName() && !"".equals(rechargeRecord.getDeductUserName())){
			ew.eq("deductUserName", rechargeRecord.getDeductUserName().trim());
		}
		if(null != rechargeRecord.getRechargeUserName() && !"".equals(rechargeRecord.getRechargeUserName())){
			ew.eq("rechargeUserName", rechargeRecord.getRechargeUserName().trim());
		}
		Page<RechargeRecord> selectPage = rechargeRecordService.selectPage(page, ew);
		return jsonPage(selectPage);
	}
	
//	@ResponseBody
//	@Permission("1003")
//	@RequestMapping(value="/test", method = RequestMethod.GET)
//	public String test( ) {
//		boolean rlt = false;
//		try {
//			   List<Customer> selectList = customService.selectList(null);
//			   CustomerPayRecord  customerPayRecord  =null;
//			   for (Customer customer2 : selectList) {
//				   customerPayRecord  =new CustomerPayRecord();
//				   customerPayRecord.setOpeater(1+"");
//				   customerPayRecord.setCustomerId(customer2.getId()+"");
//				   customerPayRecord.setPayTimeStr("2017-03-30");
//				   customerPayRecord.setBuyType(1);
//				   customerPayRecord.setBuyMoney(new BigDecimal(65)); 
//				   customerPayRecord.setPayType("购买");
//				   rlt= customService.updateAndSaveReord(customerPayRecord);
//			   }
//		} catch (Exception e) {
//			return  "false"; 
//		}
//		return booleanToString(rlt);
//	}
}
