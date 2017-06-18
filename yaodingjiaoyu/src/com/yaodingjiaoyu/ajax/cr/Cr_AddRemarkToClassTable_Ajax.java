package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;

public class Cr_AddRemarkToClassTable_Ajax {

	// 班主任在确认上课之前可以给该课程添加备注，可以先获取具体课程对象，然后向课程备注信息后追加备注，最后交给课程服务保存课程
	private int id;// 获得的课程编号,通过该编号添加备注信息
	private String remark;// 客户端传入的备注信息
	private ClassTableService classTableService;// 课程的服务
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	private void addRemak() {
		try {
			// 首先需要获取课表对象
			ClassTable classTable = classTableService.geTable(id);
			if (null == classTable.getRemarks()) {
				classTable.setRemarks(remark);
			} else {
				classTable.setRemarks(classTable.getRemarks() + "," + remark);
			}
			// 更新课表对象
			boolean success = classTableService.update(classTable);
			if (success) {
				resultMap.put("status", 200);
				resultMap.put("message", "添加成功!");
			} else {
				resultMap.put("status", 201);
				resultMap.put("message", "添加失败!");
			}

		} catch (Exception e) {
			resultMap.put("status", 202);
			resultMap.put("message", "服务器错误,添加失败!");
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->addRemak:添加课程备注失败。参数id：" + id + ",remark:" + remark
					+ ",MESSAGE:" + e.getMessage());
		}
	}

	public String execute() {
		try {
			resultMap.clear();
			addRemak();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:添加课程备注失败。参数id：" + id + ",remark:" + remark
					+ ",MESSAGE:" + e.getMessage());
			return "error";
		}

		return null;
	}
}
