package com.yaodingjiaoyu.action.sd;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.SearchStuffService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_Manager_Stuff {
	private SearchStuffService searchStuffService;
	private List<Map<String, Object>> stuffList;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private List<Job> job_list = null;
	private PageListService loadPageListService;
	private TransLateService transLateService;

	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	

	public List<Map<String, Object>> getStuffList() {
		return stuffList;
	}



	public void setStuffList(List<Map<String, Object>> stuffList) {
		this.stuffList = stuffList;
	}



	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}

	public List<Job> getJob_list() {
		return job_list;
	}

	public void setJob_list(List<Job> job_list) {
		this.job_list = job_list;
	}

	public void setSearchStuffService(SearchStuffService searchStuffService) {
		this.searchStuffService = searchStuffService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	// 查询员工信息
	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			String campus = ActionContext.getContext().getSession().get("campus").toString();
			
			//查询该校区的员工
			List<Stuff> list = searchStuffService.findBySdRequest("", "no", "no", "no", campus);
			//取上一个查询的第一页数据
			List<Stuff> stuff_list_tmp = (List<Stuff>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);// 显示前15行数据
			//最后还需要对员工列表进行翻译,因为一些状态字段值为0和1
			stuffList = transLateService.transLateStuffDetailList(stuff_list_tmp);
			
			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			job_list = loadPageListService.getJob_listForSd();// 获得岗位表，但是要去除管理员和校长
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:程序运行异常。参数SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}

	}
}
