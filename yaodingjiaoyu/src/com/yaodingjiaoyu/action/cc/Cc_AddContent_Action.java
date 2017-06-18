package com.yaodingjiaoyu.action.cc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.CcContent;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class Cc_AddContent_Action {
	// 首先需要在上方显示添加回访的表单，在表单的下方显示该例子往期的跟踪记录
	// 可以标记有效无效，以及可能性大小,和具体的谈话内容
	private String id;
	private List<Probability> probability_list;
	private List<Level> level_list;
	private List<Channel> channel_list;
	private List<Campus> campus_list;
	private PageListService pageListService;

	// -------------------------这里是显示例子需要的核心服务
	private List<Map<String, Object>> ccContentList;// JSP页面需要的记录列表
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private CcContentService searchCcContentService;// 核心服务
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;
	private Examples examples;
	private ExamplesService examplesService;
	// -------------------------

	public List<Probability> getProbability_list() {
		return probability_list;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}

	public List<Channel> getChannel_list() {
		return channel_list;
	}

	public void setChannel_list(List<Channel> channel_list) {
		this.channel_list = channel_list;
	}

	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}

	public Examples getExamples() {
		return examples;
	}

	public void setExamples(Examples examples) {
		this.examples = examples;
	}

	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Map<String, Object>> getCcContentList() {
		return ccContentList;
	}

	public void setCcContentList(List<Map<String, Object>> ccContentList) {
		this.ccContentList = ccContentList;
	}

	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
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

	public void setProbability_list(List<Probability> probability_list) {
		this.probability_list = probability_list;
	}

	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			examples = examplesService.findById(Integer.parseInt(id));
			if (null == examples) {
				return "error";
			}

			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));

			// 获取列表数据
			probability_list = pageListService.getProbability_list();
			level_list = pageListService.getLevel_list();
			channel_list = pageListService.getChannel_list();
			campus_list = pageListService.getCampus_list();
			
			// 由于时间格式需要改变，因此该数据需要转码
			// 获得存储在SESSION中的校区和职工编号
			String stuff = ActionContext.getContext().getSession().get("ID").toString();
			String campus = ActionContext.getContext().getSession().get("campus").toString();

			List<CcContent> list = searchCcContentService.findCcContentByExamplesId(id, stuff, campus);
			List<CcContent> temp = (List<CcContent>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);// 显示前15行数据
			ccContentList = transLateService.transLateCcContent(temp);

			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:初始化列表失败。参数:" + "ID:" + id + ",MESSAGE:" + e.getMessage());
			return "error";
		}

	}
}
