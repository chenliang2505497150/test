package com.yaodingjiaoyu.action.sd;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;

public class Sd_StuffManager {
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
				message = "sd_manager_stuff";
			}
			if ("2".equals(id)) {
				message = "sd_create_account";
			}

			return message;
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:程序运行异常。参数SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
