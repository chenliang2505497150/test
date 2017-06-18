package com.yaodingjiaoyu.action.tr;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class Tr_Classtable_Action2 {

	// 显示列表形式的课表

	private TransLateService transLateService;
	private ClassTableService classTableService;
	private PageListService pageListService;
	private List<Map<String, Object>> ContentList = null;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetItemInfoService getItemInfoService;
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据

	// 需要显示的列表
	private List<ClassTime> class_time_list = null;
	private List<CourseType> course_type_list = null;
	private List<Level> level_list = null;
	private List<Subject> subject_list = null;

	public List<ClassTime> getClass_time_list() {
		return class_time_list;
	}

	public void setClass_time_list(List<ClassTime> class_time_list) {
		this.class_time_list = class_time_list;
	}

	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}

	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}

	public List<Subject> getSubject_list() {
		return subject_list;
	}

	public void setSubject_list(List<Subject> subject_list) {
		this.subject_list = subject_list;
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

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	@SuppressWarnings("unchecked")
	private void doLoad() {
		int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
		// 获得存储在SESSION中的校区和职工编号
		String stuff = ActionContext.getContext().getSession().get("ID").toString();
		String campus = ActionContext.getContext().getSession().get("campus").toString();

		List<ClassTable> list = classTableService.findTrClassTable("no", stuff, "", "", "", "no", "no", campus, "no",
				"no");
		List<ClassTable> tmp = (List<ClassTable>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);// 显示前15行数据
		ContentList = transLateService.transLateClassTableListToMap(tmp);
		item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);

		level_list = pageListService.getLevel_list();// 获得年级列表
		course_type_list = pageListService.getCourseTypeList();
		subject_list = pageListService.getSubjectList();
		class_time_list = pageListService.getClassTimeList();
	}

	public String execute() {
		try {
			doLoad();
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:操作失败。参数TrId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
