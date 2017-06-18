package com.yaodingjiaoyu.action.admin;

import org.apache.log4j.Logger;

public class Admin_ClassTableManager {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 管理员查看课表
	public String execute() {

		try {
			String message = "error";

			if ("1".equals(id)) {
				message = "admin_classtable_1";
			}
			

			return message.toString();
		} catch (Exception e) {

			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化列表失败。参数:" + "ID:" + id);
			return "error";
		}

	}
}
