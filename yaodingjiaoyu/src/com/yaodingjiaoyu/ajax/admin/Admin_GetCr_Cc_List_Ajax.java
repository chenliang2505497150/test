package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Admin_GetCr_Cc_List_Ajax {
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private List<Map<String, Object>> stuffs;
	private PageListService pageListService;
	private TransLateService transLateService;
	private String campus;
	
	
	public String getCampus() {
		return campus;
	}


	public void setCampus(String campus) {
		this.campus = campus;
	}


	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}


	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}


	public String execute(){
		try {
			resultMap.clear();
			List<Stuff> stuff_Obj_list= pageListService.getcr_ccListByCampus(campus);
			stuffs =  transLateService.transLateCrAndCcList(stuff_Obj_list);
			resultMap.put("status", 200);//200表示成功
			resultMap.put("salerList", stuffs);
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:删除课表信息失败，MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
