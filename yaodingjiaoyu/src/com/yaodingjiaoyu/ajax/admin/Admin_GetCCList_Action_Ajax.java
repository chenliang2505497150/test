package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Admin_GetCCList_Action_Ajax {
	
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private List<Map<String, Object>> stuffs;
	private TransLateService transLateService;
	private String campus;//传入的校区编号，用于返回校区销售列表
	private PageListService pageListService;
	
	
	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public String getCampus() {
		return campus;
	}



	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String execute() {
		try {
			resultMap.clear();
			List<Stuff> stuff_Obj_list= pageListService.getSalerListByCampus(campus);
			stuffs =  transLateService.transLateStuffList(stuff_Obj_list);
			resultMap.put("status", 200);//200表示成功
			resultMap.put("salerList", stuffs);
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:获取销售列表信息失败。"+e.getMessage());
			return "error";
		}
		
		return null;
	}
}
