package com.yaodingjiaoyu.action.tr;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassContentService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;

public class Tr_AddContent_Action {
	// 首先需要在上方显示添加回访的表单，在表单的下方显示该例子往期的跟踪记录

	private String id;

	// -------------------------这里是显示跟踪记录需要的核心服务
	private List<Map<String, Object>> ContentList;// JSP页面需要的记录列表
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private ClassContentService searchClassContentService;// 核心服务
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;
	// -------------------------

	public String getId() {
		return id;
	}

	public void setSearchClassContentService(ClassContentService searchClassContentService) {
		this.searchClassContentService = searchClassContentService;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Map<String, Object>> getContentList() {
		return ContentList;
	}

	public void setContentList(List<Map<String, Object>> contentList) {
		ContentList = contentList;
	}

	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			// 由于时间格式需要改变，因此该数据需要转码
			// 获得存储在SESSION中的校区和职工编号
			String stuff = ActionContext.getContext().getSession().get("ID").toString();
			String campus = ActionContext.getContext().getSession().get("campus").toString();

			List<ClassContent> list = searchClassContentService.findTrContentByStudentId(id, stuff, campus);
			List<ClassContent> temp = (List<ClassContent>) getResultObjectListService.getResultObjectList(1, PAGE_MAX,
					list);// 显示前15行数据
			ContentList = transLateService.transLateClassContent(temp);

			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:添加跟踪记录失败。参数id：" + id  + ",TrId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}

	}
}
