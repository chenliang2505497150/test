package com.yaodingjiaoyu.action.tr;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

public class TrStuManagerAction {
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
				message = "tr_stuinfo_1";
			}

			return message;
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:操作失败。参数TrId:"
					+ ActionContext.getContext().getSession().get("ID") + ",ID:" + id + ",MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
