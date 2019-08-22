package com.baomidou.springwind.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.CrmGroup;
import com.baomidou.springwind.service.IGroupService;

/**
 * <p>
 * 群组管理相关操作
 * </p>
 *
 *
 * @Author hubin
 * @Date 2016-04-15
 */
@Controller
@RequestMapping("/group/manage")
public class GroupController extends BaseController {

	@Autowired
	private IGroupService groupService;

	@Permission("3000")
	@RequestMapping("/list")
	public String list( Model model ) {
		return "/crmmanage/group/list";
	}
	 
	@ResponseBody
	@Permission("3001")
	@RequestMapping("/getGroupList")
	public String getGroupList() {
		Page<CrmGroup> page = getPage();
		EntityWrapper<CrmGroup> ew = new EntityWrapper<CrmGroup>();
		ew.orderBy("createTime", false);
		return jsonPage(groupService.selectPage(page, ew));
	}
//	@ResponseBody
//	@Permission("3002")
//	@RequestMapping("/getUserGroupList")
//	public String getUserGroupList() {
//		Page<CrmGroup> page = getPage();
//		EntityWrapper<CrmGroup> ew = new EntityWrapper<CrmGroup>();
//		ew.orderBy("createTime", false);
//		return jsonPage(groupService.selectPage(page, ew));
//	}
//	
	

	@Permission("3001")
	@RequestMapping("/edit")
	public String edit( Model model, Long id ) {
		if ( id != null ) {
			model.addAttribute("group", groupService.selectById(id));
		}
		return "/crmmanage/group/edit";
	}
	
	
	@ResponseBody
	@Permission("3001")
	@RequestMapping("/editGroup")
	public String editGroup( CrmGroup crmGroup ) {
		boolean rlt = false;
		if ( crmGroup != null ) {
			if ( crmGroup.getId() != null ) {
				rlt = groupService.updateById(crmGroup);
			} else {
				crmGroup.setCreateTime(new Date());
				rlt = groupService.insert(crmGroup);
			}
		}
		return callbackSuccess(rlt);
	}
	

}
