package com.yaodingjiaoyu.action.admin;

import org.apache.log4j.Logger;

public class AdminExamplesManager {
private String id;

	public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

	public String execute(){
		
		try {
			String message = "error";

			switch (id) {
			case "1":
				message = "admin_examples_1";
				break;
				
			case "2":
				message = "admin_examples_2";
				break;
			
			case "3":
				message = "admin_examples_3";
				break;
				

			}
				
				return message;
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化学生列表失败。");
			return "error";
		}
	}
}
