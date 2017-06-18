package com.yaodingjiaoyu.action.sd;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Sd_ChangeStudent_Action {
	//获得相关的列表信息:年级，是否有效，是否上门，可能性，来源，是否分配，销售，校区
	private List<Level> level_list;
	private List<Campus> campus_list;
	private PageListService loadPageListService;
	private int id;
	
	private UserService studentService;
	
	private Student student;
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}


	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private String changeStudent() {
		String campus= ActionContext.getContext().getSession().get("campus").toString();
		String power= ActionContext.getContext().getSession().get("power").toString();
		
		//初始化列表
		level_list = loadPageListService.getLevel_list();
		campus_list = loadPageListService.getCampus_list();
				
		student = (Student) studentService.getUser(id);
		if(student != null){
			
			if(("sd".equals(power))&&(campus.equals(student.getCampus().getPId().toString()))){
				return "success";
			}else{
				return "error";
			}
			
		}else{
			return "error";
		}
	}
	
	public String execute(){

		try {
			return changeStudent();
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:程序运行异常。参数StuID："+id+
					",SdId:"+ActionContext.getContext().getSession().get("ID")+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
		
	}
}
