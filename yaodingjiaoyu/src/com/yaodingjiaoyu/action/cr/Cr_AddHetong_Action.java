package com.yaodingjiaoyu.action.cr;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class Cr_AddHetong_Action {

	//加载各个列表
	private int id;
	private List<Subject> subject_list = null;
	private List<HtProperty> ht_property_list = null;
	private List<CourseType> course_type_list = null;
	private int unit_price = 0;
	private UnitPriceService unitPriceService;
	private PageListService loadPageListService;
	private UserService studentService;
	
	
	
	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public List<Subject> getSubject_list() {
		return subject_list;
	}




	public void setSubject_list(List<Subject> subject_list) {
		this.subject_list = subject_list;
	}




	public List<HtProperty> getHt_property_list() {
		return ht_property_list;
	}




	public void setHt_property_list(List<HtProperty> ht_property_list) {
		this.ht_property_list = ht_property_list;
	}




	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}




	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
	}




	public int getUnit_price() {
		return unit_price;
	}




	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}




	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
	}




	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}




	public String execute() {
		try {
			//初始化列表
			int campus= (int) ActionContext.getContext().getSession().get("campus");
			Student student = (Student) studentService.getUser(id);
			//获得学生的年级
			if(student != null){
				unit_price = unitPriceService.GetUnitPriceBy(campus, 1, student.getLevel().getPId());
				course_type_list = loadPageListService.getCourseTypeList();
				ht_property_list = loadPageListService.getHtPropertyList();
				subject_list = loadPageListService.getSubjectList();
				return "success";
			}else{
				return "error";
			}
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:添加合同失败。参数ID："+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
		
	}
}
