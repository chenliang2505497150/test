package com.yaodingjiaoyu.ajax.cc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.CcContent;

public class Cc_SearchCcContentSimple_Ajax {
	private int start_look;// 本次浏览的起始页
	private String id;// 编号

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private List<Map<String, Object>> ccContennt_list;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息

	private CcContentService searchCcContentService;// 核心服务
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

	public void setSearchCcContentService(CcContentService searchCcContentService) {
		this.searchCcContentService = searchCcContentService;
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
			resultMap.clear();// 先清空数据
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			// 获得存储在SESSION中的校区和职工编号
			String stuff = ActionContext.getContext().getSession().get("ID").toString();
			String campus = ActionContext.getContext().getSession().get("campus").toString();
			// 未经处理，例如年级还是数字
			List<CcContent> list = searchCcContentService.findCcContentByExamplesId(id, stuff, campus);
			List<CcContent> tmp = (List<CcContent>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX,
					list);// 显示要求行数据

			ccContennt_list = transLateService.transLateCcContent(tmp);
			item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
			// 返回数据:先将MAP转成JSON，然后返回数据

			resultMap.put("ContentList", ccContennt_list);
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
			logger.error(this.getClass().getName() + "-->execute:查询例子失败。参数id：" + id + ",start_look:" + start_look
					+ ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
