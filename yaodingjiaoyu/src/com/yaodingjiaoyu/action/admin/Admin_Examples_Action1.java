package com.yaodingjiaoyu.action.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;


//线索客户查询的初始化
public class Admin_Examples_Action1 {
	private List<Level> level_list;
	private List<Probability> probability_list;
	private List<Channel> channel_list;
	private List<Campus> campus_list;
	private List<Map<String, Object>> examples_list;
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private ExamplesService searchExamplesService;
	private PageListService loadPageListService;
	private TransLateService transLateService;
	
	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}

	public List<Probability> getProbability_list() {
		return probability_list;
	}

	public void setProbability_list(List<Probability> probability_list) {
		this.probability_list = probability_list;
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

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	
	public void setSearchExamplesService(ExamplesService searchExamplesService) {
		this.searchExamplesService = searchExamplesService;
	}

	public List<Map<String, Object>> getExamples_list() {
		return examples_list;
	}

	public void setExamples_list(List<Map<String, Object>> examples_list) {
		this.examples_list = examples_list;
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

	@SuppressWarnings("unchecked")
	public String execute() {
		
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			//初始化列表
			level_list = loadPageListService.getLevel_list();
			probability_list = loadPageListService.getProbability_list();
			channel_list = loadPageListService.getChannel_list();
			campus_list = loadPageListService.getCampus_list();
			//未经处理，例如年级还是数字
			List<Map<String, Object>> list= searchExamplesService.findByAdminRequest_MAP("", "", "no", "", "", "", "no", "no", "no", "no", "no","no");
			//处理，将部分的内容进行翻译
			list = transLateService.transLateExamples(level_list, probability_list, channel_list, campus_list, list);
			examples_list = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);//显示前15行数据
			item =  getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化例子列表失败。");
			return "error";
		}
	}
	
	
}
