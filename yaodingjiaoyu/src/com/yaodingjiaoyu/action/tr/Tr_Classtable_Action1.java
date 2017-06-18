package com.yaodingjiaoyu.action.tr;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.DayService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Tr_Classtable_Action1 {
	private TransLateService transLateService;
	private DayService dayService;
	private ClassTableService classTableService;
	private Map<String, String> day = null;
	private Map<String, Object> table = null;
	private List<ClassTime> class_time = null;
	private PageListService pageListService;
	private UserService stuffService;
	private Stuff teacher;
	// 获得在读学员列表
	private List<Student> stu_list = null;
	// 获得课程类型列表
	private List<CourseType> course_type_list = null;

	public Stuff getTeacher() {
		return teacher;
	}

	public void setTeacher(Stuff teacher) {
		this.teacher = teacher;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
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

	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	public List<ClassTime> getClass_time() {
		return class_time;
	}

	public void setClass_time(List<ClassTime> class_time) {
		this.class_time = class_time;
	}

	public Map<String, Object> getTable() {
		return table;
	}

	public void setTable(Map<String, Object> table) {
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
			String stuff = ActionContext.getContext().getSession().get("ID").toString();
			teacher = (Stuff) stuffService.getUser(Integer.parseInt(stuff));

			class_time = pageListService.getClassTimeList();
			course_type_list = pageListService.getCourseTypeList();
			// 可以获得指定日期的周一和周日
			day = dayService.getMondayAndSundayOfWeek(new Date());

			// 获得课表
			List<ClassTable> list = classTableService.findClassTableByTr(day.get("one"), day.get("seven"), campus,
					stuff);
			table = transLateService.transLateClassTableListForTr(list);
			// 根据日期和员工的编号以及校区获得课表的列表
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
