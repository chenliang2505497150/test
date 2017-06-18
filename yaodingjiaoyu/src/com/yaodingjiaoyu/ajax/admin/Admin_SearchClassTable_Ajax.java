package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
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
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Admin_SearchClassTable_Ajax {
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private TransLateService transLateService;
	private ClassTableService classTableService;
	private PageListService pageListService;
	private List<Map<String, Object>> Contennt_list = null;
	private int start_look;// 本次浏览的起始页
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private GetItemInfoService getItemInfoService;
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	
	private String student;
	private String teacher;
	private String time1;
	private String time2;
	private String level;
	private String subject;
	private String class_time;
	private String course_type;
	private String status;
	private String campus;
	private String cr;
	
	

	
	
	
	public String getCampus() {
		return campus;
	}




	public void setCampus(String campus) {
		this.campus = campus;
	}




	public String getCr() {
		return cr;
	}




	public void setCr(String cr) {
		this.cr = cr;
	}




	public String getStudent() {
		return student;
	}




	public void setStudent(String student) {
		this.student = student;
	}




	public String getTeacher() {
		return teacher;
	}




	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}




	public String getTime1() {
		return time1;
	}




	public void setTime1(String time1) {
		this.time1 = time1;
	}




	public String getTime2() {
		return time2;
	}




	public void setTime2(String time2) {
		this.time2 = time2;
	}




	public String getLevel() {
		return level;
	}




	public void setLevel(String level) {
		this.level = level;
	}




	public String getSubject() {
		return subject;
	}




	public void setSubject(String subject) {
		this.subject = subject;
	}




	public String getClass_time() {
		return class_time;
	}




	public void setClass_time(String class_time) {
		this.class_time = class_time;
	}




	public String getCourse_type() {
		return course_type;
	}




	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}


	public int getStart_look() {
		return start_look;
	}




	public void setStart_look(int start_look) {
		this.start_look = start_look;
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
	public String execute(){
		
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			resultMap.clear();
			
			List<Stuff> tr_list_tmp = pageListService.getTrListByCampusAndInput(campus, teacher);
			
			List<ClassTable> list =classTableService.findClassTable(subject, tr_list_tmp, cr, student, time1, time2, status, class_time, campus, course_type, level);
			List<ClassTable> tmp = (List<ClassTable>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX, list);//显示前15行数据
			Contennt_list = transLateService.transLateClassTableListToMap(tmp);
			item =  getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
			
			
			resultMap.put("Contennt_list", Contennt_list);	
			resultMap.put("all_page",item.get("all_page"));
			resultMap.put("firstItem", item.get("firstItem"));
			resultMap.put("lastItem", item.get("lastItem"));
			resultMap.put("allItem", item.get("allItem"));
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查询课表失败。参数start_look："+start_look+",time1:"+time1+
					",time2:"+time2+",student:"+student+",campus:"+campus+",teacher:"+teacher+",level:"+level+",subject:"+
					subject+",class_time:"+class_time+",course_type:"+course_type+",status:"+status+",cr:"+cr+",MESSAGE:"
					+e.getMessage());
			return "error";
		}
		return null;
	}
}
