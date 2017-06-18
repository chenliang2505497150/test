package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;

import net.sf.json.JSONObject;

public class Cr_DeleteClassTable_List_Ajax {
	private String classtable_list_json;// 获得的课程编号列表,通过该编号删除课程
	private ClassTableService classTableService;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public String getClasstable_list_json() {
		return classtable_list_json;
	}

	public void setClasstable_list_json(String classtable_list_json) {
		this.classtable_list_json = classtable_list_json;
	}

	@SuppressWarnings("rawtypes")
	private void doExecute() {
		resultMap.put("message","");
		// 首先需要把JSON转成数组
		JSONObject jsonObject = JSONObject.fromObject(classtable_list_json);
		Iterator it = jsonObject.keys();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			// 确认上课
			doDeleteTable(Integer.parseInt(value));

		}

		resultMap.put("status", 200);
		resultMap.put("message", resultMap.get("message") + "操作完成!");
	}

	private void doDeleteTable(int id) {
		// 如过课程为已上，则班主任不能修改
		ClassTable classTable = classTableService.geTable(id);

		if (classTable.getStatus() == 0) {// 未上课
			if (!classTableService.deleteTable(id)) {
				resultMap.put("message", resultMap.get("message") + "课程编号:" + id + "删除课程失败、");
			}
		} else {
			resultMap.put("message", resultMap.get("message") + "课程编号:" + id + "不能删除已经完成的课程、");
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
			logger.error(this.getClass().getName() + "-->execute:删除课表失败。参数classtable_list_json：" + classtable_list_json
					+ ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
