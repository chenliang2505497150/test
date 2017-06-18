package com.yaodingjiaoyu.action.admin;

import org.apache.log4j.Logger;

public class Admin_Stuff_Manager {
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
			if ("1".equals(id)) {
				message = "admin_stuff_manager_1";
			}

			return message;
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化失败。");
			return "error";
		}
	}
}
