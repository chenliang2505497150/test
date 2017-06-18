package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Cr_ConfirmClassTable_Ajax {
	private int id;//获得的课程编号,通过该编号删除课程
	private ClassTableService classTableService;
	private UserService studentService;
	private ClassHourService classHourService;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	
	
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

	private void confirmClassTable() {
		//获取classtable
		ClassTable class_table = classTableService.geTable(id);
		
		if(class_table.getStatus() == 0){
			
			//获得学生
			Student student = (Student) studentService.getUser(class_table.getStudent().getPId());
			//获得课时表
			ClassHour classHour = classHourService.getClassHour(class_table.getStudent().getPId()+"", class_table.getCourseType().getPId()+"");
			
			if((student ==null)||(classHour == null)){
				
				resultMap.put("status", 500);
				resultMap.put("message", "服务器异常,请联系管理员");
				
			}else{
				
				//对学生，以及课时表进行处理
				student.setLastHour(student.getLastHour()-3);
				classHour.setLastHour(classHour.getLastHour()-3);
				class_table.setStatus(1);
				//更新对象
				studentService.update(student);
				classHourService.update(classHour);
				classTableService.update(class_table);
				
				resultMap.put("status", 200);
				resultMap.put("message", "操作课程成功");
				
			}
			
			
			
		}else{
			resultMap.put("status", 401);
			resultMap.put("message", "该课程已经完成");
		}
	}
	
	public  String execute(){
		
		try {
			resultMap.clear();
			confirmClassTable();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
				
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:确认上课失败。参数id："+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
		
		return null;
	}
}
