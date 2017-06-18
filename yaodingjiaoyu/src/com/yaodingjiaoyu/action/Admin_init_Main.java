package com.yaodingjiaoyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Admin_init_Main {

	private Stuff stuff;
	private UserService stuffService;

	public UserService getStuffService() {
		return stuffService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	// 初始化管理员信息
	public String execute() {

		// 通过SESSION获得员工的ID，根据ID查询员工对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		try {

			int id = (int) session.getAttribute("ID");
			stuff = (Stuff) stuffService.getUser(id);

			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:处理登陆请求失败。参数:" + "ID:" + session.getAttribute("ID"));
			return "error";
		}

	}

}
