package com.yaodingjiaoyu.action.generalAjax;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Student_detail_Ajax {

	private int id;
	
	private UserService studentService;
	
	private Map<String, Object> student;
	
	private TransLateService transLateService;
	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}


	public Map<String, Object> getStudent() {
		return student;
	}

	public void setStudent(Map<String, Object> student) {
		this.student = student;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private void getStudentDetail() {
		//获得存储在SESSION中的校区和职工编号
		String stuff=  ActionContext.getContext().getSession().get("ID").toString();
		String campus= ActionContext.getContext().getSession().get("campus").toString();
		String power = ActionContext.getContext().getSession().get("power").toString(); 
		
		Student tmp = (Student) studentService.getUser(id);
		
		if(tmp != null){
			//判断该老师有没有这个学生
			if(tmp.getStuff() != null){
				
				if(((tmp.getStuff().getPId() == Integer.parseInt(stuff))&&(tmp.getCampus().getPId() == Integer.parseInt(campus)))||(("sd".equals(power))&&(tmp.getCampus().getPId() == Integer.parseInt(campus)))||("admin".equals(power))){
					student = transLateService.transLateStudent(tmp);
				}else{
					student = null;
				}
				
			}else{
				student = null;
			}
			
			
		}else{
			student = null;
		}
	}
	
	public String execute(){
		
		try {
			getStudentDetail();
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查找学生详情失败。参数ID："+id+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
