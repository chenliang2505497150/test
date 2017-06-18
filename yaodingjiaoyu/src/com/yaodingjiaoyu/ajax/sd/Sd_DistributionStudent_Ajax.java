package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.SaveStudentService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;
import net.sf.json.JSONObject;

public class Sd_DistributionStudent_Ajax {

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private int cr;
	private String student_list_json;
	private SaveStudentService saveStudentService;
	private UserService studentService;

	public void setSaveStudentService(SaveStudentService saveStudentService) {
		this.saveStudentService = saveStudentService;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public String getStudent_list_json() {
		return student_list_json;
	}

	public void setStudent_list_json(String student_list_json) {
		this.student_list_json = student_list_json;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	@SuppressWarnings("rawtypes")
	private void doDistributionStudent() {
		// 首先需要把JSON转成数组
		JSONObject jsonObject = JSONObject.fromObject(student_list_json);
		Iterator it = jsonObject.keys();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			// 将这些学生分配给指定的老师
			Student student = (Student) studentService.getUser(Integer.parseInt(value));
			if (student != null) {
				saveStudentService.SdDistributionStudent(student, cr);
			}

		}

		resultMap.put("status", 200);
		resultMap.put("message", "分配完成");
	}


	public String execute() {

		try {
			resultMap.clear();
			doDistributionStudent();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数cr：" + cr + ",student_list_json:"
					+ student_list_json + ",SdId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:"
					+ e.getMessage());
			return "error";
		}

		return null;
	}
}
