package com.yaodingjiaoyu.action;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.yaodingjiaoyu.Service.AchievementService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.Campus;

public class Achievement_CC_Action {

	/*
	 * 需求：销售系统的业绩列表主要显示销售的：姓名、校区、金额按照金额进行排名 可以添加筛选框：姓名、时间段、校区 数据准备：1.校区列表
	 * 2.销售排名列表
	 */
	private List<Campus> campus_list = null;
	private List<Map<String, Object>> ContentList = null;
	private PageListService loadPageListService;
	private AchievementService achievementService;
	private GetResultObjectListService getResultObjectListService;// 分页
	private GetItemInfoService getItemInfoService;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public List<Map<String, Object>> getContentList() {
		return ContentList;
	}

	public void setContentList(List<Map<String, Object>> contentList) {
		ContentList = contentList;
	}

	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public void setAchievementService(AchievementService achievementService) {
		this.achievementService = achievementService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			List<Map<String, Object>> list = achievementService.getCcAchievement("", "", "", "no");
			ContentList = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);// 显示前15行数据
			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			campus_list = loadPageListService.getCampus_list();
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:获取销售业绩。");
			return "error";
		}
	}
}
