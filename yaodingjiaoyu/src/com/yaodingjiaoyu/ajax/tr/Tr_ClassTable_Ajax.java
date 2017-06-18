package com.yaodingjiaoyu.ajax.tr;

import java.util.Date;
import java.util.HashMap;
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
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Tr_ClassTable_Ajax {
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private TransLateService transLateService;
	private DayService dayService;
	private ClassTableService classTableService;
	private Map<String, String> day = null;
	private Map<String, Object> table = null;
	private List<Map<String, Object>> class_time = null;
	private PageListService pageListService;
	private UserService stuffService;

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	private void getClassTable() {
		if (time == null) {
			time = new Date();
		}
		// 获得存储在SESSION中的校区和职工编号
		String campus = ActionContext.getContext().getSession().get("campus").toString();
		String stuff = ActionContext.getContext().getSession().get("ID").toString();

		Stuff teacher = (Stuff) stuffService.getUser(Integer.parseInt(stuff));

		List<ClassTime> class_time_tmp = pageListService.getClassTimeList();
		class_time = transLateService.transLateClassTimeList(class_time_tmp);

		// 可以获得指定日期的周一和周日
		day = dayService.getMondayAndSundayOfWeek(time);

		// 获得课表
		List<ClassTable> list = classTableService.findClassTableByTr(day.get("one"), day.get("seven"), campus, stuff);
		table = transLateService.transLateClassTableListForTr(list);

		// 根据日期和员工的编号以及校区获得课表的列表

		// 需要返回的数据:tr_list,table,day,class_time
		resultMap.put("table", table);
		resultMap.put("day", day);
		resultMap.put("class_time", class_time);
		resultMap.put("teacher_name", teacher.getName());
		resultMap.put("teacher_phone", teacher.getPhone());
	}

	public String execute() {

		try {
			resultMap.clear();
			getClassTable();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化课表失败。参数time：" + time + ",TrId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
