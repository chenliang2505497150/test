package com.yaodingjiaoyu.ajax.cr;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassContentService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Cr_AddContent_Ajax {
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private int id;
	private String contents;
	private ClassContentService classContentService;
	private UserService stuffService;
	
	public void setClassContentService(ClassContentService classContentService) {
		this.classContentService = classContentService;
	}



	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}



	public String getContents() {
		return contents;
	}



	public void setContents(String contents) {
		this.contents = contents;
	}

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	private void DoAddContent() {
		int success = 0;
		//获得存储在SESSION中的校区和职工编号
		int stuff=  (int) ActionContext.getContext().getSession().get("ID");
		
		Stuff stuff_obj = (Stuff) stuffService.getUser(stuff);
		Date now = new Date();
		
		success = classContentService.addContent(id, now, contents, stuff_obj);
		
		
		if(success!=0){//成功
			resultMap.put("status", 200);
			resultMap.put("message", "添加成功!");
		}
		else{
			resultMap.put("status", 400);
			resultMap.put("message", "添加失败!");
		}
	}
	public String execute(){
		try {
			resultMap.clear();
			DoAddContent();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:添加课表失败。参数ID："+id+",contents:"+contents);
			return "error";
		}

		return null;
	}
}
