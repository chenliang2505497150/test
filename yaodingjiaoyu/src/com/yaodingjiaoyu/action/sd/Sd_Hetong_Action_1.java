package com.yaodingjiaoyu.action.sd;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_Hetong_Action_1 {

	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private List<Map<String, Object>> ContentList = null;
	private List<Level> level_list = null;
	private List<Stuff> stuff_list = null;
	private List<HetongType> hetong_type_list = null;
	private List<CourseType> course_type_list = null;
	private Double total_money;

	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private PageListService loadPageListService;
	private HetongService searchHetongService;
	private TransLateService transLateService;

	public Double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public List<Map<String, Object>> getContentList() {
		return ContentList;
	}

	public void setContentList(List<Map<String, Object>> contentList) {
		ContentList = contentList;
	}

	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}

	public List<Stuff> getStuff_list() {
		return stuff_list;
	}

	public void setStuff_list(List<Stuff> stuff_list) {
		this.stuff_list = stuff_list;
	}

	public List<HetongType> getHetong_type_list() {
		return hetong_type_list;
	}

	public void setHetong_type_list(List<HetongType> hetong_type_list) {
		this.hetong_type_list = hetong_type_list;
	}

	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}

	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
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

	public void setSearchHetongService(HetongService searchHetongService) {
		this.searchHetongService = searchHetongService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			int campus = (int) ActionContext.getContext().getSession().get("campus");
			List<Hetong> list = searchHetongService.findHetongByAdmin("", "", "", "no", "no", campus + "", "no", "no",
					"", "");
			total_money = searchHetongService.findHetong_Sum_Money("", "", "", "no", "no", campus + "", "no", "no", "",
					"");

			List<Hetong> tmp = (List<Hetong>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);// 显示前15行数据
			ContentList = transLateService.transLateHetongList(tmp);
			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			level_list = loadPageListService.getLevel_list();// 获得年级列表
			hetong_type_list = loadPageListService.getHetongTypeList();
			course_type_list = loadPageListService.getCourseTypeList();
			stuff_list = loadPageListService.getcr_ccListByCampus(campus + "");
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
