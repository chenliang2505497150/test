package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.CcContent;

public class Admin_SearchCcContent_Ajax {

	private int start_look;// 本次浏览的起始页
	private String time1 = "";
	private String time2 = "";
	private String cc;//销售人员的编号
	private String campus;//校区编号
	
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private List<Map<String, Object>> ccContennt_list;
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	
	private CcContentService searchCcContentService;//核心服务
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;
	
	
	public int getStart_look() {
		return start_look;
	}


	public void setStart_look(int start_look) {
		this.start_look = start_look;
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


	public String getCc() {
		return cc;
	}


	public void setCc(String cc) {
		this.cc = cc;
	}


	public String getCampus() {
		return campus;
	}


	public void setCampus(String campus) {
		this.campus = campus;
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
	public String execute(){
		
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			resultMap.clear();//先清空数据	
			//未经处理，例如年级还是数字
			List<CcContent> list= searchCcContentService.findCcContentByAdmin(campus, time1, time2, cc);
			
			List<Map<String, Object>> tmp = transLateService.transLateCcContent(list);
			ccContennt_list = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX, tmp);//显示要求行数据
			item =  getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
			// 返回数据:先将MAP转成JSON，然后返回数据
			
			
			resultMap.put("examplesList", ccContennt_list);	
			resultMap.put("all_page",item.get("all_page"));
			resultMap.put("firstItem", item.get("firstItem"));
			resultMap.put("lastItem", item.get("lastItem"));
			resultMap.put("allItem", item.get("allItem"));
				
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查询跟踪记录失败。参数start_look："+start_look+",time1:"+time1+
					",time2:"+time2+",cc:"+cc+",campus:"+campus+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
