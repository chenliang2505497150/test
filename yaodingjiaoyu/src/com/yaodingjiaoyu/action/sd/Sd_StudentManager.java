package com.yaodingjiaoyu.action.sd;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

public class Sd_StudentManager {
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
				message = "sd_stuinfo_1";
			}
			if ("2".equals(id)) {
				message = "sd_stuinfo_2";
			}

			return message;
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:程序运行异常。参数SdId:"
			+ActionContext.getContext().getSession().get("ID")+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
