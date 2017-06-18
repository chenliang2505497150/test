package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Cr_GetUnitPrice_Ajax {

	private UnitPriceService unitPriceService;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private UserService studentService;
	private int id;
	private int course_type;
	private int price;
	
	
	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
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



	public int getCourse_type() {
		return course_type;
	}



	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}

	private void getUnitPrice() {
		Student student = (Student) studentService.getUser(id);
		if(student != null){
			
			int campus= (int) ActionContext.getContext().getSession().get("campus");
			price = unitPriceService.GetUnitPriceBy(campus, course_type, student.getLevel().getPId());
			resultMap.put("price", price);
			resultMap.put("status", 200);
			
		}else{
			resultMap.put("status", 400);
		}
	}

	public String execute(){
		
		try {
			resultMap.clear();
			getUnitPrice();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:确认上课失败。参数id："+id
					+",course_type:"+course_type+",price:"+price+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
