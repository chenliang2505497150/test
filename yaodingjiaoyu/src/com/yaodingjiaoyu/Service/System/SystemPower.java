package com.yaodingjiaoyu.Service.System;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SystemPower {
	/**
	 * 
	 * @return 返回用户权限表
	 */
	public Map<String, List<String>> getPowerList();
	/**
	 * 判断用户的请求是否合法
	 * @param power
	 * @param actionPath
	 * @return
	 */
	public boolean isLegal(String power,String actionPath);
	
	/**
	 * 返回struts2中所有注册的action列表
	 */
	public Set<String> getActionList();
	
	/**
	 * 判断该请求是否是struts 2中注册过的合法请求
	 * @param actionPath 请求路径
	 * @return
	 */
	public boolean isLegalAction(String actionPath);
}
