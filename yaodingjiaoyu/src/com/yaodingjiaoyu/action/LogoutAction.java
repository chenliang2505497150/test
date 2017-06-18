package com.yaodingjiaoyu.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class LogoutAction {

	public LogoutAction() {
		super();
	}

	/**
	 * 退出登陆并返回登陆界面
	 * 
	 * @return
	 */
	public String execute() {
		try {
			// 清空当前用户相关的session对象
			ServletActionContext.getRequest().getSession().invalidate();
			return "login";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:退出登陆失败");
			return "error";
		}
	}
}
