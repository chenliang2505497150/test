package com.yaodingjiaoyu.ajax.admin;


import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public class Admin_DeleteExamples_Ajax {

	private int id;
	
	private ExamplesService examplesService;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
	}

	private Map<String, Object> resultMap = new HashMap<String,Object>();
	
	private void DoDelete() {
		
		Examples examples = examplesService.findById(id);
		if(examples == null){
			resultMap.put("status", 400);
			resultMap.put("message", "没有该例子");
		}else{
			//执行删除操作
			if(examplesService.DeleteExamples(examples)){
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
			DoDelete();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:删除例子信息失败。参数ID:"+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
