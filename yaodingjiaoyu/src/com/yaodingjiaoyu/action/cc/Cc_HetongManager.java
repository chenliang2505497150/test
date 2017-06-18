package com.yaodingjiaoyu.action.cc;

import org.apache.log4j.Logger;

public class Cc_HetongManager {
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
				message = "cc_hetong_1";
			}
			if("2".equals(id)) {
				message = "cc_hetong_2";
			}	
				return message;
		} catch (Exception e) {
			
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化列表失败。参数:"+"ID:"+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
