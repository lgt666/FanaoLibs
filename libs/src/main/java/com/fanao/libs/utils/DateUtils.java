package com.fanao.libs.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日间格式
 * 
 * @author liutao
 *
 */
public class DateUtils {
	/**
	  * 获取现在时间
	  * 
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		 
		return currentTime_2;
	}
	
	/**
	  * 获取现在时间
	  * 
	  * @return 返回短时间格式 yyyy-MM-dd
	  */
	 public static Date getNowDateShort() {
		 Date currentTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(currentTime);
		 ParsePosition pos = new ParsePosition(8);
		 Date currentTime_2 = formatter.parse(dateString, pos);
		 
		 return currentTime_2;
	 }
	 
	 /**
	  * 获取现在时间
	  * 
	  * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	  */
	 public static String getStringDate() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }
	 
	 /**
	  * 获取现在时间
	  * 
	  * @return 返回字符串格式 MM-dd HH:mm
	  */
	 public static String getStringDate2() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }
	 
	 /**
	  * 获取现在时间
	  * 
	  * @return 返回短时间字符串格式yyyy-MM-dd
	  */
	 public static String getStringDateShort() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }
	 
	 /**
	  * 获取时间 小时:分;秒 HH:mm:ss
	  */
	 public static String getTimeShort() {
	  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	  Date currentTime = new Date();
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }
	 
	 /**
	  * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date strToDateLong(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	 }
	 
	 /**
	  * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	  */
	 public static String dateToStrLong(Date dateDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(dateDate);
	  return dateString;
	 }

	 /**
	  * 将短时间格式时间转换为字符串 yyyy-MM-dd
	  */
	 public static String dateToStr(Date dateDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(dateDate);
	  return dateString;
	 }

	 /**
	  * 将短时间格式字符串转换为时间 yyyy-MM-dd
	  */
	 public static Date strToDate(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	 }

	 /**
	  * 得到现在时间
	  */
	 public static Date getNow() {
	  Date currentTime = new Date();
	  return currentTime;
	 }

	 /**
	  * 提取一个月中的最后一天
	  */
	 public static Date getLastDate(long day) {
	  Date date = new Date();
	  long date_3_hm = date.getTime() - 3600000 * 34 * day;
	  Date date_3_hm_date = new Date(date_3_hm);
	  return date_3_hm_date;
	 }

	 /**
	  * 得到现在时间
	  *
	  * @return 字符串 yyyyMMdd HHmmss
	  */
	 public static String getStringToday() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * 得到现在小时
	  */
	 public static String getHour() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  String hour;
	  hour = dateString.substring(11, 13);
	  return hour;
	 }

	 /**
	  * 得到现在分钟
	  */
	 public static String getTime() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  String min;
	  min = dateString.substring(14, 16);
	  return min;
	 }

	 /**
	  * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	  *
	  * @param sformat
	  *            yyyyMMddhhmmss
	  */
	 public static String getUserDate(String sformat) {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat(sformat);
	  String dateString = formatter.format(currentTime);
	  return dateString;
	 }

	 /**
	  * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	  */
	 public static String getTwoHour(String st1, String st2) {
	  String[] kk = null;
	  String[] jj = null;
	  kk = st1.split(":");
	  jj = st2.split(":");
	  if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
	   return "0";
	  else {
	   double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
	   double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
	   if ((y - u) > 0)
	    return y - u + "";
	   else
	    return "0";
	  }
	 }

	 /**
	  * 得到二个日期间的间隔天数
	  */
	 public static String getTwoDay(String sj1, String sj2) {
	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	  long day = 0;
	  try {
	   Date date = myFormatter.parse(sj1);
	   Date mydate = myFormatter.parse(sj2);
	   day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	  } catch (Exception e) {
	   return "";
	  }
	  return day + "";
	 }
	 
	 /**
	  * 时间前推或后推分钟,其中JJ表示分钟.
	  */
	 public static String getPreTime(String sj1, String jj) {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String mydate1 = "";
	  try {
	   Date date1 = format.parse(sj1);
	   long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
	   date1.setTime(Time * 1000);
	   mydate1 = format.format(date1);
	  } catch (Exception e) {
	  }
	  return mydate1;
	 }
	 
	 /**
	  * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	  */
	 public static String getNextDay(String nowdate, String delay) {
	  try{
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  String mdate = "";
	  Date d = strToDate(nowdate);
	  long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
	  d.setTime(myTime * 1000);
	  mdate = format.format(d);
	  return mdate;
	  }catch(Exception e){
	   return "";
	  }
	 }
	 
	 /**
	  * 判断是否润年
	  * 
	  *  详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	  * 3.能被4整除同时能被100整除则不是闰年
	  */
	 public static boolean isLeapYear(String date) {
	  Date d = strToDate(date);
	  GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	  gc.setTime(d);
	  int year = gc.get(Calendar.YEAR);
	  if ((year % 400) == 0)
	   return true;
	  else if ((year % 4) == 0) {
	   if ((year % 100) == 0)
	    return false;
	   else
	    return true;
	  } else
	   return false;
	 }
	 
	 /**
	  * 返回美国时间格式 26 Apr 2006
	  */
	 public static String getEDate(String str) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  ParsePosition pos = new ParsePosition(0);
		  Date strtodate = formatter.parse(str, pos);
		  String j = strtodate.toString();
		  String[] k = j.split(" ");
		  return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	 }

	/**
	 * 通过时间戳获取格式化后的时间
	 *
	 * @param timestamp 此时间戳乘以1000L原因是PHP的, 所以Java返回的时间戳时要去除.
     */
	public static String getFormatDate(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sd = sdf.format(new Date(timestamp * 1000L));
		String day = formatDisplayTime(sd, "yyyy-MM-dd HH:mm");
		return day;
	}

	/**
	 * 格式化时间（输出类似于 刚刚, 4分钟前, 一小时前, 昨天这样的时间）
	 *
	 * @param time    需要格式化的时间 如"2014-07-14 19:01:45"
	 * @param pattern 输入参数time的时间格式 如:"yyyy-MM-dd HH:mm:ss"
	 *                如果为空则默认使用"yyyy-MM-dd HH:mm:ss"格式
	 * @return time为null，或者时间格式不匹配，输出空字符""
	 */
	public static String formatDisplayTime(String time, String pattern) {
		String display = "";
		int tMin = 60 * 1000;
		int tHour = 60 * tMin;
		int tDay = 24 * tHour;

		if (time != null) {
			try {
				Date tDate = new SimpleDateFormat(pattern).parse(time);
				Date today = new Date();
				SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
				SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
				Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
				Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
				Date beforeYes = new Date(yesterday.getTime() - tDay);
				if (tDate != null) {
					SimpleDateFormat halfDf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					long dTime = today.getTime() - tDate.getTime();
					if (tDate.before(thisYear)) {
						display = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tDate);
					} else {

						if (dTime < tMin) {
							display = "刚刚";
						} else if (dTime < tHour) {
							display = (int) Math.ceil(dTime / tMin) + "分钟前";
						} else if (dTime < tDay && tDate.after(yesterday)) {
							display = (int) Math.ceil(dTime / tHour) + "小时前";
						} else if (tDate.after(beforeYes) && tDate.before(yesterday)) {
							display = "昨天" + new SimpleDateFormat("HH:mm").format(tDate);
						} else {
							display = halfDf.format(tDate);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return display;
	}

	/**
	 * 返回人性化的时间显示格式
	 */
	public static String showTime(Date ctime) {
		String r = "";
		if (ctime == null) {
			return r;
		}

		Calendar current = Calendar.getInstance();
		Calendar today = Calendar.getInstance(); // 今天
		Calendar yesterday = Calendar.getInstance(); // 昨天

		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
		yesterday.set(Calendar.HOUR_OF_DAY, 0);
		yesterday.set(Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);
		yesterday.add(Calendar.DAY_OF_MONTH, -1);

		current.setTime(ctime);

		String format;

		if(current.after(today)) { // 一天内
			format = "今天HH:mm";
		} else if(current.before(today) && current.after(yesterday)) { // 二天内
			format = "昨天HH:mm";
		} else { // 日期格式
			format = "yyyy-MM-dd HH:mm";
		}

		SimpleDateFormat df = new SimpleDateFormat(format);
		r = df.format(ctime).toString();
		return r;
	}
}