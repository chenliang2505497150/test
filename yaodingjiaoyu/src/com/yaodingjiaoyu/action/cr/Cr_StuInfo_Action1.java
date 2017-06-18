package com.yaodingjiaoyu.action.cr;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Level;

public class Cr_StuInfo_Action1 {
	private SearchStudentService searchStudentService;
	private List<Map<String, Object>> studentList;
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private List<Level> level_list = null;
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

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
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

	public SearchStudentService getSearchStudentService() {
		return searchStudentService;
	}

	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	

	public List<Map<String, Object>> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Map<String, Object>> studentList) {
		this.studentList = studentList;
	}


	@SuppressWarnings("unchecked")
	public String execute(){
		
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			//获得存储在SESSION中的校区和职工编号
			String stuff=  ActionContext.getContext().getSession().get("ID").toString();
			String campus= ActionContext.getContext().getSession().get("campus").toString();
			level_list = loadPageListService.getLevel_list();//获得年级列表
			List<Map<String, Object>> list = searchStudentService.findByCrRequest_MAP("", "", "no", "", "", "no", "no", stuff, campus);
			//处理，将部分的内容进行翻译
			list = transLateService.transLateStudent(level_list, list);
			studentList = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);//显示前15行数据
			item =  getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:加载学生信息失败,MESSAGE:"+e.getMessage());
			return "error";
		}
		
	}
}
