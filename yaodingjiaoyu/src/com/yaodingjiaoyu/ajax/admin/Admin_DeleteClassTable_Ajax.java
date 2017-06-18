package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Admin_DeleteClassTable_Ajax {
	private int id;//获得的课程编号,通过该编号删除课程
	private ClassTableService classTableService;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private UserService studentService;
	private ClassHourService classHourService;
	private int CLASS_HOUR;
	
	
	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}
	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}
	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	//删除已经完成的课程
	private void DeletePastClassTable(ClassTable classTable) {
		//如过课程为已上,需要回滚课时
		Student student = classTable.getStudent();
		ClassHour classHour = classHourService.getClassHour(student.getPId().toString(), classTable.getCourseType().getPId().toString());
		//总课时不能变更，剩余可是是需要增加的
		
		student.setLastHour(student.getLastHour()+CLASS_HOUR);
		boolean ok1 =  studentService.update(student);
		
		//classHour.setAllHour(classHour.getAllHour()+CLASS_HOUR);
		classHour.setLastHour(classHour.getLastHour()+CLASS_HOUR);
		boolean ok2 =classHourService.update(classHour);
		
		boolean ok3 = classTableService.deleteTable(id);
		if(ok1&&ok2&&ok3){
			resultMap.put("status", 200);
			resultMap.put("message", "删除课程成功");
		}else{
			resultMap.put("status", 401);
			resultMap.put("message", "删除课程失败");
		}
	}
	
	private void Do_Delete() {
		
		ClassTable classTable = classTableService.geTable(id);
		
		if(classTable.getStatus() == 0){//未上课
			
			if(classTableService.deleteTable(id)){
				resultMap.put("status", 200);
				resultMap.put("message", "删除课程成功");
			}else{
				resultMap.put("status", 400);
				resultMap.put("message", "删除课程失败");
			}
			
		}else{
			
			DeletePastClassTable(classTable);
		}
		
	}
	
	public  String execute(){

		try {
			
			CLASS_HOUR = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("CLASS_HOUR"));
			resultMap.clear();
			Do_Delete();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
		
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:删除课表信息失败。参数ID:"+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
