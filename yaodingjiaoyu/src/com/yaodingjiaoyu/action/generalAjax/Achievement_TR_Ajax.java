package com.yaodingjiaoyu.action.generalAjax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AchievementService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;

public class Achievement_TR_Ajax {
	private String name;
	private String time1;
	private String time2;
	private String campus;
	private int start_look;// 本次浏览的起始页
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private List<Map<String, Object>> Contennt_list;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private AchievementService achievementService;
	private GetResultObjectListService getResultObjectListService;// 分页
	private GetItemInfoService getItemInfoService;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}

	public void setAchievementService(AchievementService achievementService) {
		this.achievementService = achievementService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			List<Map<String, Object>> list = achievementService.getTrAchievement(name, time1, time2, campus);
			Contennt_list = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(start_look,
					PAGE_MAX, list);// 显示前15行数据
			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			// 返回数据:先将MAP转成JSON，然后返回数据

			resultMap.put("Contennt_list", Contennt_list);
			resultMap.put("all_page", item.get("all_page"));
			resultMap.put("firstItem", item.get("firstItem"));
			resultMap.put("lastItem", item.get("lastItem"));
			resultMap.put("allItem", item.get("allItem"));

			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:获取销售业绩。name:" + name + ",time1:" + time1 + ",time2:"
					+ time2 + ",campus:" + campus);
			return "error";
		}
		return null;
	}
}
