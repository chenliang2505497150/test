package com.yaodingjiaoyu.ajax.cr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Student;

import net.sf.json.JSONObject;

public class Cr_ConfirmClassTable_List_Ajax {
	private String classtable_list_json;
	private ClassTableService classTableService;
	private UserService studentService;
	private ClassHourService classHourService;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public String getClasstable_list_json() {
		return classtable_list_json;
	}

	public void setClasstable_list_json(String classtable_list_json) {
		this.classtable_list_json = classtable_list_json;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}

	private boolean checkIsLegal(String StuCr) {
		String Cr=  ActionContext.getContext().getSession().get("ID").toString();
		if(!Cr.equals(StuCr)){
			return false;
		} else {
			return true;
		}
	}
	@SuppressWarnings("rawtypes")
	private void doExecute() {
		// 首先需要把JSON转成数组
		JSONObject jsonObject = JSONObject.fromObject(classtable_list_json);
		Iterator it = jsonObject.keys();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			// 确认上课
			confirmClassTable(Integer.parseInt(value));

		}

		resultMap.put("status", 200);
		resultMap.put("message",resultMap.get("message") + "操作完成!");
	}

	private void confirmClassTable(int id) {
		// 获取classtable
		ClassTable class_table = classTableService.geTable(id);

		if (class_table.getStatus() == 0) {

			// 获得学生
			Student student = (Student) studentService.getUser(class_table.getStudent().getPId());
			
			//判断是否是该学生的班主任
			String StuCr = student.getStuff().getPId().toString();
			if(!checkIsLegal(StuCr)){
				return ;
			}
			
			// 获得课时表
			ClassHour classHour = classHourService.getClassHour(class_table.getStudent().getPId() + "",
					class_table.getCourseType().getPId() + "");

			if ((student == null) || (classHour == null)) {

				resultMap.put("message", resultMap.get("message") + "课程编号" + id + "服务器异常,请联系管理员、");

			} else {

				// 对学生，以及课时表进行处理
				student.setLastHour(student.getLastHour() - 3);
				classHour.setLastHour(classHour.getLastHour() - 3);
				class_table.setStatus(1);
				// 更新对象
				studentService.update(student);
				classHourService.update(classHour);
				classTableService.update(class_table);
			}

		} else {
			resultMap.put("message", resultMap.get("message") + "课程编号" + id + "该课程已经完成、");
		}
	}

	public String execute() {

		try {
			resultMap.clear();
			resultMap.put("message","");
			doExecute();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			resultMap.put("status", 400);
			resultMap.put("message","服务器遇到未知错误!");
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数classtable_list_json："
					+ classtable_list_json + ",SdId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:"
					+ e.getMessage());
			return "error";
		}

		return null;
	}
}
