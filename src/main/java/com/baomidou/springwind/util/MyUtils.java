package com.baomidou.springwind.util;

import java.text.DecimalFormat;
import java.util.Map;

public class MyUtils {
	
	private static DecimalFormat df = new DecimalFormat("0.000");
	
	/**计算评价值*/
	public static Double calculateAvg(Integer rechargeNUm, Integer presentNum,
			double rechargePrice) {
		try {
			Integer num =  rechargeNUm+presentNum;
			double sumNum = Double.parseDouble(num+"");
			double averagePrice = rechargePrice/sumNum;
			return Double.parseDouble(df.format(averagePrice));
		} catch (Exception e) {
		}
		return null;
	}
	
	
	/**计算当月，月末剩余数量查询日期*/
	public static Map<String, Object> calculateMonth(Map<String, Object> numMap) {
		try {
			String data = (String) numMap.get("rechargeDate");
			String[] split = data.split("-");
			Integer year = Integer.parseInt(split[0]);
			Integer day = Integer.parseInt(split[1]);
			day++;
			if(day > 12){
				year++;
				day = 1;
			}
			String MonthEndDate = year+"-"+day;
			numMap.put("rechargeDate", MonthEndDate);
		} catch (Exception e) {
			return null;
		}
		return numMap;
	}
}
