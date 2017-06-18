package com.yaodingjiaoyu.action.sd;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.DayService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_Classtable_ActionMap {
	private TransLateService transLateService;
	private DayService dayService;
	private ClassTableService classTableService;
	private Map<String, String> day = null;
	private Map<String, Map<String, Object>> table = null;
	private List<ClassTime> class_time = null;
	private PageListService pageListService;
	private List<Stuff> tr_list = null;

	private SearchStudentService searchStudentService;

	// 获得在读学员列表
	private List<Student> stu_list = null;
	// 获得课程类型列表
	private List<CourseType> course_type_list = null;

	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	public List<Student> getStu_list() {
		return stu_list;
	}

	public void setStu_list(List<Student> stu_list) {
		this.stu_list = stu_list;
	}

	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}

	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public List<Stuff> getTr_list() {
		return tr_list;
	}

	public void setTr_list(List<Stuff> tr_list) {
		this.tr_list = tr_list;
	}

	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	public List<ClassTime> getClass_time() {
		return class_time;
	}

	public void setClass_time(List<ClassTime> class_time) {
		this.class_time = class_time;
	}

	public Map<String, Map<String, Object>> getTable() {
		return table;
	}

	public void setTable(Map<String, Map<String, Object>> table) {
		this.table = table;
	}

	public Map<String, String> getDay() {
		return day;
	}

	public void setDay(Map<String, String> day) {
		this.day = day;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}

	public String execute() {
		try {
			// 获得存储在SESSION中的校区和职工编号
			String campus = ActionContext.getContext().getSession().get("campus").toString();

			tr_list = pageListService.getTrListByCampusAndInput(campus, "");
			class_time = pageListService.getClassTimeList();
			course_type_list = pageListService.getCourseTypeList();
			stu_list = searchStudentService.findBySdRequest("","","no","","","1",campus,"no","no");

			// 可以获得指定日期的周一和周日
			day = dayService.getMondayAndSundayOfWeek(new Date());

			// 获得课表
			List<ClassTable> list = classTableService.findClassTableByCr(day.get("one"), day.get("seven"), campus,
					null);
			table = transLateService.transLateClassTableList(list);

			// 根据日期和员工的编号以及校区获得课表的列表
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:添加合同失败,MESSAGE:" + e.getMessage()+",stuff:"+ActionContext.getContext().getSession().get("ID").toString());
			return "error";
		}
	}
}
