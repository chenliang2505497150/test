package com.yaodingjiaoyu.action.sd;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_Student_Action1 {
	private SearchStudentService searchStudentService;
	private List<Student> studentList;
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private List<Level> level_list = null;
	private PageListService loadPageListService;
	private List<Stuff> stuff_list = null;
	

	public List<Stuff> getStuff_list() {
		return stuff_list;
	}

	public void setStuff_list(List<Stuff> stuff_list) {
		this.stuff_list = stuff_list;
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

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	

	@SuppressWarnings("unchecked")
	public String execute(){
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			int campus= (int) ActionContext.getContext().getSession().get("campus");
			List<Student> list = searchStudentService.findBySdRequest("", "", "no", "", "", "no",campus+"","no","no");
			studentList = (List<Student>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);//显示前15行数据
			item =  getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			level_list = loadPageListService.getLevel_list();//获得年级列表
			stuff_list = loadPageListService.getcrListByCampus(campus+"");
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:程序运行异常。参数SdId:"
			+ActionContext.getContext().getSession().get("ID")+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
		
	}
}
