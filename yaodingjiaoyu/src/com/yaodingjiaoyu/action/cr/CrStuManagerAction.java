package com.yaodingjiaoyu.action.cr;

import org.apache.log4j.Logger;

public class CrStuManagerAction {
	
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String execute() {
		
		try {
			String message = "error";
			if("1".equals(id)) {
				message = "cr_stuinfo_1";
			}
			if("2".equals(id)) {
				message = "cr_stuinfo_2";
			}	
				return message;
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:加载课表失败,参数ID:"+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
