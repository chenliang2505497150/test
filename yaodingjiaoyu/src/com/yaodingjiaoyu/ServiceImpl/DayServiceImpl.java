package com.yaodingjiaoyu.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.DayService;

public class DayServiceImpl implements DayService {
	private Logger logger;

	@Override
	public Map<String, String> getMondayAndSundayOfWeek(Date date) {

		try {
			Map<String, String> result = new HashMap<String, String>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
			Calendar cal = Calendar.getInstance();

			cal.setTime(date);
			// System.out.println("要计算日期为:"+sdf.format(cal.getTime()));
			// //输出要计算日期

			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}

			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

			result.put("one", sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			result.put("two", sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			result.put("three", sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			result.put("four", sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			result.put("five", sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			result.put("six", sdf.format(cal.getTime()));

			cal.add(Calendar.DATE, 1);

			result.put("seven", sdf.format(cal.getTime()));
			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数date:" + date + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public int getDayOfWeek(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayWeek == 1) {
				return 7;
			} else {
				return dayWeek - 1;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数date:" + date + ",MESSAGE:" + e.getMessage());
			// 程序异常返回最小的整数
			return Integer.MIN_VALUE;
		}
	}

	@Override
	public Date getWeeklast(Date date, int n) {
		try {
			int day = 7 * n;
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			// 得到当前时间，+3天
			rightNow.add(Calendar.DATE, day);
			// 进行时间转换
			return rightNow.getTime();
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数date:" + date + ",n:" + n + ",MESSAGE:"
					+ e.getMessage());
			// 程序异常返回最小的整数
			return null;
		}

	}

}
