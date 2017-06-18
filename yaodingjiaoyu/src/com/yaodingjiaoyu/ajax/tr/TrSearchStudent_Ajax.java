package com.yaodingjiaoyu.ajax.tr;

import java.util.HashMap;
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

public class TrSearchStudent_Ajax {
	private SearchStudentService searchStudentService;
	private List<Map<String, Object>> studentList;
	private String name;
	private String school;
	private String level;
	private String telephone;
	private String now_class;
	private int start_look;// 本次浏览的起始页
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;
	private PageListService loadPageListService;
	private List<Level> level_list = null;

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNow_class() {
		return now_class;
	}

	public void setNow_class(String now_class) {
		this.now_class = now_class;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}

	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	@SuppressWarnings("unchecked")
	private void doLoad() {
		int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
		// 获得存储在SESSION中的校区和职工编号
		String stuff = ActionContext.getContext().getSession().get("ID").toString();
		String campus = ActionContext.getContext().getSession().get("campus").toString();
		level_list = loadPageListService.getLevel_list();// 获得年级列表

		List<Map<String, Object>> list = searchStudentService.findByTrRequest_MAP(name, school, level, now_class,
				telephone, stuff, campus);
		List<Map<String, Object>> tmp = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(start_look,
				PAGE_MAX, list);// 显示前15行数据
		// 处理，将部分的内容进行翻译
		studentList = transLateService.transLateStudent(level_list, tmp);

		item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);

		resultMap.put("studentList", studentList);
		resultMap.put("all_page", item.get("all_page"));
		resultMap.put("firstItem", item.get("firstItem"));
		resultMap.put("lastItem", item.get("lastItem"));
		resultMap.put("allItem", item.get("allItem"));
	}

	// 返回学员信息列表、总页数、当前页的第一条记录号，最后一条，以及总的学员信息数目

	public String execute() {

		try {
			resultMap.clear();// 先清空数据
			doLoad();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查询例子失败。参数start_look：" + start_look + ",name:" + name
					+ ",school:" + school + ",level:" + level + ",telephone:" + telephone + ",now_class:" + now_class
					+ ",TrId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;

	}
}
