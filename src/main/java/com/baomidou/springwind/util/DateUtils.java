/*
 * create on 2008-4-1
 * Copy right (2008)
 * HNA System All rights reserved
 */
package com.baomidou.springwind.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @ClassName: DateUtils <br/>
 * @Description: DateUtils <br/>
 * @CreateDate: 2016年4月27日 下午4:25:17 <br/>
 * @author zlp
 * @version V1.0
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{
	   /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
	public static String dateToString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date).trim();

	}

	public static String dateToString(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();

	}
	public static Date formateDate(Date date, String format) {
		try {
			if (date == null)
				date=new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			return null;
		}
	}
	public static String dateToString(Date date, String pattern, Locale locale) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
		return formatter.format(date).toUpperCase().trim();
	}
	
	public static Date StringToDate(String date,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date obj = null;
		try {
			obj=formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public static Date StringTODateTime(String CST_time) {// add by zhouhui

		Date obj = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);

			obj = sdf.parse(CST_time);// 构造Date对象

		} catch (Exception e) {

			e.printStackTrace();

		}

		return obj;

	}
	
	public static boolean isDayBefore(Date date, int day) {
		Calendar target = Calendar.getInstance();
		target.setTime(date);
		target.add(Calendar.DAY_OF_MONTH, -day);
		target.set(Calendar.HOUR_OF_DAY, target.getActualMaximum(Calendar.HOUR_OF_DAY));
		target.set(Calendar.MINUTE, target.getActualMaximum(Calendar.MINUTE));
		target.set(Calendar.SECOND, target.getActualMaximum(Calendar.SECOND));
		target.set(Calendar.MILLISECOND, target.getActualMaximum(Calendar.MILLISECOND));
		
		Calendar now = Calendar.getInstance();
		return now.before(target);
	}
	
	/**
	 * Compare two flight times up to the minute.
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isSameFlightTime(Date time1, Date time2) {
		return (compare(time1, time2) == 0);
	}
	
	/**
	 * Compare two dates up to the minute, ignoring seconds and milliseconds.
	 * 
	 * @param date1
	 * @param date2
	 * @return the value 0 if date1 and date2 are equal; a value less than 0 if date1 is before date2; and a value greater than 0
	 *         if date1 is after date2.
	 */
	public static int compare(Date date1, Date date2) {
		return compare(date1, date2, Calendar.MINUTE);
	}
	
	/**
	 * Compare two dates up to the specified field.
	 * 
	 * @param date1
	 * @param date2
	 * @param field
	 *            the field from Calendar or SEMI_MONTH
	 * @return the value 0 if date1 and date2 are equal; a value less than 0 if date1 is before date2; and a value greater than 0
	 *         if date1 is after date2.
	 */
	public static int compare(Date date1, Date date2, int field) {
		Date d1 = org.apache.commons.lang.time.DateUtils.truncate(date1, field);
		Date d2 = org.apache.commons.lang.time.DateUtils.truncate(date2, field);

		return d1.compareTo(d2);
	}
	
	/**
	 * 一个非常傻的方法


	 * 将Date转换成String然后转回来，为了不让oracle出错，不知道是Hibernate的问题还是oracle的问题


	 * 总之能解决就是好方法
	 * @param date
	 * @return
	 */
	public static Date convertOracleDate(Date date)
	{
		
		DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			return dateTimeFormatter.parse(dateTimeFormatter.format(date));
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	
	/**
	 * 增加小时
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addHour(Date date,int add){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, add);
		return cal.getTime();
		
		
	}
	
	/**
	 * Set the time of the given date to the maximum time of the day.
	 * Ex: 29-10-2008 15:23:12 will be set to 29-10-2008 23:59:59 
	 * @param date
	 * @return
	 */
	public static Date setMaximumTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 * Set the time of the given date to the minimum time of the day.
	 * Ex: 29-10-2008 15:23:12 will be set to 29-10-2008 00:00:00 
	 * @param date
	 * @return
	 */
	public static Date setMinimumTimeOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 *@param date
	 *@return
	 *
	 *@author huang_yu
	 *@description 
	 *@date 2008-9-20
	 */
	public static Date stringToDate(String date) {
		if (date == null)
			return null;

		Calendar cd = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(date, "-/ :");
		if (token.hasMoreTokens()) {
			cd.set(Calendar.YEAR, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.YEAR, 1970);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MONTH, Integer.parseInt(token.nextToken()) - 1);
		} else {
			cd.set(Calendar.MONTH, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.HOUR_OF_DAY, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MINUTE, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MINUTE, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.SECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.SECOND, 0);
		}
		if (token.hasMoreTokens()) {
			cd.set(Calendar.MILLISECOND, Integer.parseInt(token.nextToken()));
		} else {
			cd.set(Calendar.MILLISECOND, 0);
		}

		return cd.getTime();
	}
	
	public static Calendar convertToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * Returns the difference between the two dates in days.
	 * 
	 * @param date1
	 *            the first date
	 * @param date2
	 *            the second date
	 * @return the number of days between the two dates.
	 */
	public static int getDayDifference(Date date1, Date date2) {
		Calendar gc1 = Calendar.getInstance();
		Calendar gc2 = Calendar.getInstance();
		gc1.setTime(date1);
		gc2.setTime(date2);

		long millis = gc2.getTimeInMillis() - gc1.getTimeInMillis();
		return Math.abs((int) (millis / 1000 / 24 / 60 / 60));
	}
	
	/**
	 * Returns the difference between the two dates in days.
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static int getDayDifference(Calendar c1, Calendar c2) {
		return getDayDifference(c1.getTime(), c2.getTime());
	}
	
	public static String prettyPrint(long duration) {
//		DateFormat df = new SimpleDateFormat("D 'days', HH 'hours', mm 'mins,' ss 'seconds'");
//		df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//		return df.format(new Date(duration));
		
 
		long aSecondInMilli = 1000;
		long aMinuteInMilli = 60 * aSecondInMilli;
		long anHourInMilli = 60 * aMinuteInMilli;
		long aDayInMilli = 24 * anHourInMilli;
		
		// Ceil the duration to nearest second
		BigDecimal bd = new BigDecimal(String.valueOf(duration));
		bd = bd.divide(new BigDecimal("1000"));
		bd = bd.setScale(0, BigDecimal.ROUND_CEILING);
		bd = bd.multiply(new BigDecimal("1000"));
		long roundedDuration = bd.longValue();

		String days = Integer.toString((int) (roundedDuration / aDayInMilli));
		String hours = Integer.toString((int) ((roundedDuration % aDayInMilli) / anHourInMilli));
		String minutes = Integer
				.toString((int) ((roundedDuration % aDayInMilli % anHourInMilli) / aMinuteInMilli));
		String seconds = Integer
				.toString((int) ((roundedDuration % aDayInMilli % anHourInMilli % aMinuteInMilli) / aSecondInMilli));
		
//		for (int i = 0; i < 2; i++) {
//			if (seconds.length() < 2) {
//				seconds = "0" + seconds;
//			}
//			if (minutes.length() < 2) {
//				minutes = "0" + minutes;
//			}
//			if (hours.length() < 2) {
//				hours = "0" + hours;
//			}
//		}
		
		StringBuffer buf = new StringBuffer();
		if (Integer.valueOf(days) > 0) {
			buf.append(days).append(" day(s) ");
		}
		if (Integer.valueOf(hours) > 0) {
			buf.append(hours).append(" hour(s) ");
		}
		if (Integer.valueOf(minutes) > 0) {
			buf.append(minutes).append(" minutes(s) ");
		}
		if (Integer.valueOf(seconds) > 0) {
			buf.append(seconds).append(" seconds(s) ");
		}
		
		return buf.toString();
	}
	
	/**
	 * 
	 * @param sourceDate
	 * @param days
	 * @return
	 * @description
	 * 加天
	 */
	public static Date addDate(Date sourceDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param sourceDate
	 * @param months
	 * @return
	 * @description
	 * 加月
	 */
	public static Date addMonth(Date sourceDate,int months)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param sourceDate
	 * @param months
	 * @return
	 * @description
	 * 加分钟
	 */
	public static Date addMinute(Date sourceDate,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	public  static String getTimeStartDate(Date date){
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	public  static String getTimeExpireDate(Date date){
		Date destDate  =  addMinute(date,30);
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(destDate);
	}

	public static Date StringToDate(String date){
		String format="yyyy-MM-dd";
		if(date.indexOf(" ")>-1){
			String h=date.split(" ")[1];
			int size=h.split(":").length;
			switch (size) {
			case 1:
				format="yyyy-MM-dd HH";
				break;
			case 2:
				format="yyyy-MM-dd HH:mm";
				break;
			case 3:
				format="yyyy-MM-dd HH:mm:ss";
				break;
			}
		}
		return StringToDate(date, format);
	}
}
