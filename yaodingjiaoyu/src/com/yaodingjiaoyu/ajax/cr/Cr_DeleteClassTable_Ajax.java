package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;

public class Cr_DeleteClassTable_Ajax {

	private int id;// 获得的课程编号,通过该编号删除课程
	private ClassTableService classTableService;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 权限操作判断
	public boolean isLegal(ClassTable classTable) {
		int stuff = (int) ActionContext.getContext().getSession().get("ID");
		try {
			// 如果是校长，因为管理员删除不再这个action处理
			// 获取用户的对象

			String power = ActionContext.getContext().getSession().get("power").toString();
			int stu_stuff = classTable.getStudent().getStuff().getPId();

			if ("sd".equals(power)) {
				return true;
			}
			if (stuff == stu_stuff) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			resultMap.put("status", 400);
			resultMap.put("message", "删除课程失败");

			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->isLegal:删除课表失败。参数id：" + id + ",stuff:" + stuff + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

	private void doDeleteTable(ClassTable classTable) {
		// 如过课程为已上，则班主任不能修改

		if (classTable.getStatus() == 0) {// 未上课

			if (classTableService.deleteTable(id)) {
				resultMap.put("status", 200);
				resultMap.put("message", "删除课程成功");
			} else {
				resultMap.put("status", 400);
				resultMap.put("message", "删除课程失败");
			}

		} else {
			resultMap.put("status", 401);
			resultMap.put("message", "不能删除已经完成的课程");
		}
	}

	public String execute() {

		try {
			resultMap.clear();
			ClassTable classTable = classTableService.geTable(id);
			if (isLegal(classTable)) {
				doDeleteTable(classTable);
			} else {
				resultMap.put("status", 402);
				resultMap.put("message", "没有删除该课程的权限!");
			}

			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:删除课表失败。参数id：" + id + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
