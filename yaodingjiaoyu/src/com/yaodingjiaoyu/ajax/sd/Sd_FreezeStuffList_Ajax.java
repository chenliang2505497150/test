package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.Service.SaveStuffService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import net.sf.json.JSONObject;

public class Sd_FreezeStuffList_Ajax {
	private String stuff_list_json;// 获得的员工编号列表
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

	public String getStuff_list_json() {
		return stuff_list_json;
	}

	public void setStuff_list_json(String stuff_list_json) {
		this.stuff_list_json = stuff_list_json;
	}

	public void setSaveStuffService(SaveStuffService saveStuffService) {
		this.saveStuffService = saveStuffService;
	}

	@SuppressWarnings("rawtypes")
	private void doExecute() {
		resultMap.put("message", "");
		// 首先需要把JSON转成数组
		JSONObject jsonObject = JSONObject.fromObject(stuff_list_json);
		Iterator it = jsonObject.keys();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			// 确认上课
			freezeStuff(Integer.parseInt(value));

		}

		resultMap.put("status", 200);
		resultMap.put("message", resultMap.get("message") + "操作完成!");
	}

	public void freezeStuff(int id) {
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		String power = ActionContext.getContext().getSession().get("power").toString();

		Stuff stuff = (Stuff) stuffService.getUser(id);
		if (null != stuff) {
			if (!accessManagerService.SdToStuff(power, campus, stuff)) {
				resultMap.put("message", resultMap.get("message")+"编号:"+id+"没有权限,");
			} else {
				boolean success = saveStuffService.freezeStuffBySd(stuff);
				if (!success) {
					resultMap.put("message", resultMap.get("message")+"编号:"+id+"冻结失败,");
				}
			}
		} else {
			resultMap.put("status", 201);
			resultMap.put("message", resultMap.get("message")+"编号:"+id+"冻结失败,");
		}
	}

	public String execute() {
		try {
			resultMap.clear();
			doExecute();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:冻结员工账号。参数stuff_list_json：" + stuff_list_json + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
