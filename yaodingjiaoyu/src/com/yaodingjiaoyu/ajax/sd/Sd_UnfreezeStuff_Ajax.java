package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.Service.SaveStuffService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 用于处理解除冻结员工账号请求
 * 
 * @author chenliang
 *
 */
public class Sd_UnfreezeStuff_Ajax {
	private int id;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private SaveStuffService saveStuffService;
	private AccessManagerService accessManagerService;
	private UserService stuffService;

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public void setAccessManagerService(AccessManagerService accessManagerService) {
		this.accessManagerService = accessManagerService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSaveStuffService(SaveStuffService saveStuffService) {
		this.saveStuffService = saveStuffService;
	}

	public void unfreezeStuff() {

		int campus = (int) ActionContext.getContext().getSession().get("campus");
		String power = ActionContext.getContext().getSession().get("power").toString();

		Stuff stuff = (Stuff) stuffService.getUser(id);
		if (null != stuff) {
			if (!accessManagerService.SdToStuff(power, campus, stuff)) {
				resultMap.put("status", 202);// 失败
				resultMap.put("message", "没有权限!");
			} else {
				boolean success = saveStuffService.unfreezeStuffBySd(stuff);
				if (success) {
					resultMap.put("status", 200);
					resultMap.put("message", "解冻完成!");
				} else {
					resultMap.put("status", 201);
					resultMap.put("message", "解冻失败!");
				}
			}
		} else {
			resultMap.put("status", 201);
			resultMap.put("message", "解冻失败!");
		}

	}

	public String execute() {
		try {
			resultMap.clear();
			unfreezeStuff();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:解冻员工账号。参数id：" + id + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
