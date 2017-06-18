package com.yaodingjiaoyu.action.cc;

import org.apache.log4j.Logger;

public class CcExamplesManager {
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

			switch (id) {
			case "1":
				message = "cc_examples_1";
				break;
				
			case "2":
				message = "cc_examples_2";
				break;
			
			case "3":
				message = "cc_examples_3";
				break;
				

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
