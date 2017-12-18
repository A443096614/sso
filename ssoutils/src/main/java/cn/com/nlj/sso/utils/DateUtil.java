package cn.com.nlj.sso.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
* @author nlj 2017年9月21日 下午5:47:58
* 类说明：
*/
public class DateUtil {

	/***
	 * 当前日期
	 * @return 格式:20171122
	 */
	public static String getCurrent() {
		LocalDate today = LocalDate.now();
		return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/***
	 * 当前日期
	 * @return 格式：2017-11-22
	 */
	public static String getCurrentFmt() {
		LocalDate today = LocalDate.now();
		return today.toString();
	}
	
	/***
	 * 当前日期
	 * @return 格式:20171122204833
	 */
	public static String getCurrentHms() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}
	
	/***
	 * 当前日期
	 * @return 格式:2017-11-22 20:48:33
	 */
	public static String getCurrentFmtHms() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	/***
	 * 获取当前日期上一天或下一天
	 * @param day 上一天时为负数（如：－1则为上一天， 1为下一天）
	 * @param fmt 格式化日期格式
	 * @return
	 */
	public static String getNextDay(int day, String fmt) {
		LocalDate nextDay = LocalDate.now().plusDays(day);
		return nextDay.format(DateTimeFormatter.ofPattern(fmt));
	}
	
	/***
	 * 获取某天上一天或下一天
	 * @param date 日期
	 * @param day 上一天时为负数（如：－1则为上一天， 1为下一天）
	 * @return
	 */
	public static String getNextDay(String date, int day) {
		if (date.contains("-")) {
			LocalDate nextDay = LocalDate.parse(date);
			return nextDay.plusDays(day).toString();
		} else {
			LocalDate nextDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
			String formatdate = nextDay.plusDays(day).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			return formatdate;
		}
	}
	
	/***
	 * 获取某天上一天或下一天
	 * @param date 日期
	 * @param day 上一天时为负数（如：－1则为上一天， 1为下一天）
	 * @param fmt 返回结果格式化
	 * @return
	 */
	public static String getNextDay(String date, int day, String fmt) {
		if (date.contains("-")) {
			LocalDate nextDay = LocalDate.parse(date);
			return nextDay.plusDays(day).format(DateTimeFormatter.ofPattern(fmt)).toString();
		} else {
			LocalDate nextDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
			String formatdate = nextDay.plusDays(day).format(DateTimeFormatter.ofPattern(fmt));
			return formatdate;
		}
	}
	
	/***
	 * 获取某天上一个月或下一个月
	 * @param date 日期
	 * @param mon 上一个月时为负数（如：－1则为上一个月， 1为下一个月）
	 * @param fmt 返回结果格式化
	 * @return
	 */
	public static String getNextMonths(String date, int mon, String fmt) {
		if (date.contains("-")) {
			LocalDate nextDay = LocalDate.parse(date);
			return nextDay.plusMonths(mon).format(DateTimeFormatter.ofPattern(fmt)).toString();
		} else {
			LocalDate nextDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
			String formatdate = nextDay.plusMonths(mon).format(DateTimeFormatter.ofPattern(fmt));
			return formatdate;
		}
	}	
	
	
	/***
	 * 获取本月第一天
	 * @return
	 */
	public static String getFirstDayOfThisMonth() {
		LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		return firstDayOfThisMonth.toString();
	}
	
	/***
	 * 获取本月第一天
	 * @param fmt 格式化
	 * @return
	 */
	public static String getFirstDayOfThisMonth(String fmt) {
		LocalDate firstDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		return firstDayOfThisMonth.format(DateTimeFormatter.ofPattern(fmt)).toString();
	}
	
	/***
	 * 获取指定日期月份的第一天
	 * @return
	 */
	public static String getFirstDayOfThisMonth(String date, String fmt) {
		
		if (date.contains("-")) {
			LocalDate nextDay = LocalDate.parse(date);
			return nextDay.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(fmt)).toString();
		} else {
			LocalDate nextDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
			return nextDay.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(fmt)).toString();
		}
	}
	
	/***
	 * 获取本月的第N天
	 * @param day 第几天（day不能超过当月最大天数）
	 * @return
	 */
	public static String getFirstDayOfThisMonth(int day) {
		LocalDate secondDayOfThisMonth = LocalDate.now().withDayOfMonth(day);
		return secondDayOfThisMonth.toString();
	}

	/***
	 * 获取本月最后一天
	 * @return
	 */
	public static String getLastDayOfThisMonth() {
		LocalDate lastDayOfThisMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		return lastDayOfThisMonth.toString();
	}
	
	/***
	 * 获取本月最后一天
	 * @return
	 */
	public static String getLastDayOfThisMonth(String date, String fmt) {
		if (date.contains("-")) {
			LocalDate nextDay = LocalDate.parse(date);
			return nextDay.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(fmt)).toString();
		} else {
			LocalDate nextDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
			return nextDay.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(fmt)).toString();
		}
	}
	
	public static void main(String[] args) {
		System.err.println(getLastDayOfThisMonth("20171223", "yyyyMMdd"));
		
		System.err.println(getNextDay("20161028",6,"yyyyMMdd"));
		System.err.println(getLastDayOfThisMonth("20160201","yyyyMMdd"));
		
		System.err.println(getNextMonths("20160131",-1,"yyyyMMdd"));
	
	}
}
