package com.yaodingjiaoyu.action.cc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.CcContent;

public class Cc_Examples_Action2 {
	private List<Map<String, Object>> ccContentList;//JSP页面需要的记录列表
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private CcContentService searchCcContentService;//核心服务
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;
	
	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
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


	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setSearchCcContentService(CcContentService searchCcContentService) {
		this.searchCcContentService = searchCcContentService;
	}

	//页面加载前的初始化工作
	@SuppressWarnings("unchecked")
	public String execute(){
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			//由于时间格式需要改变，因此该数据需要转码
			//获得存储在SESSION中的校区和职工编号
			String stuff=  ActionContext.getContext().getSession().get("ID").toString();
			String campus= ActionContext.getContext().getSession().get("campus").toString();
			
			List<CcContent> list = searchCcContentService.findCcContentByCc("", campus, "", "", stuff);
			List<CcContent> temp = (List<CcContent>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);//显示前15行数据
			ccContentList =  transLateService.transLateCcContent(temp);
			
			item =  getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化列表失败。MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
