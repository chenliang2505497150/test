package com.yaodingjiaoyu.action.generalAjax;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ChangeStuffInfo_Ajax {
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private String sex;
	private UserService stuffService;

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private void Do_Change(int id) {

		boolean success = false;
		// 通过编号获得原来的例子
		Stuff stuff = (Stuff) stuffService.getUser(id);

		if (null != stuff) {
			stuff.setSex(sex);
			// 更新例子
			success = stuffService.update(stuff);
			// 返回信息
			if (success) {
				resultMap.put("status", 200);
				resultMap.put("message", "修改成功!");
			} else {
				resultMap.put("status", 400);// 失败
				resultMap.put("message", "修改失败!");
			}
		} else {
			resultMap.put("status", 400);// 失败
			resultMap.put("message", "没有该用户信息!");
		}

	}

	public String execute() {

		// 通过SESSION获得员工的ID，根据ID查询员工对象
		try {
			resultMap.clear();
			int id = (int) ActionContext.getContext().getSession().get("ID");
			Do_Change(id);

			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数id："
					+ ActionContext.getContext().getSession().get("ID") + ",sex:" + sex + ",MESSAGE:" + e.getMessage());
			return "error";
		}

		return null;
	}

}
