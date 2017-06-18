package com.yaodingjiaoyu.Service;

import java.util.Date;
import java.util.Map;

public interface DayService {

	public  Map<String, String> getMondayAndSundayOfWeek(Date date);
	//获得当日是本周星期几
	public  int getDayOfWeek(Date date);
	
	//获得N周后的日期
	public Date getWeeklast(Date date,int n);
}
