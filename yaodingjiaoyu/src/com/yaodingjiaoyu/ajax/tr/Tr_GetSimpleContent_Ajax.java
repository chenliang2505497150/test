package com.yaodingjiaoyu.ajax.tr;

import java.util.HashMap;
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

public class Tr_GetSimpleContent_Ajax {
	private String id;
	private int start_look;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	private List<Map<String, Object>> ContentList;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private ClassContentService searchClassContentService;// 核心服务
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}

	public void setSearchClassContentService(ClassContentService searchClassContentService) {
		this.searchClassContentService = searchClassContentService;
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
	private void doLoad() {
		int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
		// 由于时间格式需要改变，因此该数据需要转码
		// 获得存储在SESSION中的校区和职工编号
		String stuff = ActionContext.getContext().getSession().get("ID").toString();
		String campus = ActionContext.getContext().getSession().get("campus").toString();

		List<ClassContent> list = searchClassContentService.findTrContentByStudentId(id, stuff, campus);
		List<ClassContent> temp = (List<ClassContent>) getResultObjectListService.getResultObjectList(start_look,
				PAGE_MAX, list);// 显示前15行数据
		ContentList = transLateService.transLateClassContent(temp);

		item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);

		resultMap.put("ContentList", ContentList);
		resultMap.put("all_page", item.get("all_page"));
		resultMap.put("firstItem", item.get("firstItem"));
		resultMap.put("lastItem", item.get("lastItem"));
		resultMap.put("allItem", item.get("allItem"));
	}

	public String execute() {

		try {
			resultMap.clear();// 先清空数据
			doLoad();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查询例子失败。参数start_look：" + start_look + ",id:" + id
					+ ",TrId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
