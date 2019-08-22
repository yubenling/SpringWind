package com.baomidou.springwind.schedule;

/**
 * 类名称：ScheduleComstants <br/>
 * 类描述：以后所有 定时任务标示全部转移至这里,进行分组和管理<br/>
 * 创建时间：2017年3月06日 下午4:19:16 <br/>
 * @author zlp
 * @version V1.0
 */
public class ScheduleConstants {
	
	
	
	/**
	 * 类名称：JobStatus <br/>
	 * 类描述：任务状态定义 <br/>
	 * 创建时间：2017年3月06日 下午4:19:16 <br/>
	 * @author zlp
	 * @version V1.0
	 */
	public static class JobStatus{
		//开启,不是1都不开启
		public static final String  OPEN = "1";
		//关闭
		public static final String  CLOSE = "0";
	}
	
	
	/**
	 * 类名称：JobName <br/>
	 * 类描述：JOB名称 <br/>
	 * 创建时间：2017年3月06日 下午4:19:16 <br/>
	 * @author zlp
	 * @version V1.0
	 */
	public static class JobName {
		//定时筛选录入的客户
		public static final String AUTO_FILTE_CUSTOM = "auto_filte_custom";
		
		public static final String AUTO_UPDATE_USERINFO = "auto_update_userinfo";
		
		public static final String AUTO_ADD_USERMONTHSURPLUSSMS = "auto_add_userMonthSurplusSms";
		
		public static final String AUTO_EXECUTEDELETESQL = "auto_executeDeleteSql";
	}
	
	/**
	 * 类名称：JobGroup <br/>
	 * 类描述：JOB组 <br/>
	 * 创建时间：2017年3月06日 下午4:19:16 <br/>
	 * @author zlp
	 * @version V1.0
	 */
	public static class JobGroup {
		//定时筛选录入的客户
		public static final String AUTO_FILTE_CUSTOM = "auto_filte_custom";
		
		public static final String AUTO_UPDATE_USERINFO = "auto_update_userinfo";
		
		public static final String AUTO_ADD_USERMONTHSURPLUSSMS = "auto_add_userMonthSurplusSms";
		
		public static final String AUTO_EXECUTEDELETESQL = "auto_executeDeleteSql";
		
	}
	
	
	/**
	 * 类名称：TriggerName <br/>
	 * 类描述：触发器名称 <br/>
	 * 创建时间：2017年3月06日 下午4:19:16 <br/>
	 * @author zlp
	 * @version V1.0
	 */
	public static class TriggerName {
		//定时筛选录入的客户
		public static final String AUTO_FILTE_CUSTOM = "auto_filte_custom";
		
		public static final String AUTO_UPDATE_USERINFO = "auto_update_userinfo";
		
		public static final String AUTO_ADD_USERMONTHSURPLUSSMS = "auto_add_userMonthSurplusSms";
		
		public static final String AUTO_EXECUTEDELETESQL = "auto_executeDeleteSql";
	}

	/**
	 * 类名称：TriggerGroup <br/>
	 * 类描述：触发器组 <br/>
	 * 创建时间：2017年3月06日 下午4:19:16 <br/>
	 * @author zlp
	 * @version V1.0
	 */
	public static class TriggerGroup {
		//定时筛选录入的客户
		public static final String AUTO_FILTE_CUSTOM = "auto_filte_custom";
		
		public static final String AUTO_UPDATE_USERINFO = "auto_update_userinfo";

		public static final String AUTO_ADD_USERMONTHSURPLUSSMS = "auto_add_userMonthSurplusSms";
		
		public static final String AUTO_EXECUTEDELETESQL = "auto_executeDeleteSql";
	}
	
	
}
