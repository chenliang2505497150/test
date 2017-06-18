package com.yaodingjiaoyu.action.generalAjax;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;

public class GetClassHourAjax {

	private String student;
	private String course_type;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private ClassHourService classHourService;
	
	
	
	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getCourse_type() {
		return course_type;
	}
	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	
	private void getClassHour() {
		ClassHour classHour = classHourService.getClassHour(student, course_type);
		
		if(classHour != null){
			int last_hour = classHour.getLastHour();
			resultMap.put("last_hour", last_hour);
			resultMap.put("status", 200);
		}else{
			resultMap.put("last_hour", 0);
			resultMap.put("status", 200);
		}
	}
	
	public String execute(){
		
		try {
			resultMap.clear();
			getClassHour();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查看课时失败。参数student："+student+",course_type:"+course_type+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
