package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Admin_DeleteHetong_Ajax {

	private int id;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private HetongService hetongService;
	private UserService studentService;
	private ClassHourService classHourService;
	
	
	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}

	//删除合同
	public void DoDeleteHetong() {
		
		Hetong hetong = hetongService.findById(id);
		
		if(hetong == null){
			
			resultMap.put("status", 400);
			resultMap.put("message", "没有该合同");
		}else{
			Student student = (Student) studentService.getUser(hetong.getStudent().getPId());
			ClassHour classHour =  classHourService.getClassHour(hetong.getStudent().getPId().toString(),hetong.getCourseType().getPId().toString());
			int hour = hetong.getNormalHour();
			student.setAllHour(student.getAllHour()-hour);
			student.setLastHour(student.getLastHour()-hour);
			
			classHour.setAllHour(classHour.getAllHour()-hour);
			classHour.setLastHour(classHour.getLastHour()-hour);
			
			studentService.update(student);
			classHourService.update(classHour);
			//执行删除操作
			if(hetongService.DeleteHetong(hetong)){
				//回档课时信息
				resultMap.put("status", 200);
				resultMap.put("message", "删除成功");
			}else{
				resultMap.put("status", 401);
				resultMap.put("message", "删除失败");
			}
		}
	}
	
	
	public String execute(){
		
		try {
			resultMap.clear();
			DoDeleteHetong();
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
