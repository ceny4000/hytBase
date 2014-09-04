package com.hyt.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Title: DateUtils 日期相關Utils
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/29
 */
public class DateUtils {

    /**
     * Test
     * @param args
     */
    public static void main(String args[]) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    	
    	Calendar cal = Calendar.getInstance();
    	System.out.println(sdf.format(getFirstMilisOfDay(cal).getTime()));
    	System.out.println(sdf.format(getFirstMilisOfMonth(cal).getTime()));
    	System.out.println(sdf.format(getFirstMilisOfYear(cal).getTime()));
    	System.out.println(sdf.format(getLastMilisOfDay(cal).getTime()));
    	System.out.println(sdf.format(getLastMilisOfMonth(cal).getTime()));
    	System.out.println(sdf.format(getLastMilisOfYear(cal).getTime()));
    }
	
	/**
	 * 取得任意時間當日開始毫秒 00:00:00 0000
	 * 2014/7/29 16:40:50 2410 >> 2014/7/29 00:00:00 000
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getFirstMilisOfDay(Calendar cal) {
		Calendar rsl = (Calendar)cal.clone();
		rsl.set(Calendar.HOUR_OF_DAY, 0);
		rsl.set(Calendar.MINUTE, 0);
		rsl.set(Calendar.SECOND, 0);
		rsl.set(Calendar.MILLISECOND, 0);
		
		return rsl;
	}
	
	/**
	 * 取得任意時間當月起始毫秒
	 * 2014/7/29 16:40:50 2410 >> 2014/7/1 00:00:00 000
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getFirstMilisOfMonth(Calendar cal) {
		Calendar rsl = getFirstMilisOfDay(cal);
		rsl.set(Calendar.DATE, 1);
		
		return rsl;
	}
	
	/**
	 * 取得任意時間當年起始毫秒
	 * 2014/7/29 16:40:50 2410 >> 2014/1/1 00:00:00 000
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getFirstMilisOfYear(Calendar cal) {
		Calendar rsl = getFirstMilisOfMonth(cal);
		rsl.set(Calendar.MONTH, 0);
		
		return rsl;
	}
	
	/**
	 * 取得任意時間當日結束毫秒 23:59:59 9999
	 * 2014/7/29 16:40:50 2410 >> 2014/7/29 23:59:59 999
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getLastMilisOfDay(Calendar cal) {
		Calendar rsl = getFirstMilisOfDay(cal);
		rsl.add(Calendar.DATE, 1);
		rsl.add(Calendar.MILLISECOND, -1);
		
		return rsl;
	}
	
	/**
	 * 取得任意時間當月結束毫秒
	 * 2014/7/29 16:40:50 2410 >> 2014/7/31 23:59:59 999
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getLastMilisOfMonth(Calendar cal) {
		Calendar rsl = getFirstMilisOfMonth(cal);
		rsl.add(Calendar.MONTH, 1);
		rsl.add(Calendar.MILLISECOND, -1);
		
		return rsl;
	}
	
	/**
	 * 取得任意時間當年結束毫秒
	 * 2014/7/29 16:40:50 2410 >> 2014/12/31 23:59:59 999
	 * @param cal 任意時間
	 * @return
	 */
	public static Calendar getLastMilisOfYear(Calendar cal) {
		Calendar rsl = getFirstMilisOfYear(cal);
		rsl.add(Calendar.YEAR, 1);
		rsl.add(Calendar.MILLISECOND, -1);
		
		return rsl;
	}
	
	/**
	 * 格式化日期
	 * @param cal
	 * @param pattern
	 * @return
	 */
	public static String format(Calendar cal, String pattern) {
		return format(cal.getTime(), pattern);
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
	}
	
	/**
	 * 判斷兩個日期是否相同(只判斷日期，忽略時間)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateEquals(Calendar date1, Calendar date2) {
		if (date1 == null && date2 == null) {
			return true;
		}
		if (date1 == null || date2 == null) {
			return false;
		}
		return format(date1, "yyyyMMdd").equals(format(date2, "yyyyMMdd"));
	}
	
	/**
	 * 判斷兩個日期是否相同(只判斷日期，忽略時間)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateEquals(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return true;
		}
		if (date1 == null || date2 == null) {
			return false;
		}
		return format(date1, "yyyyMMdd").equals(format(date2, "yyyyMMdd"));
	}
}
