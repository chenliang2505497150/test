package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.Service.ResetPassService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 该类用于重置员工账户密码，并返回新密码
 * 
 * @author chenliang
 *
 */
public class Sd_ResetPass_Ajax {
	private int id;
	private AccessManagerService accessManagerService;
	private UserService stuffService;
	private ResetPassService resetPassService;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccessManagerService(AccessManagerService accessManagerService) {
		this.accessManagerService = accessManagerService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public void setResetPassService(ResetPassService resetPassService) {
		this.resetPassService = resetPassService;
	}

	public void doReset() {
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		String power = ActionContext.getContext().getSession().get("power").toString();

		Stuff stuff = (Stuff) stuffService.getUser(id);
		if (null != stuff) {
			if (!accessManagerService.SdToStuff(power, campus, stuff)) {
				resultMap.put("status", 202);// 失败
				resultMap.put("message", "没有权限!");
			} else {
				String newpass = resetPassService.SdResetStuff(stuff);
				if (null != newpass) {
					resultMap.put("status", 200);
					resultMap.put("message", "重置完成,新密码:" + newpass);
				} else {
					resultMap.put("status", 201);
					resultMap.put("message", "重置失败!");
				}
			}
		} else {
			resultMap.put("status", 201);
			resultMap.put("message", "重置失败!");
		}
	}

	public String execute() {
		try {
			resultMap.clear();
			doReset();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:重置密码。参数id：" + id + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
