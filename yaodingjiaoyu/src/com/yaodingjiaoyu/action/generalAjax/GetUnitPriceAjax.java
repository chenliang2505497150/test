package com.yaodingjiaoyu.action.generalAjax;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.UnitPriceService;

public class GetUnitPriceAjax {

	private UnitPriceService unitPriceService;
	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
	}
	
	private Map<String, Object> resultMap = new HashMap<String,Object>();


	
	private int level;
	private int course_type;
	private int price;
	
	
	
	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getCourse_type() {
		return course_type;
	}



	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}



	public String execute(){
		try {
			resultMap.clear();
			int campus= (int) ActionContext.getContext().getSession().get("campus");
			price = unitPriceService.GetUnitPriceBy(campus, course_type, level);
			resultMap.put("price", price);
			resultMap.put("status", 200);
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查找课堂跟踪记录失败。参数level："
			+level+",course_type:"+course_type+",price:"+price+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
		
		
		return null;
	}
}
