package com.yaodingjiaoyu.Service;

import java.util.List;
import java.util.Map;

/*
 * 业绩系统服务
 */
public interface AchievementService {

	
	/**
	 * @author chenliang
	 * 该方法获取销售的业绩
	 */
	public List<Map<String, Object>> getCcAchievement(String name,String time1,String time2,String campus);
	
	/**
	 * @author chenliang
	 * 该方法获取班主任业绩
	 */
	public List<Map<String, Object>> getCrAchievement(String name,String time1,String time2,String campus);
	
	/**
	 * @author chenliang
	 * 该方法获取老师业绩
	 */
	public List<Map<String, Object>> getTrAchievement(String name,String time1,String time2,String campus);
	
}
